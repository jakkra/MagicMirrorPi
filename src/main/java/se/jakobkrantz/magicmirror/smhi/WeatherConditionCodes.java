package se.jakobkrantz.magicmirror.smhi;
/*
 * Created by krantz on 16-05-17.
 */

import java.util.Calendar;
import java.util.Date;

/**
 * Maps the SMHI! Weather codes to the weather icons font characters
 * http://opendata.smhi.se/apidocs/metfcst/parameters.html#parameter-wsymb
 */
public enum WeatherConditionCodes {
    SHOWERS_NIGHT(38, 0xf01a),
    SHOWERS_DAY(8, 0xf01a),//Rain showers

    SHOWERS_NIGHT1(42, 0xf01a),
    SHOWERS_DAY1(12, 0xf01a),//Rain showers


    SLEET_NIGHT(40, 0xf0b5),//Light sleet
    SLEET_DAY(10, 0xf0b5),//Light sleet

    SLEET_NIGHT1(44, 0xf0b5),//Light sleet
    SLEET_DAY1(14, 0xf0b5),//Light sleet


    FOGGY_NIGHT(37, 0xf014),//Fog
    FOGGY(7, 0xf014),//Fog

    CLOUDY_NIGHT(36, 0xf013),
    CLOUDY(6, 0xf013), //Overcast

    MOSTLY_CLOUDY_NIGHT(35, 0xf086),
    MOSTLY_CLOUDY_DAY(5, 0xf002),//Cloudy sky

    PARTLY_CLOUDY_NIGHT(33, 0xf083),//
    PARTLY_CLOUDY_DAY(3, 0xf00c),//Variable cloudiness

    VARIABLE_CLOUDINESS_NIGHT(34, 0xf083),//
    VARIABLE_CLOUDINESS_DAY(4, 0xf00c),//Halfclear sky

    CLEAR_NIGHT(31, 0xf02e),//
    SUNNY(1, 0xf00d),//Clear sky

    FAIR_NIGHT(32, 0xf083),//
    FAIR_DAY(2, 0xf00c),//Nearly clear sky

    ISOLATED_THUNDERSTORMS_NIGHT(43, 0xf01e),//Thunder
    ISOLATED_THUNDERSTORMS_DAY(13, 0xf01e),//Thunder

    SNOW_NIGHT(45, 0xf01b),//Snowfall
    SNOW_DAY(15, 0xf01b),//Snowfall

    THUNDERSHOWERS_NIGHT(39, 0xf01e), //Thunderstorm
    THUNDERSHOWERS_DAY(9, 0xf01e), //Thunderstorm

    SNOW_SHOWERS_DAY(41, 0xf01b),//	Snow showers
    SNOW_SHOWERS_NIGHT(11, 0xf01b); //Snow showers

    int code;
    int character;

    WeatherConditionCodes(int code, int character) {
        this.code = code;
        this.character = character;
    }

    public static WeatherConditionCodes fromInt(int smhiCode) {
        int code = convertCodeDependingOnTime(smhiCode);

        for (WeatherConditionCodes w : WeatherConditionCodes.values()) {
            if (w.code == code) {
                return w;
            }
        }

        return null;
    }

    private static int convertCodeDependingOnTime(int code) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 7 && hour < 18) { //Day
            return code;
        } else { //Night
            return code + 30; //Night symbols will be 30 higher
        }
    }

    public String toString() {
        return Character.toString((char) character);
    }
}
