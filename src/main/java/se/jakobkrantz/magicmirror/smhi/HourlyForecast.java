package se.jakobkrantz.magicmirror.smhi;/*
 * Created by krantz on 16-04-19.
 */

public class HourlyForecast {
    String validDate;
    double pressure;
    double temp;
    double visability;
    double windDirection;
    double windVelocity;
    double relativeHumidity;
    double probabilityThunderstorm;
    double totalCloudCover;
    double lowCloudCover;
    double mediumCloudCover;
    double highCloudCover;
    double windGust;
    double minPrecipitation;
    double maxPrecipitation;
    /* Category of rainFall, 0 no, 1 snow, 2 snow and rain, 3 rain, 4 drizzle, 5, freezing rain, 6 freezing drizzle */
    double rainfallType;
    double rainfallMeanAmount; //type: mm/h
    double rainfallMedianAmount; //type mm/h
    public int weatherSymbol; //symbol 0-15



    /* Heltal, 0-100 eller -9. Om värdet är -9, så är parametern inte relevant. */
    double frozenPartOfTotalPrecipitation;

    public String getValidDate() {
        return validDate;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemp() {
        return temp;
    }

    public double getVisability() {
        return visability;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getWindVelocity() {
        return windVelocity;
    }

    public double getRelativeHumidity() {
        return relativeHumidity;
    }

    public double getProbabilityThunderstorm() {
        return probabilityThunderstorm;
    }

    public double getTotalCloudCover() {
        return totalCloudCover;
    }

    public double getLowCloudCover() {
        return lowCloudCover;
    }

    public double getMediumCloudCover() {
        return mediumCloudCover;
    }

    public double getHighCloudCover() {
        return highCloudCover;
    }

    public double getWindGust() {
        return windGust;
    }

    public double getMinPrecipitation() {
        return minPrecipitation;
    }

    public double getMaxPrecipitation() {
        return maxPrecipitation;
    }

    public double getFrozenPartOfTotalPrecipitation() {
        return frozenPartOfTotalPrecipitation;
    }

    public double getRainfallType() {
        return rainfallType;
    }

    public double getRainfallMeanAmount() {
        return rainfallMeanAmount;
    }

    public double getRainfallMedianAmount() {
        return rainfallMedianAmount;
    }

    public int getWeatherSymbol() {
        return weatherSymbol;
    }



    @Override
    public String toString() {
        return "ValidDate: " + validDate + "\n" +
                "Pressure: " + pressure + "\n" +
                "Temp: " + temp + "\n" +
                "visability: " + visability + "\n" +
                "windDirection: " + windDirection + "\n" +
                "windVelocity: " + windVelocity + "\n" +
                "relativeHumidity: " + relativeHumidity + "\n" +
                "probabilityThunderstrom: " + probabilityThunderstorm + "\n" +
                "totalCloudCover: " + totalCloudCover + "\n" +
                "windGust: " + windGust + "\n" +
                "minRainfall: " + minPrecipitation + "\n" +
                "maxRainfall: " + maxPrecipitation + "\n" +
                "frozenPartOfRain: " + frozenPartOfTotalPrecipitation + "\n" +
                "rainfallType: " + rainfallType + "\n" +
                "rainfallMeanAmount: " + rainfallMeanAmount + "\n" +
                "rainfallMedianAmount: " + rainfallMedianAmount + "\n" +
                "weathersymbol: " + weatherSymbol + "\n";


    }
}
