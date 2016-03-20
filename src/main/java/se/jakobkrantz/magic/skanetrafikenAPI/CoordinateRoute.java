package se.jakobkrantz.magic.skanetrafikenAPI;
/*
 * Created by jakkra on 2015-03-03.
 */


public class CoordinateRoute {
    private Station from;
    private Station to;
    //private List<LatLng> latLngs;
    private String lineName;
    private String lineNameNbr;
    private String lineNbr;

    public CoordinateRoute() {
       // latLngs = new ArrayList<LatLng>();
    }

    public Station getFrom() {
        return from;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }

    public void setTo(Station to) {
        this.to = to;
    }

 /*   public List<LatLng> getLatLngs() {
        return latLngs;
    }*/

    /**
     * Converts the points in the list and transforms them to WGS84 (long lat).
     * Use get LatLngs to retrieve them
     *
     * @param points List with Points in it
     */
    /*public void setRT90Points(List<Point> points) {
        //TODO update so it doesn't just work on Android, but in desktop Java also (Because of LatLng class
        /or (Point p : points) {
            RT90Position position = new RT90Position(p.getX(), p.getY());
            WGS84Position wgsPos = position.toWGS84();
            double lat = ((double) Math.round(wgsPos.getLatitude() * 10000)) / 10000;
            double lon = ((double) Math.round(wgsPos.getLongitude() * 10000)) / 10000;
            latLngs.add(new LatLng(lat, lon));
        }
    }*/


    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineNameNbr(String lineNameNbr) {
        this.lineNameNbr = lineNameNbr;
    }

    public String getLineNameNbr() {
        return lineNameNbr;
    }

    public void setLineNbr(String lineNbr) {
        this.lineNbr = lineNbr;
    }

    public String getLineNbr() {
        return lineNbr;
    }
}