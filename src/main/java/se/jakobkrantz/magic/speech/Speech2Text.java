package se.jakobkrantz.magic.speech;
/*
 * Created by jakkra on 2016-02-08.
 */

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import edu.cmu.pocketsphinx.Config;
import edu.cmu.pocketsphinx.Decoder;
import edu.cmu.pocketsphinx.Segment;

public class Speech2Text {

    static {
        System.loadLibrary("pocketsphinx_jni");
    }

    public static Capture capture = new Capture();

    static String errStr;

    private static Decoder d;

    public static void main(String[] args) {
        capture.start();
    }

    /**
     * Reads data from the input channel and writes to the output stream
     */
    public static class Capture implements Runnable {

        private Thread thread;

        private AudioInputStream ais;

        private TargetDataLine line;

        public void start() {
            thread = new Thread(this);
            thread.start();
        }

        public void stop() {
            thread = null;
        }

        public void run() {
            System.out.println("In start run");

            ais = null;

            /**
             * Defines an audio format
             **/
            AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
            float rate = 48000.0f;
            int sampleSizeInBits = 16;
            int channels = 1;
            boolean bigEndian = false;
            AudioFormat format = new AudioFormat(encoding, rate, sampleSizeInBits,
                    channels, (sampleSizeInBits / 8)
                    * channels, rate, bigEndian);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line matching " + info + " not supported.");
                return;
            }
            System.out.println("Works");

            try {
                line = (TargetDataLine) AudioSystem.getLine(info);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            try {
                line.open(format, line.getBufferSize());
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }


            ais = new AudioInputStream(line);
            line.addLineListener(new LineListener() {

                @SuppressWarnings("static-access")
                @Override
                public void update(LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.START)) {
                        System.out.println("Line started!");
                    }
                    if (event.getType().equals(LineEvent.Type.STOP)) {
                        System.out.println("Line stopped!");
                    }
                    if (event.getType().equals(LineEvent.Type.OPEN)) {
                        System.out.println("Line opened!");
                    }
                    if (event.getType().equals(LineEvent.Type.CLOSE)) {
                        System.out.println("Line closed!");
                    }
                }
            });
            //line.start();


            while (thread == null) {

            }

            // stop and close the line.
            /*line.stop();
            line.close();
            line = null;*/

            Config config = Decoder.defaultConfig();


            config.setString("-hmm", "../../model/en-us/en-us");
            config.setString("-lm", "4802.lm");
            config.setString("-dict", "4802.dic");
            Decoder d = new Decoder(config);

            d.startUtt();

            d.setRawdataSize(300000);
            byte[] b = new byte[4096];
            try {
                int nbytes;
                while ((nbytes = ais.read(b)) >= 0) {
                    ByteBuffer bb = ByteBuffer.wrap(b, 0, nbytes);
                    short[] s = new short[nbytes / 2];
                    bb.asShortBuffer().get(s);
                    d.processRaw(s, nbytes / 2, false, false);
                }
            } catch (IOException e) {
                System.out.println("Error when reading AudioInputStream: " + e.getMessage());
            }
            d.endUtt();

            for (Segment seg : d.seg()) {
                if (!seg.getWord().equals("<s>")
                        && !seg.getWord().equals("</s>")
                        && !seg.getWord().equals("<sil>")) {
                    System.out.println(seg.getWord().toUpperCase() + "");
                    System.out.println(seg.getWord().toUpperCase());
                }
            }

        }
    } // end Capture class.
} // end Speech2Text Class.