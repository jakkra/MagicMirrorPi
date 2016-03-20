package se.jakobkrantz.magic.sensors;
/*
 * Created by krantz on 16-01-03.
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class PirMotionDetector {
    private GpioPinDigitalInput sensor;
    private MotionDetectionListener motionDetectionListener;

    public void startDetecting() {
        // create gpio controller
        final GpioController gpioSensor = GpioFactory.getInstance();
        sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);


        // create and register gpio pin listener
        sensor.addListener((GpioPinListenerDigital) event -> {
            if (event.getState().isHigh()) {
                if (motionDetectionListener != null) {
                    motionDetectionListener.onMotionDetected();
                }
            }

            if (event.getState().isLow()) {
                motionDetectionListener.onMotionStopped();
            }

        });

        try {
            // keep program running until user aborts
            for (; ; ) {
                Thread.sleep(500);
            }
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void setMotionDetectionListener(MotionDetectionListener listener) {
        this.motionDetectionListener = listener;
    }

    public interface MotionDetectionListener {
        public void onMotionDetected();

        public void onMotionStopped();
    }
}


