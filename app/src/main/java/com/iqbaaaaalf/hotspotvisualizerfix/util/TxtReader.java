package com.iqbaaaaalf.hotspotvisualizerfix.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by iqbaaaaalf on 5/7/2017.
 */

public class TxtReader {

    private ArrayList<String> seq = new ArrayList<String>();

    public void TxtBaca(String alamatFile) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(alamatFile));
        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan");
        }
        String line = "";
        StringBuilder sb = new StringBuilder();
        String text= "";

        try {
            while ((line = br.readLine()) != null) {
                seq.add(line.toString());
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            text = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gagal Membaca File Txt");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(text);
        }
    }

    public ArrayList<String> getSequential(){
        return seq;
    }

    public int getNumberOfSeq(){
        return seq.size();
    }
}
