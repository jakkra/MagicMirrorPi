/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Client sends streaming audio to Speech.Recognize via gRPC and returns streaming transcription.
//
// Uses a service account for OAuth2 authentication, which you may obtain at
// https://console.developers.google.com
// API Manager > Google Cloud Speech API > Enable
// API Manager > Credentials > Create credentials > Service account key > New service account.
//
// Then set environment variable GOOGLE_APPLICATION_CREDENTIALS to the full path of that file.

package se.jakobkrantz.magicmirror;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.cloud.speech.v1.InitialRecognizeRequest.AudioEncoding;
import com.google.protobuf.ByteString;
import com.google.protobuf.TextFormat;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.auth.ClientAuthInterceptor;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Client that sends streaming audio to Speech.Recognize and returns streaming transcript.
 */
public class RecognizeClient {

    private final String host;
    private final int port;
    private final int samplingRate;

    private static final float SAMPLE_RATE = 16000.0f;
    private static final int SAMPLE_SIZE_IN_BITS = 16;
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;
    private static ByteArrayOutputStream out;
    private static boolean running = true;
    private static AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);

    private final ManagedChannel channel;

    private final SpeechGrpc.SpeechStub stub;

    private static final List<String> OAUTH2_SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/cloud-platform");

    /**
     * Construct client connecting to Cloud Speech server at {@code host:port}.
     */
    public RecognizeClient(String host, int port, int samplingRate) throws IOException {
        this.host = host;
        this.port = port;
        this.samplingRate = samplingRate;


        GoogleCredentials creds = GoogleCredentials.fromStream(
                new FileInputStream("/home/jakkra/Documents/java-docs-samples/speech/MagicMirrorPI/src/main/java/se/jakobkrantz/magicmirror/MagicMirror-eb21122c894f.json"));
        creds = creds.createScoped(OAUTH2_SCOPES);
        channel = NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.TLS)
                .intercept(new ClientAuthInterceptor(creds, Executors.newSingleThreadExecutor()))
                .build();
        stub = SpeechGrpc.newStub(channel);
        System.out.println("Created stub for " + host + ":" + port);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Send streaming recognize requests to server.
     */
    public void recognize() throws InterruptedException, IOException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<RecognizeResponse> responseObserver = new StreamObserver<RecognizeResponse>() {
            @Override
            public void onNext(RecognizeResponse response) {
                System.out.println("Received response: " + TextFormat.printToString(response));
            }

            @Override
            public void onError(Throwable error) {
                Status status = Status.fromThrowable(error);
                System.out.println("recognize failed: {0}" +  status);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("recognize completed.");
                finishLatch.countDown();
            }
        };

        StreamObserver<RecognizeRequest> requestObserver = stub.recognize(responseObserver);
        try {
            // Build and send a RecognizeRequest containing the parameters for processing the audio.
            InitialRecognizeRequest initial = InitialRecognizeRequest.newBuilder()
                    .setEncoding(AudioEncoding.LINEAR16)
                    .setSampleRate(samplingRate)
                    .setLanguageCode("sv-SE")
                    .setInterimResults(true)
                    .setEnableEndpointerEvents(true)
                    .setInterimResults(false)
                    .build();
            RecognizeRequest firstRequest = RecognizeRequest.newBuilder()
                    .setInitialRequest(initial)
                    .build();
            requestObserver.onNext(firstRequest);

            /*// Open audio file. Read and send sequential buffers of audio as additional RecognizeRequests.
            FileInputStream in = new FileInputStream(new File(file));
            // For LINEAR16 at 16000 Hz sample rate, 3200 bytes corresponds to 100 milliseconds of audio.
            byte[] buffer = new byte[3200];
            int bytesRead;
            int totalBytes = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                totalBytes += bytesRead;
                AudioRequest audio = AudioRequest.newBuilder()
                        .setContent(ByteString.copyFrom(buffer, 0, bytesRead))
                        .build();
                RecognizeRequest request = RecognizeRequest.newBuilder()
                        .setAudioRequest(audio)
                        .build();
                requestObserver.onNext(request);
                // To simulate real-time audio, sleep after sending each audio buffer.
                // For 16000 Hz sample rate, sleep 100 milliseconds.
                Thread.sleep(samplingRate / 160);
            }*/

            TargetDataLine recorder;
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Error! Audio System line not supported.");
            } else {
                try {
                    recorder = AudioSystem.getTargetDataLine(format);
                    recorder.open();

                    recorder.start();
                    System.out.println("Start talking to recognize");

                    byte[] buffer = new byte[3200];

                    // Skip the first buffer, usually zeroes
                    //recorder.read(b, 0, b.length);

                    while ((running)) {
                        int bytesRead;
                        bytesRead = recorder.read(buffer, 0, buffer.length);

                        AudioRequest audio = AudioRequest.newBuilder()
                                .setContent(ByteString.copyFrom(buffer, 0, bytesRead))
                                .build();
                        RecognizeRequest request = RecognizeRequest.newBuilder()
                                .setAudioRequest(audio)
                                .build();
                        requestObserver.onNext(request);
                        //logger.info("Sent " + bytesRead + " bytes");

                        /*short[] s = null;
                        bytesRead = recorder.read(b, 0, b.length);

                        ByteBuffer bb = ByteBuffer.wrap(b, 0, bytesRead);
                        bb.order(ByteOrder.LITTLE_ENDIAN); // Swap
                        s = new short[bytesRead / 2];

                        bb.asShortBuffer().get(s);

                        d.processRaw(s, nbytes / 2, false, false);*/
                    }
                } catch (Exception e) {

                }
            }
        } catch (RuntimeException e) {
            // Cancel RPC.
            requestObserver.onError(e);
            throw e;
        }
        // Mark the end of requests.
        requestObserver.onCompleted();

        // Receiving happens asynchronously.
        finishLatch.await(1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) throws Exception {

        String host = "speech.googleapis.com";
        Integer port = 443;
        Integer sampling = 16000;

        RecognizeClient client = new RecognizeClient(host, port, sampling);
        try {
            client.recognize();
        } finally {
            client.shutdown();
        }
    }
}
