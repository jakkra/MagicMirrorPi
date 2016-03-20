package se.jakobkrantz.magic.downloaders;
/*
 * Created by krantz on 14-11-19.
 */


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import se.jakobkrantz.magic.skanetrafikenAPI.Station;
import se.jakobkrantz.magic.skanetrafikenAPI.XMLQueryStationHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class SearchStationsTask {
    private XMLQueryStationHandler xmlStationHandler;
    private XMLReader xmlR;

    public SearchStationsTask() {
        SAXParserFactory saxPF = SAXParserFactory.newInstance();
        SAXParser saxP;

        try {
            saxP = saxPF.newSAXParser();
            xmlR = saxP.getXMLReader();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        xmlStationHandler = new XMLQueryStationHandler();
        xmlR.setContentHandler(xmlStationHandler);
    }

    public ArrayList<Station> download(String... params) {
        try {
            InputStream inputStream = new URL(params[0]).openStream();
            xmlR.parse(new InputSource(inputStream));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return xmlStationHandler.getStations();
    }



}
