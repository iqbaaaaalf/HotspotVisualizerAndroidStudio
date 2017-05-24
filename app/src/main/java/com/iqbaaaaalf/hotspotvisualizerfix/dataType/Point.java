package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by iqbaaaaalf on 5/20/2017.
 */

public class Point implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
    }

    protected Point(Parcel in){
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel source) { return new Point(source); }

        @Override
        public Point[] newArray(int size) { return new Point[size]; }
    };
}
