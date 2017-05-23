package com.iqbaaaaalf.hotspotvisualizerfix.util;

import android.os.Environment;
import android.support.v4.app.Fragment;

/**
 * Created by iqbaaaaalf on 5/20/2017.
 */

public class DirectoryList extends Fragment {

    String PathInput = Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/input/";
    String PathOutputCsv= Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/praproses/csv/";
    String PathOutputSeq= Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/praproses/seq/";

    String PathInputSpade = Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/praproses/seq/";
    String PathOutputSpade= Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/spade/";
    String PathOutputTemp= Environment.getExternalStorageDirectory().toString()+"/dataSkripsi/output/tempSpade/";


    public String getPathInput() {
        return PathInput;
    }

    public String getPathOutputCsv() {
        return PathOutputCsv;
    }

    public String getPathOutputSeq() {
        return PathOutputSeq;
    }

    public String getPathInputSpade() {
        return PathInputSpade;
    }

    public String getPathOutputSpade() {
        return PathOutputSpade;
    }

    public String getPathOutputTemp() {
        return PathOutputTemp;
    }
}
