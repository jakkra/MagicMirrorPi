package se.jakobkrantz.magic.downloaders;
/*
 * Created by jakkra on 2015-03-02.
 */


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import se.jakobkrantz.magic.skanetrafikenAPI.CoordinateRoute;
import se.jakobkrantz.magic.skanetrafikenAPI.XMLRouteMapHandler;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchRouteMapTask{
    private XMLRouteMapHandler xmlRouteMapHandler;
    private XMLReader xmlR;

    public SearchRouteMapTask() {
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
        xmlRouteMapHandler = new XMLRouteMapHandler();
        xmlR.setContentHandler(xmlRouteMapHandler);
    }


    public ArrayList<CoordinateRoute> download(String... params) {

        try {
            String xml = new Scanner(new URL(params[0]).openStream()).useDelimiter("\\A").next();
            //xml = HtmlEscape.unescapeHtml(xml);
            InputSource stream = new InputSource(new StringReader(xml));
            xmlR.parse(stream);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return xmlRouteMapHandler.getCoordinateRoutes();

    }


}
