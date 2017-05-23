package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

/**
 * Created by iqbaaaaalf on 5/20/2017.
 */

public class Point {
    private double longitude;
    private double latitude;

    public Point() {
        this.longitude = 0;
        this.latitude = 0;
    }

    public void reset(){
        this.longitude = 0;
        this.latitude = 0;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
