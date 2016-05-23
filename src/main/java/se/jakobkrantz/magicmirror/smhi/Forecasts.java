package se.jakobkrantz.magicmirror.smhi;/*
 * Created by krantz on 16-04-19.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Forecasts {
    public ArrayList<HourlyForecast> hourlyForecasts;
    public String approvedTime;
    public String referenceTime;

    public Forecasts() {
        this.hourlyForecasts = new ArrayList<>();
    }

    public void addHourlyForecast(HourlyForecast hf) {
        hourlyForecasts.add(hf);
    }

    public HourlyForecast getCurrentWeather() {
        Date now = new Date();
        Date forecastDate;
        HourlyForecast hf;
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");



        for (int i = 0; i < hourlyForecasts.size(); i++) {
            hf = hourlyForecasts.get(i);
            try {
                forecastDate = sb.parse(hf.validDate);
                if (forecastDate.compareTo(now) > 0) {
                    return hourlyForecasts.get(i - 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HourlyForecast h : hourlyForecasts) {
            sb.append("\n" + h.toString());
        }
        return "Forecast: \n" +
                "ApprovedTime: " + approvedTime + "\n" +
                "referenceTime: " + referenceTime + "\n" +
                sb.toString();
    }
}
