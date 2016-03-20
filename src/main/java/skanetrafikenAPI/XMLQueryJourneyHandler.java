package skanetrafikenAPI;
/*
 * Created by krantz on 14-11-20.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XMLQueryJourneyHandler extends DefaultHandler {
    boolean elementOn = false;
    private ArrayList<Journey> journeys;
    private Journey t;
    private Station tempStation;
    private ArrayList<RouteLink> routeLinks;
    private RouteLink r;
    private boolean isOnRouteLinksElement;
    private boolean isOnLineElement;
    private boolean isOnPriceZoneElement;
    private StringBuilder sb;
    private String jounreyRouteKey;

    public XMLQueryJourneyHandler() {
        sb = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attribute) {
        elementOn = true;
        sb.delete(0, sb.length());
        if (qName.equals("Journeys")) {
            journeys = new ArrayList<Journey>();
        } else if (qName.equals("Journey")) {
            t = new Journey();
        } else if (qName.equals("From")) {
            tempStation = new Station();
        } else if (qName.equals("To")) {
            tempStation = new Station();
        } else if (qName.equals("Line")) {
            isOnLineElement = true;
        } else if (qName.equals("PriceZones")) {
            isOnPriceZoneElement = true;
        } else if (qName.equals("RouteLinks")) {
            routeLinks = new ArrayList<RouteLink>();
            isOnRouteLinksElement = true;
        } else if (qName.equals("RouteLink")) {
            r = new RouteLink();
        }

    }

    //FML...
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        elementOn = false;
        //Journey
        if (qName.equals("SequenceNo")) {
            t.setSequenceNbr(sb.toString());
        } else if (qName.equals("DepDateTime") && !isOnRouteLinksElement) {
            t.setDepDateTime(sb.toString());
        } else if (qName.equals("ArrDateTime") && !isOnRouteLinksElement) {
            t.setArrDateTime(sb.toString());
        } else if (qName.equals("DepWalkDist")) {
            t.setDepWalkDist(sb.toString());
        } else if (qName.equals("ArrWalkDist")) {
            t.setArrWalkDist(sb.toString());
        } else if (qName.equals("NoOfChanges")) {
            t.setNbrOfChanges(sb.toString());
        } else if (qName.equals("Guaranteed")) {
            t.setGuaranteed(sb.toString());
        } else if (qName.equals("CO2factor")) {
            t.setCO2Factor(sb.toString());
        } else if (qName.equals("NoOfZones")) {
            t.setNbrOfZones(sb.toString());
        } else if (qName.equals("JourneyResultKey")) {
            jounreyRouteKey = sb.toString();

            //RouteLink start
        } else if (qName.equals("RouteLinkKey")) {
            r.setRouteLinkKey(sb.toString());
        } else if (qName.equals("DepDateTime") && isOnRouteLinksElement) {
            r.setDepDateTime(sb.toString());
        } else if (qName.equals("DepIsTimingPoint")) {
            r.setDepIsTimingPoint(sb.toString());
        } else if (qName.equals("ArrDateTime") && isOnRouteLinksElement) {
            r.setArrDateTime(sb.toString());
        } else if (qName.equals("ArrIsTimingPoint")) {
            r.setArrIsTimingPoint(sb.toString());
        } else if (qName.equals("CallTrip")) {
            r.setCallTrip(sb.toString());
        } else if (qName.equals("DepTimeDeviation")) {
            r.setDepTimeDeviation(sb.toString());
        } else if (qName.equals("DepDeviationAffect")) {
            r.setDepDeviationAffect(sb.toString());
        } else if (qName.equals("ArrTimeDeviation")) {
            r.setArrTimeDeviation(sb.toString());
        } else if (qName.equals("ArrDeviationAffect")) {
            r.setArrDeviationAffect(sb.toString());
        } else if (qName.equals("Id") && !isOnPriceZoneElement) {
            tempStation.setStationId(Integer.parseInt(sb.toString()));
        } else if (qName.equals("Name") && !isOnLineElement) {
            tempStation.setStationName(sb.toString());


            //Line
        } else if (qName.equals("Name") && isOnLineElement) {
            r.setLineName(sb.toString());
        } else if (qName.equals("No")) {
            r.setLineNbr(sb.toString());
        } else if (qName.equals("RunNo")) {
            r.setRunNbr(sb.toString());
        } else if (qName.equals("LineTypeId")) {
            r.setLineTypeId(sb.toString());
        } else if (qName.equals("LineTypeName")) {
            r.setLineTypeName(sb.toString());
        } else if (qName.equals("TransportModeId")) {
            r.setTransportMode(sb.toString());
        } else if (qName.equals("TransportModeName")) {
            r.setTransportModeName(sb.toString());
        } else if (qName.equals("TrainNo")) {
            r.setTrainNbr(sb.toString());
        } else if (qName.equals("Towards")) {
            r.setTowardDirection(sb.toString());
        } else if (qName.equals("OperatorId")) {
            r.setOperatorId(sb.toString());
        } else if (qName.equals("OperatorName")) {
            r.setOperatorName(sb.toString());
        } else if (qName.equals("Text")) {
            r.setText(sb.toString());
        } else if (qName.equals("PublicNote")) {
            r.setPublicNote(sb.toString());
        } else if (qName.equals("Header")) {
            r.setHeader(sb.toString());
        } else if (qName.equals("Summary")) {
            r.setSummary(sb.toString());
        } else if (qName.equals("ShortText")) {
            r.setShortText(sb.toString());

        } else if (qName.equals("DepTimeDeviation")) {
            r.setDepTimeDeviation(sb.toString());
        } else if (qName.equals("ArrTimeDeviation")) {
            r.setArrTimeDeviation(sb.toString());
        } else if (qName.equals("Accessibility")) {
            r.setAccessibility(sb.toString());
        } else if (qName.equals("StopPoint")) {
            if (r.getStartPoint() == null) {
                r.setStartPoint(sb.toString());
            } else {
                r.setStopPoint(sb.toString());
            }
            //Done with RouteLink
        } else if (qName.equals("Distance")) {
            t.setDistance(sb.toString());
        } else if (qName.equals("CO2value")) {
            t.setCO2value(sb.toString());

        } else if (qName.equals("From")) {
            r.setFromStation(tempStation);
        } else if (qName.equals("To")) {
            r.setToStation(tempStation);
        } else if (qName.equals("Line")) {
            isOnLineElement = false;
        } else if (qName.equals("PriceZones")) {
            isOnPriceZoneElement = true;
        } else if (qName.equals("RouteLink")) {
            routeLinks.add(r);
        } else if (qName.equals("Journey")) {
            t.setRouteLinks(routeLinks);
            journeys.add(t);
        } else if (qName.equals("RouteLinks")) {
            isOnRouteLinksElement = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //StringBuilder because the parser splits elements over multiple character arrays.
        if (elementOn) {
            sb.append(ch, start, length);
        }
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public String getJourneyRouteKey() {
        return jounreyRouteKey;
    }
}
