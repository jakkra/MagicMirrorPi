package se.jakobkrantz.magicmirror.speech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Created by jakkra on 2016-06-22.
 */

public class SpeechRecognizerGoogle extends Thread {
    private final Set<VoiceCommandListener> listeners = new CopyOnWriteArraySet<>();
    private boolean isRecognizing = false;

    public final void addListener(final VoiceCommandListener listener) {
        listeners.add(listener);
    }

    public final void removeListener(final VoiceCommandListener listener) {
        listeners.remove(listener);
    }

    private void notifyCommandListeners(String cmd) {
        for (VoiceCommandListener listener : listeners) {
            listener.onVoiceCommand(cmd);
        }
    }

    private void onStopCommandListeners() {
        for (VoiceCommandListener listener : listeners) {
            listener.onStop();
        }
    }
    public boolean isRecognizing(){
        return isRecognizing;
    }

    public void run(){
        try {
            isRecognizing = true;
            final Process p = Runtime.getRuntime().exec("sudo -u pi python speech_streaming.py");
            Thread process = new Thread(() -> {
                String s = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

                System.out.println("Output of the command (if any):\n");
                try {
                    while ((s = in.readLine()) != null) {
                        if(s.contains("transcript:")){
                            System.out.println("Recognizer: " + s);


                            int start = s.indexOf("\"");
                            int end = s.lastIndexOf("\"");
                            String result = s.substring(start + 1, end);
                            result = result.replace("\\", "");
                            result = result.replaceAll("303", "");
                            result = result.replaceAll("244", "ä");
                            result = result.replaceAll("245", "å");
                            result = result.replaceAll("266", "ö");

                            notifyCommandListeners(result);
                        } else if(s.contains("--EXIT--")){
                            onStopCommandListeners();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Process end = p.destroyForcibly();
                try {
                    end.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----STOPPED-----");
                isRecognizing = false;
            });
            process.start();

            /*new Thread(() -> {
                String s = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                System.err.println("Here is the standard error of the command (if any):\n");
                try {
                    while ((s = in.readLine()) != null) {
                        System.out.println(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();*/
            System.out.println("Waiting for process to finish");
            process.join();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
