package com.iqbaaaaalf.hotspotvisualizerfix.util;

import com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass.TxtWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by iqbaaaaalf on 5/9/2017.
 * sementara tidak mencetak txt tapi mengembalikan String
 */

public class TxtWriterSeqReadable implements TxtWrite {
    private StringBuilder sb = new StringBuilder();
    private PrintWriter pw;
    private Long support = null;
    private String text = null;

    public void setSupport(Long support) {
        this.support = support;
    }

    @Override
    public void buatFile(String namaFile) {
        try {
            pw = new PrintWriter(new File(namaFile));
        } catch (FileNotFoundException e) {
            System.out.println("pembuatan awal file bermasalah :(");
            e.printStackTrace();
        }
    }

    public void txtTulis(String tanggal){
        sb.append(tanggal + ", ");
    }

    public void openingWord(){
        sb.append("Kemunculan beruntun pada tanggal ");
    }

    @Override
    public void setOneSeq() {
        sb.append("pada "+support+" titik.");
        sb.append("\n");
    }

    @Override
    public void set() {
        text = sb.toString();
        text = text.trim();
        pw.write(text);
        pw.close();
        System.out.println("File txt readable berhasil dibuat\n");
        System.out.println(text);
    }

    public String getText() {
        return text;
    }

}
