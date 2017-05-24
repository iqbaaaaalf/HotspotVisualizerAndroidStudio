package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by iqbaaaaalf on 5/9/2017.
 */

public class OneSeqType implements Parcelable{

    private ArrayList<Long> listUnix = new ArrayList<>();
    private Long support = null;

    public OneSeqType(){

    }

    public void setSupport(Long support) {
        this.support = support;
    }

    public void setListUnix(ArrayList<Long> listUnix) {
        this.listUnix = listUnix;
    }

    public ArrayList<Long> getListUnix() {
        return listUnix;
    }

    public Long getSupport() {
        return support;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.listUnix);
        dest.writeLong(this.support);
    }

    protected OneSeqType(Parcel in){
        listUnix = new ArrayList<>();
        in.readList(listUnix ,Long.class.getClassLoader());
        this.support = in.readLong();
    }

    public static final Creator<OneSeqType> CREATOR = new Creator<OneSeqType>() {
        @Override
        public OneSeqType createFromParcel(Parcel source) { return new OneSeqType(source); }

        @Override
        public OneSeqType[] newArray(int size) { return new OneSeqType[size]; }
    };
}
