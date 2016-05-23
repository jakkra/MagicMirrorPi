package se.jakobkrantz.magicmirror.skanetrafikenAPI;

import java.util.Calendar;
import java.util.Date;

public class Constants {

    public static final String testURL = "http://www.labs.skanetrafiken.se/v2.2/resultspage.asp?cmdaction=next&selPointFr=%7C81116%7C0&selPointTo=%7C65008%7C0&LastStart=2012-10-14%2008:00";
    public static final String baseURL = "http://www.labs.skanetrafiken.se/v2.2/";
    public static final String queryActionNextURL = "resultspage.asp?cmdaction=next&selPointFr=";
    public static final String queryActionPreviousURL = "resultspage.asp?cmdaction=previous&selPointFr=";
    public static final String getStationURL = "querystation.asp?inpPointfr=";
    public static final String pipe = "%7C";
    public static final String space = "%20";
    public static final String midPartURL = "0&selPointTo=";
    public static final String lastPartDateFromURL = "0&LastStart=";
    public static final String lastPartDateToRL = "0&FirstStart=";

    public static final String noOfResults = "&NoOf=";
    public static int nbrResultsToGet = 25;

    /**
     * Build QueryString for getting a route
     * Query string parameters*
     * @param resultKey JourneyKey retrieved in response from resultspage method
     * @param sequenceNbr SequenceNo of journey retrieved in response from resultspage method
     * @return url
     */
    public static String getUrlJourneyPath(String resultKey, String sequenceNbr){
        return baseURL + "journeypath.asp?cf=" + resultKey + "&id=" + sequenceNbr;
    }

    /**
     * Build the Querystringz
     *
     * @param startStationNumber from skånetrafiken
     * @param endStationNumber   from skånetrafiken
     * @param date               for first start from in form yyyy-mm-dd
     * @param time               in format hh:mm 24 H
     * @param nbrResults         max 20
     */
    public static String getURL(int startStationNumber, int endStationNumber, String date, String time, int nbrResults) {
        String nbrRes = String.valueOf(nbrResults);
        String url = baseURL + queryActionNextURL + pipe + startStationNumber + pipe + midPartURL + pipe + endStationNumber + pipe + lastPartDateFromURL + date + space + time + noOfResults + nbrRes;
        return url;
    }

    /**
     * Build the Querystringz
     *
     * @param startStationNumber from skånetrafiken
     * @param endStationNumber   from skånetrafiken
     * @param date               last result will be just before this date in form yyyy-mm-dd
     * @param time               in format hh:mm 24 H
     * @param nbrResults         max 20
     */
    public static String getURLPreviousResults(int startStationNumber, int endStationNumber, String date, String time, int nbrResults) {
        String nbrRes = String.valueOf(nbrResults);
        String url = baseURL + queryActionPreviousURL + pipe + startStationNumber + pipe + midPartURL + pipe + endStationNumber + pipe + lastPartDateToRL + date + space + time + noOfResults + nbrRes;
        return url;
    }


    /**
     * Build the Querystringz
     *
     * @param startStationNumber from skånetrafiken
     * @param endStationNumber   from skånetrafiken
     * @param nbrResults         max 20 from now
     */
    public static String getURL(int startStationNumber, int endStationNumber, int nbrResults) {
        String nbrRes = String.valueOf(nbrResults);
        String url = baseURL + queryActionNextURL + pipe + startStationNumber + pipe + midPartURL + pipe + endStationNumber + pipe + "0" + noOfResults + nbrRes;
        return url;
    }

    /**
     * Build the QueryString to search for stations
     *
     * @param search search for stations with {@search} in them.
     * @return url to use with Skånetrafiken api
     */
    public static String getSearchStationURL(String search) {
        String url = baseURL + getStationURL + search;
        return url.replace(" ", space);
    }

    public static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        return year + "-" + month + "-" + day;
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        String hour;
        if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
            hour = "0" + String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        } else {
            hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        }
        String minute;
        if (cal.get(Calendar.MINUTE) < 10) {
            minute = "0" + String.valueOf(cal.get(Calendar.MINUTE));
        } else {
            minute = String.valueOf(cal.get(Calendar.MINUTE));
        }
        return hour + ":" + minute;
    }
}
