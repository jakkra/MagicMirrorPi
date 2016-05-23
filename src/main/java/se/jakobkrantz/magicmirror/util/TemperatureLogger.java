package se.jakobkrantz.magicmirror.util;/*
 * Created by jakkra on 2016-02-23.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TemperatureLogger
{
    public static void appendLog(String message){
        try{
            File file =new File("/home/pi/temperature_log.txt");

            //if file doesn't exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWriter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(message);
            bufferWriter.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}