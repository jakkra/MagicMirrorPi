package se.jakobkrantz.magicmirror.smhi;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jakkra on 2016-05-22.
 */

/*
    http://graphical.weather.gov/definitions/defineApparentT.html
    http://www.wpc.ncep.noaa.gov/html/heatindex_equation.shtml
    Where Tf = air temperature in degrees Fahrenheit,
    RH= relative humidity expressed as a whole number
 */
public class WeatherUtils {
    private static DecimalFormat df = new DecimalFormat("###.#");


    public static String getWindChill(double ms, double tempC) {
        if (tempC > 10) {
            return null;
        }

        double T = tempC * ((double) 9 / (double) 5) + 32;
        double V = ms / 0.44704;

        double windChillF = 35.74 + 0.6215 * T - Math.pow(35.75 * V, 0.16) + Math.pow(0.4275 * T * V, 0.16);
        double windChillC = (windChillF - 32) * ((double) 5 / (double) 9);
        return df.format(windChillC);

    }

    public static String getHeatIndex(double tempC, double RH) {
        double T = tempC * ((double) 9 / (double) 5) + 32;

        double feelsLikeF = -42.379 + 2.04901523 * T + 10.14333127 * RH - 0.22475541 * T * RH - 0.00683783 * T * T -
                0.05481717 * RH * RH + 0.00122874 * T * T * RH + 0.00085282 * T * RH * RH - 0.00000199 * T * T * RH * RH;
        if (RH > 13 && T >= 80 && T <= 112) {
            feelsLikeF -= ((13 - RH) / 4) * Math.sqrt((17 - Math.abs(T - 95.)) / 17);
        } else if (RH > 85 && T >= 80 && T <= 87) {
            feelsLikeF -= ((RH - 85) / 10) * ((87 - T) / 5);
        }
        double feelsLikeC = (feelsLikeF - 32) * ((double) 5 / (double) 9);

        return df.format(feelsLikeC);
    }

    /**
     * Maps the SMHI! Weather codes to the weather icons font characters
     * http://opendata.smhi.se/apidocs/metfcst/parameters.html#parameter-wsymb
     *
     * Icons from http://fa2png.io/r/weather-icons/
     */
    public static String fileFromInt(int smhiCode) {
        return smhiCode + "_" + convertCodeDependingOnTime(smhiCode) + ".png";
    }

    private static String convertCodeDependingOnTime(int code) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 7 && hour < 18) { //Day
            return "day";
        } else { //Night
            return "night";
        }
    }
}
