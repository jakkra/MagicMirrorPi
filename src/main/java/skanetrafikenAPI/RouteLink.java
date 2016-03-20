package skanetrafikenAPI;/*
 * Created by krantz on 14-11-20.
 */


/**
 * Used in Journey class, each Journey consists of 1 or more RouteLinks.
 * For example one Journey from A to B has 3 bus/train changes, it will have 3 RouteLinks. One for each part of the Journey.
 */
public class RouteLink {

    //TravelTypes
//    public static String STADSBUSS = "1";
//    public static String REGIONBUSS = "2";
    public static final int TRAIN = 4;
    public static final int FERRY = 8;
    public static final int BUS = 16;

    //LineTypes
    public static final int PENDELN = 1;
    public static final int REGIONBUSS = 2;
    public static final int SKÅNE_EXPRESSEN = 4;
    public static final int STADSBUSS = 8;
    public static final int KRÖSATÅG = 16;
    public static final int PÅGATÅG_EXPRESS = 32;
    public static final int PÅGATÅGEN = 64;
    public static final int TÅGBUSS = 128;
    public static final int ÖRESUNDSTÅG = 256;

    public static final int KOMMERSIELL_TRAFIK = 512;
    public static final int FÄRJEFÖRBINDELSE = 1024;

    public static final int IN_TIME = 22;
    public static final int LATE = 24;
    public static final int EARLY = 26;
    public static final int UNKNOWN_DEVIATION = 28;


    private String lineNbr; //Line's number
    private String runNbr; //Line's run number
    private int lineTypeId; //Reference to one line type in line types collection defined by transport authority. All available line types and ids can be retreved from GetMeansOfTransport function
    private String lineTypeName; //Line type name
    private int transportMode; //Reference to one TransportMode in modes collection defined by transport authority. All available TransportModes and ids can be retreved from GetMeansOfTransport function
    private String transportModeName; //Transport mode name
    private String trainNbr; //Additional info about train number if route link's line type is train
    private String towardDirection; //Destination text
    private String operatorId; //Vehicle operators unique id
    private String operatorName; //Vehicle operators name
    private String depTimeDeviation; //Deviation from timetable time in min. (on departure side). Delays are positive integer values and earlier times are negative.
    private String arrTimeDeviation; //Deviation from timetable time in min. (on arrival side). Delays are positive integer values and earlier times are negative.
    private String accessibility; //Accessibility, sum of accessibility features for line where 1=R (Adapted for wheelchair), 2=S (Visually impaired), 4=H (Hearing impaired)
    private String routeLinkKey; //Used by the Elmer search engine to identify an object uniquely in the scope of a traffic data.
    private String depDateTime; //Departure date and time
    private String depIsTimingPoint; //Denotes if Departure node is a timing point. False means that DepDateTime is approximated time
    private String arrDateTime; //Arrival date and time
    private String arrIsTimingPoint; //Arrival date and time
    private String callTrip; //Denotes if Arrival node is timing point. False means that ArrDateTime is approximated time
    private String lineName; //Line's name
    private Station fromStation; // from station, can be an middle point
    private Station toStation; // to station, can be an middle point
    private String depDeviationAffect; //Describes how departure time deviation affects the journey.
    private String arrDeviationAffect; //Describes how arrival time deviation affects the journey.
    private String startPoint; // dep startPoint, eg "Läge D"
    private String stopPoint; // arrival stopPoint -||-

    //Notes and comments
    private String publicNote;
    private String text; //FootNote's text
    private String header;
    private String summary;
    private String shortText;

    //Used to debug
    public String toString() {
        return "\n-------RouteLink start---------\n" +
                "From station: " + getFromStation() + "\n" +
                "To stations: " + getToStation() + "\n" +
                "Line nbr: " + getLineNbr() + "\n" +
                "Line name: " + getLineName() + "\n" +
                "Run nbr: " + getRunNbr() + "\n" +
                "Line typ name: " + getLineTypeName() + "\n" +
                "Transport mode name: " + getTransportModeName() + "\n" +
                "Train nbr (bland if not a train): " + getTrainNbr() + "\n" +
                "Operator id name: " + getOperatorName() + "\n" +
                "Operator name: " + getOperatorName() + "\n" +
                "Towards: " + getTowardDirection() + "\n" +
                "Dep time: " + getDepDateTime() + "\n" +
                "Dep is timing point: " + getDepIsTimingPoint() + "\n" +
                "Dep time deviation: " + getDepTimeDeviation() + "\n" +
                "Dep time deviation affect: " + getDepDeviationAffect() + "\n" +
                "Arr time: " + getArrDateTime() + "\n" +
                "Arr is timing point: " + getArrIsTimingPoint() + "\n" +
                "Arr deviation time: " + getArrTimeDeviation() + "\n" +
                "Arr time deviation effect: " + getArrDeviationAffect() + "\n" +
                "Accessibility level: " + getAccessibility() + "\n" +
                "\n" +
                "Text: " + getText() + "\n" +
                "Public note: " + getPublicNote() + "\n" +
                "Header: " + getHeader() + "\n" +
                "Summary: " + getSummary() + "\n" +
                "Short text: " + getShortText() + "\n" +
                "-------RouteLink end---------";
    }

    public String getLineNbr() {
        if (transportMode == TRAIN) {
            return trainNbr;
        } else if (lineTypeName.equals("Gång")) {
            return "";
        } else {
            return lineName;

        }
    }

    public int deviationType() {
        int diff = 0;
        String time = getDepTimeDeviation();
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
        return RouteLink.UNKNOWN_DEVIATION;

    }

    public String getDeviationDepTimeToString() {
        String time = getDepTimeDeviation();
        if (time == null) {
            return "";
        } else {
            if (time.equals("0")) return "i tid";
            return time + " min ";
        }

    }

    public String getDeviationArrTimeToString() {
        String time = getArrTimeDeviation();
        if (time == null) {
            return "";
        } else {
            return time + " min ";
        }

    }

    public void setLineNbr(String lineNbr) {
        this.lineNbr = lineNbr;
    }


    public void setRunNbr(String runNbr) {
        this.runNbr = runNbr;
    }

    public String getRunNbr() {
        return runNbr;
    }

    public void setLineTypeId(String lineTypeId) {
        if (lineTypeId != null && !lineTypeId.equals("")) {
            this.lineTypeId = Integer.parseInt(lineTypeId);
        }
    }

    public int getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeName(String lineTypeName) {
        this.lineTypeName = lineTypeName;
    }

    public String getLineTypeName() {
        return lineTypeName;
    }

    public void setTransportMode(String transportMode) {
        if (transportMode != null && !transportMode.equals("")) {
            this.transportMode = Integer.parseInt(transportMode);
        }
    }

    public int getTransportMode() {
        return transportMode;
    }

    public void setTransportModeName(String transportModeName) {
        this.transportModeName = transportModeName;
    }

    public String getTransportModeName() {
        return transportModeName;
    }

    public void setTrainNbr(String trainNbr) {
        this.trainNbr = trainNbr;
    }

    public String getTrainNbr() {
        if (trainNbr == null) return "";
        return trainNbr;
    }

    public void setTowardDirection(String towardDirection) {
        this.towardDirection = towardDirection;
    }

    public String getTowardDirection() {
        return towardDirection;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }


    public void setDepTimeDeviation(String depTimeDeviation) {
        this.depTimeDeviation = depTimeDeviation;
    }

    public String getDepTimeDeviation() {
        return depTimeDeviation;
    }

    public void setArrTimeDeviation(String arrTimeDeviation) {
        this.arrTimeDeviation = arrTimeDeviation;
    }

    public String getArrTimeDeviation() {
        return arrTimeDeviation;
    }


    /**
     * @param accessibility sum of accessibility features for line where
     *                      1=R (Adapted for wheelchair), 2=S (Visually impaired),
     *                      4=H (Hearing impaired)
     */
    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setRouteLinkKey(String routeLinkKey) {
        this.routeLinkKey = routeLinkKey;
    }

    public String getRouteLinkKey() {
        return routeLinkKey;
    }

    public void setDepDateTime(String depDateTime) {
        this.depDateTime = depDateTime;
    }

    public String getDepDateTime() {
        return depDateTime;
    }

    public void setDepIsTimingPoint(String depIsTimingPoint) {
        this.depIsTimingPoint = depIsTimingPoint;
    }

    public String getDepIsTimingPoint() {
        return depIsTimingPoint;
    }

    public void setArrDateTime(String arrDateTime) {
        this.arrDateTime = arrDateTime;
    }

    public String getArrDateTime() {
        return arrDateTime;
    }

    public void setArrIsTimingPoint(String arrIsTimingPoint) {
        this.arrIsTimingPoint = arrIsTimingPoint;
    }

    public String getArrIsTimingPoint() {
        return arrIsTimingPoint;
    }

    public void setCallTrip(String callTrip) {
        this.callTrip = callTrip;
    }

    public String getCallTrip() {
        return callTrip;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setDepDeviationAffect(String depDeviationAffect) {
        this.depDeviationAffect = depDeviationAffect;
    }

    public String getDepDeviationAffect() {
        return depDeviationAffect;
    }

    public void setArrDeviationAffect(String arrDeviationAffect) {
        this.arrDeviationAffect = arrDeviationAffect;
    }

    public String getArrDeviationAffect() {
        return arrDeviationAffect;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setPublicNote(String publicNote) {
        this.publicNote = publicNote;
    }

    public String getPublicNote() {
        return publicNote;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getShortText() {
        return shortText;
    }

    public String getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public boolean isTrain() {
        return transportMode == TRAIN;
    }
}
