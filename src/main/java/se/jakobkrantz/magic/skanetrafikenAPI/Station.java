package se.jakobkrantz.magic.skanetrafikenAPI;
/*
 * Created by krantz on 14-11-18.
 */


public class Station {
    private String stationName;
    private int stationId;
    private double latitude;
    private double longitude;
    private double x, y;
    private String type;
    private String timeSearched;
    // private LatLng latLng;

    public Station(String stationName, int stationId, double latitude, double longitude, String type) {
        this.stationName = stationName;
        this.stationId = stationId;
        this.latitude = latitude; // https://github.com/goober/coordinate-transformation-library
        this.longitude = longitude; // RT90 format if needed use this library to change to long/lat
        this.type = type;
    }

    public Station() {

    }

    /*public LatLng getLatLng() {
        return latLng;
    }*/

    public String toString() {
        return stationName;
    }

    public boolean equals(Object o) {
        return o instanceof Station && stationId == ((Station) o).getStationId();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public double getLatitude() {
        return 0;
    }

    public void setX(double x) {
        this.x = x;
        if (y != 0) {
          //  setLatLng(x, y);
        }
    }

    public void setY(double y) {
        this.y = y;
        if (x != 0) {
          //  setLatLng(x, y);
        }
    }
    //TODO update so it doesn't just work on Android, but in desktop Java also (Because of LatLng class

    /*private void setLatLng(double x, double y) {
        RT90Position position = new RT90Position(x, y);
        WGS84Position wgsPos = position.toWGS84();
        double lat = ((double) Math.round(wgsPos.getLatitude() * 10000)) / 10000;
        double lon = ((double) Math.round(wgsPos.getLongitude() * 10000)) / 10000;
        this.latLng = new LatLng(lat, lon);

    }*/

    public double getLongitude() {
        return 0;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTimeSearched(String timeSearched) {
        this.timeSearched = timeSearched;
    }

    public String getTimeSearched() {
        return timeSearched;
    }

}
