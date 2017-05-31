package com.iqbaaaaalf.hotspotvisualizerfix.module;


import com.google.android.gms.maps.model.LatLng;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.Point;
import com.iqbaaaaalf.hotspotvisualizerfix.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

public class Visualisasi {

    private int selectedCardPosition = 0;
    private ArrayList<OneSeqType> allSeq = new ArrayList<OneSeqType>();
    private Util util = new Util();
    private ArrayList<Point> common = new ArrayList<Point>();
    private ArrayList<LatLng> latLngList = new ArrayList<LatLng>();

    public void setSelectedCardPosition(int selectedCardPosition) {
        this.selectedCardPosition = selectedCardPosition;
    }



    public void setAllSeq(ArrayList<OneSeqType> allSeq) {
        this.allSeq = allSeq;
    }

    public ArrayList<LatLng> getLatLngList() {
        return latLngList;
    }

    public ArrayList<Point> getCommon() {
        return common;
    }

    public void run(String alamatFile){
        System.out.println("Posisi terpilih dari card : " + selectedCardPosition);
        common = util.getCommonPoint(allSeq.get(selectedCardPosition), alamatFile);
        latLngList = util.pointlatLngList(common);
    }


}
