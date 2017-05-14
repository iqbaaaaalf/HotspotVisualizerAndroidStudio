package com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by iqbaaaaalf on 5/9/2017.
 */

public interface TxtWrite {
    String namaFile = "DataSeq.txt";
    public abstract void buatFile(String namaFile);
    public abstract void setOneSeq();
    public abstract void set() throws FileNotFoundException;

}
