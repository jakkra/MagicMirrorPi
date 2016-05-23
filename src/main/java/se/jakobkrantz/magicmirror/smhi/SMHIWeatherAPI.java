package se.jakobkrantz.magicmirror.smhi;/*
 * Created by krantz on 16-04-19.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class SMHIWeatherAPI {
    private final String longitude;
    private final String latitude;
    private Forecasts forecasts;

    public SMHIWeatherAPI(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Forecasts getForecasts() {
        ObjectMapper mapper = new ObjectMapper();
        JsonWeatherAsObject jsonObject = null;
        try {
            String url = "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/" + longitude + "/lat/" + latitude + "/data.json";
            System.out.println(url);
            jsonObject = mapper.readValue(new URL(url), JsonWeatherAsObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        forecasts =  mapObjectToForecast(jsonObject);
        return forecasts;
    }




    private Forecasts mapObjectToForecast(JsonWeatherAsObject json) {
        if (json == null) {
            return null;
        }
        Forecasts f = new Forecasts();
        f.approvedTime = json.getApprovedTime();
        f.referenceTime = json.getReferenceTime();
        HourlyForecast hf;
        for (JsonWeatherAsObject.TimeSeries t : json.getTimeSeries()) {
            hf = new HourlyForecast();
            hf.validDate = t.getValidTime();
            for (JsonWeatherAsObject.Parameter p : t.getParameters()) {
                double value = ((p.getValues().size() > 0) ? p.getValues().get(0) : 0);

                switch (p.getName()) {
                    case "msl":
                        hf.pressure = value;
                        break;
                    case "t":
                        hf.temp = value;
                        break;
                    case "vis":
                        hf.visability = value;
                        break;
                    case "wd":
                        hf.windDirection = value;
                        break;
                    case "ws":
                        hf.windVelocity = value;
                        break;
                    case "r":
                        hf.relativeHumidity = value;
                        break;
                    case "tstm":
                        hf.probabilityThunderstorm = value;
                        break;
                    case "tcc_mean":
                        hf.totalCloudCover = value;
                        break;
                    case "lcc_mean":
                        hf.lowCloudCover = value;
                        break;
                    case "mcc_mean":
                        hf.mediumCloudCover = value;
                        break;
                    case "hcc_mean":
                        hf.highCloudCover = value;
                        break;
                    case "gust":
                        hf.windGust = value;
                        break;
                    case "pmin":
                        hf.minPrecipitation = value;
                        break;
                    case "pmax":
                        hf.maxPrecipitation = value;
                        break;
                    case "spp":
                        hf.frozenPartOfTotalPrecipitation = value;
                        break;
                    case "pcat":
                        hf.rainfallType = value;
                        break;
                    case "pmean":
                        hf.rainfallMeanAmount = value;
                        break;
                    case "pmedian":
                        hf.rainfallMedianAmount = value;
                        break;
                    case "Wsymb":
                        hf.weatherSymbol = (int) value;
                        break;
                    default:
                        System.out.println("Did not find any match for valueName: " + p.getName());
                        break;
                }
            }
            f.addHourlyForecast(hf);
        }
        return f;
    }
}
