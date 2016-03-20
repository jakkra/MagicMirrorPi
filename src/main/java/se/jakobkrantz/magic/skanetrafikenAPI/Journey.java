package se.jakobkrantz.magic.skanetrafikenAPI;/*
 * Created by krantz on 14-11-19.
 */


import java.util.ArrayList;
import java.util.List;

public class Journey {
    private String sequenceNbr; // Position in an ordered list of Journeys
    private String depDateTime; // Departure date and time
    private String arrDateTime; // Arrival date and time
    private String depWalkDist; // Walk distance in m. between starting point for journey (if address or POI) and Stop Area for departure
    private String arrWalkDist; // Walk distance in m. between Stop Area for arrival and journey end point (if address or POI)
    private String nbrOfChanges; // No of change
    private String guaranteed; // Denotes if journey is guaranteed by transport authority, according to rules for "Travel Guarantee"
    private String CO2Factor; // Journeys impact on the environment - environmental index based on the carbon dioxide (CO2) emissions, Values between 0(lowest impact) and 100
    private String nbrOfZones; // No of passing zones in a zoned fare structure defined by transport authority
    private String journeyKey; // Used by the Elmer search engine to identify an object uniquely in the scope of a traffic data. Information may be used by back-end services like Map Service to draw itinerary on map.

    private List<RouteLink> routeLinks; // All part distances, if more than one you need to change bus/tran..

    private String distance; // Distance i meters.
    private String CO2value; // CO2 value in kg/person/km


    public String getTotalTravelTime() {
        if (routeLinks.size() > 0) {
            return TimeAndDateConverter.getTravelTimeinMinutes(routeLinks.get(0).getDepDateTime(), routeLinks.get(routeLinks.size() - 1).getArrDateTime());
        } else {
            return "";
        }
    }

    public void printMessages() {
        for (RouteLink r : routeLinks) {
//            if(r.getTransportMode() == RouteLink.TRAIN){
//                Log.d("" ,"\nLine nbr: " + r.getLineName() + " \n Line nbr" + r.getLineNbr() + " \n operator name " + r.getOperatorName() + "\n runNbr: " + r.getRunNbr() + "\n Train NBR: " + r.getTrainNbr() + "\n " + r.getTransportModeName());
//            }
        }
    }


    /**
     * Shortens a message in a "smart" way
     */
    public String getSmartMessage() {
        String message = "";
        String text = "";
        for (RouteLink r : routeLinks) {
            if (r.getSummary() != null) {
                message = r.getSummary();
            }
        }

        for (RouteLink r : routeLinks) {
            if (r.getText() != null) {
                text = r.getText();
            }
        }
        if (message.equals("") || message.length() < 2) {
            return text;
        } else {
            return message + "\n" + text;
        }
    }

    public String getTimeToDep() {
        if (routeLinks.size() > 0) {
            return TimeAndDateConverter.timeToDeparture(routeLinks.get(0).getDepDateTime());
        } else {
            return "";
        }
    }

    public int deviationType() {
        if (routeLinks.size() > 0) {
            int diff = 0;
            String time = routeLinks.get(0).getDepTimeDeviation();
            if (time != null) {
                diff = Integer.parseInt(time);
                if (diff == 0) {
                    return RouteLink.IN_TIME;
                } else if (diff < 0) {
                    return RouteLink.EARLY;
                } else if (diff > 0) {
                    return RouteLink.LATE;
                }
            }
        }
        return RouteLink.UNKNOWN_DEVIATION;

    }

    public String getDeviationDepTime() {
        if (routeLinks.size() > 0) {
            String time = routeLinks.get(0).getDepTimeDeviation();
            if (time == null) {
                return "";
            } else {
                if(time.equals("0")) return "i tid";
                return time + " min ";
            }
        } else {
            return "";
        }
    }

    public String getDeviationArrTime() {
        if (routeLinks.size() > 0) {
            String time = routeLinks.get(0).getArrTimeDeviation();
            if (time == null) {
                return "";
            } else {
                return time + " min ";
            }
        } else {
            return "";
        }
    }

    public String getNbrChanges() {
        return (routeLinks.size() - 1) + "";
    }

    public List<String> getChangeNbrs() {
        List<String> l = new ArrayList<String>();
        for (RouteLink r : routeLinks) {
            l.add(r.getLineNbr());
        }
        return l;
    }

    public List<Integer> getLineTypes() {
        List<Integer> l = new ArrayList<Integer>();
        if (routeLinks.size() > 0) {
            for (RouteLink r : routeLinks) {
                l.add(r.getLineTypeId());
            }
        }
        return l;
    }

    public int getFirstRouteLineId() {
        if (routeLinks.size() > 0) {
            return routeLinks.get(0).getLineTypeId();
        } else {
            return -1;
        }
    }

    public String getFirstRouteTransportName() {
        if (routeLinks.size() > 0) {
            return routeLinks.get(0).getTransportModeName();
        } else {
            return "";
        }
    }

    public String getFirstRouteLineNbr() {
        if (routeLinks.size() > 0) {
            return routeLinks.get(0).getLineNbr();
        } else {
            return "";
        }

    }

    public List<RouteLink> getRouteLinks() {
        return routeLinks;
    }

    public void setRouteLinks(List<RouteLink> routeLinks) {
        this.routeLinks = routeLinks;
    }

    public void setSequenceNbr(String sequenceNbr) {
        this.sequenceNbr = sequenceNbr;
    }

    public String getSequenceNbr() {
        return sequenceNbr;
    }

    public void setDepDateTime(String depDateTime) {
        this.depDateTime = depDateTime;
    }

    public String getDepDateTime() {
        return routeLinks.get(0).getDepDateTime();
    }

    public void setArrDateTime(String arrDateTime) {
        this.arrDateTime = arrDateTime;
    }

    public String getArrDateTime() {
        return routeLinks.get(routeLinks.size() - 1).getArrDateTime();
    }

    public void setDepWalkDist(String depWalkDist) {
        this.depWalkDist = depWalkDist;
    }

    public String getDepWalkDist() {
        return depWalkDist;
    }

    public void setArrWalkDist(String arrWalkDist) {
        this.arrWalkDist = arrWalkDist;
    }

    public String getArrWalkDist() {
        return arrWalkDist;
    }

    public void setNbrOfChanges(String nbrOfChanges) {
        this.nbrOfChanges = nbrOfChanges;
    }

    public String getNbrOfChanges() {
        return nbrOfChanges;
    }

    public void setGuaranteed(String guaranteed) {
        this.guaranteed = guaranteed;
    }

    public String getGuaranteed() {
        return guaranteed;
    }

    public void setCO2Factor(String CO2Factor) {
        this.CO2Factor = CO2Factor;
    }

    public String getCO2Factor() {
        return CO2Factor;
    }

    public void setNbrOfZones(String nbrOfZones) {
        this.nbrOfZones = nbrOfZones;
    }

    public String getNbrOfZones() {
        return nbrOfZones;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setCO2value(String CO2value) {
        this.CO2value = CO2value;
    }

    public String getCO2value() {
        return CO2value;
    }

    public Station getStartStation() {
        return routeLinks.get(0).getFromStation();
    }

    public Station getEndStation() {
        return routeLinks.get(routeLinks.size() - 1).getToStation();
    }

    public String toString() {
        return "______Journey_Start_____\n" +
                "Start journey: " + getStartStation() + "\n" +
                "To journey: " + getEndStation() + "\n" +
                "Dep journey: " + getDepDateTime() + "\n" +
                "Arr journey: " + getArrDateTime() + "\n" +
                "Nbr changes: " + getNbrOfChanges() + "\n" +
                "Total distance: " + getDistance() + "\n" +
                "Part routes: " + "\n" + routeLinks.toString() +
                "\n_________Journey Snd________\n";

    }

    public void setJourneyResultKey(String journeyResultKey) {
        this.journeyKey = journeyResultKey;
    }

    public String getJourneyResultKey() {
        return journeyKey;
    }
}
