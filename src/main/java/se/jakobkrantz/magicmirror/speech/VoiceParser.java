package se.jakobkrantz.magicmirror.speech;

/**
 * Created by jakkra on 2016-05-27.
 */

public class VoiceParser {

    public enum SpeechCommand {
        LIGHTS_ON,
        LIGHTS_OFF,
        LIGHTS_ON_BEDROOM,
        LIGHTS_ON_HALLWAY,
        LIGHTS_ON_ALL,
        LIGHTS_OFF_BEDROOM,
        LIGHTS_OFF_HALLWAY,
        LIGHTS_OFF_ALL,
        LIGHTS_CHANGE_ALL,
        LIGHTS_CHANGE_BEDROOM,
        LIGHTS_CHANGE_HALLWAY,
        HIDE_NEWS,
        SHOW_NEWS,
        HIDE_FORECASTS,
        SHOW_FORECASTS,
        CHANGE_NEWS_SOURCE,
        NEXT_BUS,
        UNKNOWN,
    }

    public enum Room {
        ALL,
        BEDROOM,
        HALLWAY,
        UNKNOWN
    }

    private String[] lampSynonymsSwedish = {"lampa", "lampan", "lampor", "lamporna", "ljus", "ljuset", "ljusen", "ljuset", "lyset", "taket", "i taket"};
    private String[] onSynonymsSwedish = {"sätt på", "till", "tänd", "tända", "starta"};
    private String[] offSynonymsSwedish = {"stäng av", "från", "släck", "släcka", "stoppa"};
    private String[] allSynonymsSwedish = {"alla", "samtliga"};
    private String[] bedroomSynonymsSwedish = {"säng", "sängen", "sängens", "sovrum", "sovrums", "sovrummet", "sovrummets"};
    private String[] hallwaySynonymsSwedish = {"hall", "hallen", "hallens", "dörr", "dörren"};
    private String[] busSynonymsSwedish = {"bussen", "buss", "bus"};


    private String[] changeSynonymsSwedish = {"ändra", "byt", "ändra till"};
    private String[] showSynonymsSwedish = {"visa", "starta", "ta fram"};
    private String[] hideSynonymsSwedish = {"dölj", "stäng av", "ta bort", "göm"};
    private String[] newsSynonymsSwedish = {"nyheter", "nyheterna", "artiklar", "artiklarna", "nyhetskälla"};
    private String[] forecastsSynonymsSwedish = {"väder", "vädret", "prognos", "prognoserna"};
    private String[] whenSynonymsSwedish = {"när", "byt", "går"};




    public SpeechCommand parseText(String s) {
        if (stringContainsItemFromList(s, lampSynonymsSwedish)) {
            return parseLights(s);
        } else if (stringContainsItemFromList(s, forecastsSynonymsSwedish)) {
            return parseForecasts(s);
        } else if (stringContainsItemFromList(s, newsSynonymsSwedish)) {
            return parseNews(s);
        } else if(stringContainsItemFromList(s, busSynonymsSwedish)){
            return parseBus(s);
        }
        return SpeechCommand.UNKNOWN;
    }

    private SpeechCommand parseBus(String s) {
        if (stringContainsItemFromList(s, whenSynonymsSwedish)) {
            return SpeechCommand.NEXT_BUS;
        } else {
            return SpeechCommand.UNKNOWN;
        }
    }

    private SpeechCommand parseNews(String s) {
        if (stringContainsItemFromList(s, showSynonymsSwedish)) {
            return SpeechCommand.SHOW_NEWS;
        } else if (stringContainsItemFromList(s, hideSynonymsSwedish)) {
            return SpeechCommand.HIDE_NEWS;
        } else if(stringContainsItemFromList(s, changeSynonymsSwedish)){
            return SpeechCommand.CHANGE_NEWS_SOURCE;
        } else {
            return SpeechCommand.UNKNOWN;
        }
    }

    private SpeechCommand parseForecasts(String s) {
        if (stringContainsItemFromList(s, showSynonymsSwedish)) {
            return SpeechCommand.SHOW_FORECASTS;
        } else if (stringContainsItemFromList(s, hideSynonymsSwedish)) {
            return SpeechCommand.HIDE_FORECASTS;
        } else {
            return SpeechCommand.UNKNOWN;
        }
    }

    private SpeechCommand parseLights(String s) {
        if (stringContainsItemFromList(s, onSynonymsSwedish)) {
            return parseLightsOn(s);
        } else if (stringContainsItemFromList(s, offSynonymsSwedish)) {
            return parseLightsOff(s);
        } else if (stringContainsItemFromList(s, changeSynonymsSwedish)) {
            return parseLightChange(s);
        } else {
            return SpeechCommand.UNKNOWN;
        }
    }

    private SpeechCommand parseLightChange(String s) {
        switch (checkWhichRoom(s)) {
            case ALL:
                return SpeechCommand.LIGHTS_CHANGE_ALL;
            case BEDROOM:
                return SpeechCommand.LIGHTS_CHANGE_BEDROOM;
            case HALLWAY:
                return SpeechCommand.LIGHTS_CHANGE_HALLWAY;
            default:
                return SpeechCommand.UNKNOWN;
        }
    }

    private SpeechCommand parseLightsOff(String s) {
        switch (checkWhichRoom(s)) {
            case ALL:
                return SpeechCommand.LIGHTS_OFF_ALL;
            case BEDROOM:
                return SpeechCommand.LIGHTS_OFF_BEDROOM;
            case HALLWAY:
                return SpeechCommand.LIGHTS_OFF_HALLWAY;
            default:
                return SpeechCommand.LIGHTS_OFF;
        }
    }

    private SpeechCommand parseLightsOn(String s) {
        switch (checkWhichRoom(s)) {
            case ALL:
                return SpeechCommand.LIGHTS_ON_ALL;
            case BEDROOM:
                return SpeechCommand.LIGHTS_ON_BEDROOM;
            case HALLWAY:
                return SpeechCommand.LIGHTS_ON_HALLWAY;
            default:
                return SpeechCommand.LIGHTS_ON;
        }
    }

    private boolean stringContainsItemFromList(String inputString, String[] items) {
        inputString = inputString.toLowerCase();
        for (int i = 0; i < items.length; i++) {
            if (inputString.contains(items[i])) {
                return true;
            }
        }
        return false;
    }


    private Room checkWhichRoom(String s) {
        if (stringContainsItemFromList(s, bedroomSynonymsSwedish)) {
            return Room.BEDROOM;
        } else if (stringContainsItemFromList(s, hallwaySynonymsSwedish)) {
            return Room.HALLWAY;
        } else if (stringContainsItemFromList(s, allSynonymsSwedish)) {
            return Room.ALL;
        } else {
            return Room.UNKNOWN;
        }
    }

    private String[] concatArrays(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c = new String[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}