package se.jakobkrantz.magicmirror.skanetrafikenAPI;
/*
 * Created by jakkra on 2015-03-02.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

//TODO change all localName to qName to get this class to work
public class XMLRouteMapHandler extends DefaultHandler {
    private boolean elementOn = false;
    private StringBuilder sb;
    private ArrayList<CoordinateRoute> journey;
    private CoordinateRoute route;
    private Station tempStation;
    private ArrayList<Point> points;
    private Point tempPoint;
    private boolean isOnCoordsElement;
    private boolean isOnLineElement;


    public XMLRouteMapHandler() {
        sb = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attribute) {
        elementOn = true;
        sb.delete(0, sb.length());
        if (localName.equals("ResultXML")) {
            journey = new ArrayList<CoordinateRoute>();
        } else if (localName.equals("Part")) {
            route = new CoordinateRoute();
        } else if (localName.equals("From")) {
            tempStation = new Station();
        } else if (localName.equals("To")) {
            tempStation = new Station();
        } else if (localName.equals("Coords")) {
            points = new ArrayList<Point>();
            isOnCoordsElement = true;
        } else if (localName.equals("Coord")) {
            tempPoint = new Point();
        } else if(localName.equals("Line")){
            route.setLineName(sb.toString());
            isOnLineElement = true;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        elementOn = false;

        localName = localName.trim();
        if (localName.equals("Id")) {
        } else if (localName.equals("Name") && !isOnLineElement) {
            tempStation.setStationName(sb.toString());
        } else if (localName.equals("id")) {
            tempStation.setStationId(Integer.parseInt(sb.toString()));
        } else if (localName.equals("Y") && !isOnCoordsElement) {
            tempStation.setY(Double.parseDouble(sb.toString()));
        } else if (localName.equals("X") && !isOnCoordsElement) {
            tempStation.setX(Double.parseDouble(sb.toString()));
        } else if (localName.equals("From")) {
            route.setFrom(tempStation);
        } else if (localName.equals("To")) {
            route.setTo(tempStation);
        } else if (localName.equals("X") && isOnCoordsElement) {
            tempPoint.setX(Double.parseDouble(sb.toString()));
        } else if (localName.equals("Y") && isOnCoordsElement) {
            tempPoint.setY(Double.parseDouble(sb.toString()));
        } else if (localName.equals("Coord")) {
            points.add(tempPoint);
        } else if (localName.equals("Coords")) {
            //TODO update so it doesn't just work on Android, but in desktop Java also (Because of LatLng class)
            //route.setRT90Points(points);
            isOnCoordsElement = false;
        } else if (localName.equals("Part")) {
            journey.add(route);
        } else if (localName.equals("LinTName")) {
            route.setLineName(sb.toString());
        } else if (localName.equals("Name") && isOnLineElement) {
            route.setLineNameNbr(sb.toString());
        } else if (localName.equals("No")) {
            route.setLineNbr(sb.toString());
        } else if(localName.equals("Line")){
            isOnLineElement = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //StringBuilder because the parser splits elements over multiple character arrays.
        if (elementOn) {
            sb.append(ch, start, length);
        }
    }

    public ArrayList<CoordinateRoute> getCoordinateRoutes() {
        return journey;
    }
}
