package se.jakobkrantz.magicmirror.downloaders;
/*
 * Created by krantz on 14-11-21.
 */


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import se.jakobkrantz.magicmirror.skanetrafikenAPI.Journey;
import se.jakobkrantz.magicmirror.skanetrafikenAPI.XMLQueryJourneyHandler;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchJourneysTask {
    private XMLQueryJourneyHandler xmlQueryJourneyHandler;
    private XMLReader xmlR;

    public SearchJourneysTask() {
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
        xmlQueryJourneyHandler = new XMLQueryJourneyHandler();
        xmlR.setContentHandler(xmlQueryJourneyHandler);
    }


    public ArrayList<Journey> download(String... params) {
        try {
            URL url = new URL(params[0]);
            xmlR.parse(new InputSource(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        String key = xmlQueryJourneyHandler.getJourneyRouteKey();
        ArrayList<Journey> j = xmlQueryJourneyHandler.getJourneys();
        //This is a really bad H4X :/
        //TODO give all the same journeyKey in xml parser and then give them sequenceNbr depending on position in array
        if (j.size() != 0) {
            j.get(0).setJourneyResultKey(key);
        }
        return j;
    }



}


