package se.jakobkrantz.magicmirror.skanetrafikenAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeAndDateConverter {

    /**
     * Extracts hour and minutes from a dateDimestring
     *
     * @param String in format 2012-10-15T08:17:00
     * @return String in format HH:MM
     */
    public static String formatTime(String dateTimeString) {
        String formattedTime = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
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

        formattedTime = formattedTime + " " + hour + ":" + minute;
        return hour + ":" + minute;
    }

    /**
     * Adds minutes to a time, represented by a String
     *
     * @param String in format 2012-10-15T08:17:00
     * @return String in format 2012-10-15T08:17:00 with minutes appended
     */
    public static String appendMinsTodate(String dateTimeString, int minutes) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        date = cal.getTime();
        return dateFormat.format(date);
    }

    /**
     * Extracts date and month from dateTimeString and formats
     *
     * @param dateTimeString in format 2012-10-15T08:17:00
     * @return format dd/MM
     */
    public static String formatDate(String dateTimeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        return day + "/" + month;
    }

    /**
     * Converts dates between formats
     *
     * @param dateTimeString format 2012-10-15T08:17:00
     * @return String in format YYYY-MM-DD
     */
    public static String getDate(String dateTimeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        return year + "-" + month + "-" + day;
    }


    /**
     * Calculates difference in minutes between two datetimes
     *
     * @param startTime in format 2012-10-15T08:17:00
     * @param endTime   in format 2012-10-15T08:17:00
     * @return minutes difference between startTime and endTime
     */
    public static String getTravelTimeinMinutes(String startTime, String endTime) {
        int diffMinutes = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = dateFormat.parse(startTime);
            endDate = dateFormat.parse(endTime);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startDate);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(endDate);
            long millisTravel = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
            diffMinutes = ((int) millisTravel / (60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.valueOf(diffMinutes);
    }

    /**
     * Calculates minutes from now to a specific time in the future
     *
     * @param startTime startTime in format 2012-10-15T08:17:00
     * @return formatted String dd:HH:mm, if @param startTime is passed, '-' is returned.
     */
    public static String timeToDeparture(String startTime) {
        double diffMinutes, hours, days;

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long millisDiff = cal.getTimeInMillis() - now.getTimeInMillis();
        if (millisDiff > 0) {
            diffMinutes = ((double) millisDiff / (60 * 1000));
            diffMinutes++;
            if (diffMinutes > 120) {
                hours = diffMinutes / 60;
                diffMinutes = diffMinutes % 60;

                if (hours > 48) {
                    days = hours / 24;
                    hours = hours % 24;
                    if (hours < 10) {
                        if (diffMinutes < 10) {
                            return (int) days + ":0" + (int) hours + ":0" + (int) diffMinutes;

                        } else {
                            return (int) days + ":0" + (int) hours + ":" + (int) diffMinutes;
                        }
                    } else {
                        if (diffMinutes < 10) {
                            return (int) days + ":" + (int) hours + ":0" + (int) diffMinutes;

                        } else {
                            return (int) days + ":" + (int) hours + ":" + (int) diffMinutes;
                        }

                    }
                } else {
                    if (hours < 10) {
                        if (diffMinutes < 10) {
                            return "0" + (int) hours + ":0" + (int) diffMinutes;

                        } else {
                            return "0" + (int) hours + ":" + (int) diffMinutes;
                        }
                    } else {
                        if (diffMinutes < 10) {
                            return (int) hours + ":0" + (int) diffMinutes;

                        } else {
                            return (int) hours + ":" + (int) diffMinutes;
                        }
                    }
                }

            }
            return String.valueOf((int) diffMinutes) + " min";
        } else {
            return "-";
        }
    }
    
    /**
     * Converts a dateTimeString and makes a Calendar object
     *
     * @param dateTimeString in format 2012-10-15T08:17:00
     * @return Calendar object
     */
    public static Calendar parseCalendarString(String dateTimeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
        cal.setTime(date);
        return cal;
    }

    /**
     * Returns now
     *
     * @return Calendar object
     */
    public static Calendar getTimeNow() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now;
    }

    /**
     * Is a dateTime today
     *
     * @param dateTimeString in format 2012-10-15T08:17:00
     * @return true if today
     */
    public static boolean isToday(String depDateTime) {
        boolean isToday = false;
        Calendar depDate = parseCalendarString(depDateTime);
        int depDay = depDate.get(Calendar.DAY_OF_MONTH);
        Calendar now = getTimeNow();
        int nowDay = now.get(Calendar.DAY_OF_MONTH);
        if (depDay == nowDay) {
            isToday = true;
        }
        return isToday;
    }

    /**
     * Is a dateTime tomorrow
     *
     * @param dateTimeString in format 2012-10-15T08:17:00
     * @return true if tomorrow
     */
    public static boolean isTomorrow(String depDateTime) {
        boolean isTomorrow = false;
        Calendar depDate = parseCalendarString(depDateTime);
        int depDay = depDate.get(Calendar.DAY_OF_YEAR);
        Calendar now = getTimeNow();
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        if ((depDay - nowDay) == 1) {
            isTomorrow = true;
        }
        return isTomorrow;
    }

    /**
     * Is a dateTime after tomorrow
     *
     * @param dateTimeString in format 2012-10-15T08:17:00
     * @return true if not today or tomorrow
     */
    public static String isAfterTomorrow(String dateTime) {
        Calendar depDate = parseCalendarString(dateTime);
        int month = depDate.get(Calendar.MONTH) + 1;
        int day = depDate.get(Calendar.DAY_OF_MONTH);
        return day + "/" + month;
    }
}
