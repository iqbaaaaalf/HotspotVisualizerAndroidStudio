package com.iqbaaaaalf.hotspotvisualizerfix.module;


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
    private JSONObject geoJson = new JSONObject();

    public void setSelectedCardPosition(int selectedCardPosition) {
        this.selectedCardPosition = selectedCardPosition;
    }



    public void setAllSeq(ArrayList<OneSeqType> allSeq) {
        this.allSeq = allSeq;
    }

    public ArrayList<Point> getCommon() {
        return common;
    }

    public void run(String alamatFile){
        System.out.println("Posisi terpilih dari card : " + selectedCardPosition);
        common = util.getCommonPoint(allSeq.get(selectedCardPosition), alamatFile);
        geoJson = util.listToGeoJson(common);
    }


    public JSONObject getGeoJson() {
        return geoJson;
    }



}
