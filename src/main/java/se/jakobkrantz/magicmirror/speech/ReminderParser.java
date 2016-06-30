package se.jakobkrantz.magicmirror.speech;

import se.jakobkrantz.magicmirror.remindersAPI.NodeReminder;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakkra on 2016-06-27.
 */

public class ReminderParser {
    private NodeReminder reminder;

    public ReminderParser() {
        reminder = new NodeReminder();
    }

    public VoiceParser.SpeechCommand parse(String s) {
        System.out.println("Parsing: " + s);
        String reminderText;

        Pattern time1 = Pattern.compile("om\\s+([^\\s]*)\\s*(.*?)\\s+att\\s+(.*)");
        Pattern time2 = Pattern.compile("att\\s+(.+)\\s+om\\s+([^\\s]+)\\s+([^\\s]+)");
        Pattern tomorrow = Pattern.compile("imorgon\\s+(?:klockan)?\\s*([0-9]+)(?:\\.)?([0-9]+)?\\s+att\\s+(.*)");
        Pattern weekday = Pattern.compile("på\\s+([^\\s]*)\\s+att\\s+(.*)");

        Matcher matcher1 = time1.matcher(s);
        Matcher matcher2 = time2.matcher(s);
        Matcher tomorrowMatcher = tomorrow.matcher(s);
        Matcher weekdayMatcher = weekday.matcher(s);
        Calendar cal = null;

        if (matcher1.find()) {
            try {
                System.out.println(matcher1.group(1));
                System.out.println(matcher1.group(2));

                cal = increaseTodayDate(matcher1.group(1), matcher1.group(2));
                reminderText = matcher1.group(3);
            } catch (Exception e) {
                e.printStackTrace();
                return VoiceParser.SpeechCommand.UNKNOWN;
            }
        } else if (matcher2.find()) {
            try {
                reminderText = matcher2.group(1);
                System.out.println(reminderText);
                cal = increaseTodayDate(matcher2.group(2), matcher2.group(3));
            } catch (Exception e) {
                e.printStackTrace();
                return VoiceParser.SpeechCommand.UNKNOWN;
            }
        } else if (tomorrowMatcher.find()) {
            try {
                String hour = tomorrowMatcher.group(1);
                String min = tomorrowMatcher.group(2);
                cal = getTomorrowAt(hour, min);
                reminderText = tomorrowMatcher.group(3);
            } catch (Exception e) {
                System.out.println(e);
                return VoiceParser.SpeechCommand.UNKNOWN;
            }
        } else if (weekdayMatcher.find()) {
            try {
                String day = weekdayMatcher.group(1);
                cal = getFutureDay(day);
                reminderText = weekdayMatcher.group(2);
            } catch (Exception e) {
                e.printStackTrace();
                return VoiceParser.SpeechCommand.UNKNOWN;
            }
        } else {
            return VoiceParser.SpeechCommand.UNKNOWN;
        }

        if (cal != null && reminderText != null) {
            Date startDate = cal.getTime();
            System.out.println("Reminder start: " + startDate + "\nReminder text: " + reminderText);
            try {
                reminder.postReminder(reminderText, startDate, "ijakkra@gmail.com");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return VoiceParser.SpeechCommand.CREATE_REMINDER;
        } else {
            return VoiceParser.SpeechCommand.UNKNOWN;
        }
    }

    private Calendar increaseTodayDate(String num, String unit) throws Exception {
        Calendar c = Calendar.getInstance();
        int calUnit = getCalUnit(unit);
        int numUnits = wordToInt(num);
        if (calUnit != -1 && numUnits != -1) {
            c.add(calUnit, numUnits);
            return c;
        } else {
            throw new Exception("Parse error near " + num + " " + unit);
        }
    }

    private int wordToInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (s) {
            case "en":
                return 1;
            case "två":
                return 2;
            case "tre":
                return 3;
            case "fyra":
                return 4;
            case "fem":
                return 5;
            case "sex":
                return 6;
            case "sju":
                return 7;
            case "åtta":
                return 8;
            case "nio":
                return 9;
            case "tio":
                return 10;
            case "elva":
                return 11;
            case "tolv":
                return 12;
            default:
                return -1;
        }
    }


    private Calendar getTomorrowAt(String hour, String minute) throws Exception {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        c.set(Calendar.MINUTE, Integer.parseInt(minute));
        return c;
    }

    private Calendar getFutureDay(String day) throws Exception {
        Calendar c = Calendar.getInstance();
        int dayNum = c.get(Calendar.DAY_OF_WEEK);
        int goalDayNum = getCalId(day);
        if (goalDayNum - dayNum > 1) {
            c.add(Calendar.DATE, goalDayNum - dayNum);
        } else {
            c.add(Calendar.DATE, 7 + goalDayNum - dayNum);
        }
        return c;
    }

    private int getCalId(String day) throws Exception {
        switch (day) {
            case "söndag":
                return 1;
            case "måndag":
                return 2;
            case "tisdag":
                return 3;
            case "onsdag":
                return 4;
            case "torsdag":
                return 5;
            case "fredag":
                return 6;
            case "lördag":
                return 7;
            default:
                throw new Exception("Parse error on day: " + day);
        }
    }

    private int getCalUnit(String unit) {
        switch (unit) {
            case "minuter":
                return Calendar.MINUTE;
            case "minut":
                return Calendar.MINUTE;
            case "timme":
                return Calendar.HOUR_OF_DAY;
            case "timmar":
                return Calendar.HOUR_OF_DAY;
            case "dag":
                return Calendar.DATE;
            case "dagar":
                return Calendar.DATE;
            default:
                return -1;
        }
    }
}
