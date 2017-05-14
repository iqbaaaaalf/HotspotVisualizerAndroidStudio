package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

import java.util.ArrayList;

/**
 * Created by iqbaaaaalf on 5/9/2017.
 */

public class OneSeqType {
    private ArrayList<Long> listUnix = new ArrayList<>();
    private Long support = null;

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

}
