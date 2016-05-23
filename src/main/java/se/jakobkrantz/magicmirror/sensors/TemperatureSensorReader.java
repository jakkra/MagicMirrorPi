package se.jakobkrantz.magicmirror.sensors;
/*
 * Created by krantz on 16-01-04.
 */

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

import java.util.ArrayList;
import java.util.List;


public class TemperatureSensorReader {

    //Possible to connect multiple temperature sensors with OneWire, in that case this list will contain more than one temperature
    private List<Double> readAllTemperatureSensors() {
        List<Double> temps = new ArrayList<>();
        W1Master w1Master = new W1Master();
        for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
            temps.add(device.getTemperature(TemperatureScale.CELSIUS));
            System.out.println(device.getTemperature());
        }
        return temps;
    }

    public double readTemperatureFirstSensor(){
        return readAllTemperatureSensors().get(0);
    }
}
