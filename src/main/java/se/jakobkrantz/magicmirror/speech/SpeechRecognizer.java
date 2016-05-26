package se.jakobkrantz.magicmirror.speech;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


import cmu.edu.cmu.pocketsphinx.Decoder;
import cmu.edu.cmu.pocketsphinx.Config;
import cmu.edu.cmu.pocketsphinx.Hypothesis;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * Created by jakkra on 2016-03-19.
 */
public class SpeechRecognizer extends Thread {

    private static final float SAMPLE_RATE = 16000.0f; //8kHz
    private static final int SAMPLE_SIZE_IN_BITS = 16;
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;
    private static ByteArrayOutputStream out;
    private static boolean running = true;


    //private static final String ACOUSTIC_MODEL = SpeechRecognizer.class.getResource("resource:/cmu.edu/cmu/sphinx/models/en-us/en-us").toString();
    //private static final String DICTIONARY = SpeechRecognizer.class.getResource("resource:/cmu.edu/cmu/sphinx/models/en-us/8949.dic").toString();
    //private static final String KEY_PHRASES = SpeechRecognizer.class.getResource("resource:/cmu.edu/cmu/sphinx/models/en-us/keyphrase-list").toString();

    private static AudioFormat format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);

    static {
        System.loadLibrary("pocketsphinx_jni");
    }


    private final Set<VoiceCommandListener> listeners
            = new CopyOnWriteArraySet<>();

    public final void addListener(final VoiceCommandListener listener) {
        listeners.add(listener);
    }

    public final void removeListener(final VoiceCommandListener listener) {
        listeners.remove(listener);
    }

    private final void notifyCommandListeners(String cmd) {
        for (VoiceCommandListener listener : listeners) {
            listener.onVoiceCommand(cmd);
        }
    }


    @Override
    public void run() {
        String jarDir = "";
        try {
            jarDir = SpeechRecognizer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            jarDir = (new File(jarDir)).getParentFile().getPath();
            System.out.println(jarDir);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        final String ACOUSTIC_MODEL = jarDir + "/src/main/resources/grammar/en-us/en-us";
        final String DICTIONARY = jarDir + "/src/main/resources/grammar/8949.dic";
        final String KEY_PHRASES = jarDir + "/src/main/resources/grammar/keyphrase-list";

        TargetDataLine recorder = null;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Error! Audio System line not supported.");
        } else {
            //TODO open mixer's getLine()?
            try {
                recorder = AudioSystem.getTargetDataLine(format);
                recorder.open();
                out = new ByteArrayOutputStream();
                int numBytesRead;
                byte[] data = new byte[recorder.getBufferSize() / 5];

                Config c = Decoder.defaultConfig();

                c.setString("-hmm", ACOUSTIC_MODEL);
                c.setString("-dict", DICTIONARY);
                c.setString("-kws", KEY_PHRASES);

                //c.setString("-lm", "6284.lm");
                //c.setString("-rawlogdir", "/home/pi");
                //c.setString("hello.gram", "-jsgf");
                //c.setFloat("-samprate", SAMPLE_RATE);
                //c.setInt("-nfft", 2048);

                Decoder d = new Decoder(c);
                d.setRawdataSize(300000);


                recorder.start();
                System.out.println("After recorder start");

                byte[] b = new byte[4096];

                // Skip the first buffer, usually zeroes
                //recorder.read(b, 0, b.length);
                d.startUtt();
                while ((running)) {
                    int nbytes;
                    short[] s = null;
                    nbytes = recorder.read(b, 0, b.length);

                    ByteBuffer bb = ByteBuffer.wrap(b, 0, nbytes);
                    bb.order(ByteOrder.LITTLE_ENDIAN); // Swap
                    s = new short[nbytes / 2];

                    bb.asShortBuffer().get(s);

                    d.processRaw(s, nbytes / 2, false, false);

                    if (nbytes > 0) {

                        Hypothesis hypothesis = d.hyp();

                        if (hypothesis != null) {
                            String cmd = hypothesis.getHypstr();
                            System.out.println("------------------------------------------------------");
                            System.out.println(cmd);
                            System.out.println("------------------------------------------------------");
                            notifyCommandListeners(cmd);
                            d.endUtt();
                            d.startUtt();
                        }
                    }

                }


            } catch (Exception excp) {
                System.out.println("Error! Could not open Audio System line!");
                excp.printStackTrace();
            }
            recorder.stop();
        }
    }
}



