package com.iqbaaaaalf.hotspotvisualizerfix.module;


import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.Point;
import com.iqbaaaaalf.hotspotvisualizerfix.util.Util;

import java.util.ArrayList;

public class Visualisasi {

    private int selectedSpinnerPosition = 0;
    private ArrayList<OneSeqType> allSeq = new ArrayList<OneSeqType>();
    private Util util = new Util();
    private ArrayList<Point> commonPoint = new ArrayList<Point>();

    public void setSelectedSpinnerPosition(int selectedSpinnerPosition) {
        this.selectedSpinnerPosition = selectedSpinnerPosition;
    }

    public void setAllSeq(ArrayList<OneSeqType> allSeq) {
        this.allSeq = allSeq;
    }

    public ArrayList<Point> getCommonPoint() {
        return commonPoint;
    }

    public void run(String alamatFile){
       commonPoint = util.getCommonPoint(allSeq.get(selectedSpinnerPosition), alamatFile);
    }

}
