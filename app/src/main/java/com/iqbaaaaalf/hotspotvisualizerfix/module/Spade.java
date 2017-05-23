package com.iqbaaaaalf.hotspotvisualizerfix.module;

import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.util.RunSpade;
import com.iqbaaaaalf.hotspotvisualizerfix.util.TxtReader;
import com.iqbaaaaalf.hotspotvisualizerfix.util.TxtWriterSeqReadable;
import com.iqbaaaaalf.hotspotvisualizerfix.util.Util;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * fungsi untuk menjalankan Module Spade
 */

public class Spade {

    private String inputPath= "" ;
    private String outputPath= "" ;
    private String outputPathTemp= "" ;
    private ArrayList<String> seq = new ArrayList<String>();
    private ArrayList<OneSeqType> allSeq = new ArrayList<OneSeqType>();
    private String outputReadable = null;
    private ArrayList<String> outputReadablePerLine = new ArrayList<String>();

    RunSpade rs = new RunSpade();
    TxtReader tr = new TxtReader();
    Util util = new Util();

    public void run(String input, String output, String outputTemp, Double minSupport){
        inputPath = input;
        outputPath = output;
        outputPathTemp = outputTemp;

        try {
            rs.run(input, output, minSupport);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tr.TxtBaca(outputPath);

        seq = tr.getSequential();

        allSeq = util.getUnixFromSeq(seq);

        for(OneSeqType list : allSeq){
            System.out.print("Jumlah Unix dalam list ini " + list.getListUnix().size() + " diantaranya ");
            for(Long unix : list.getListUnix()){
                System.out.print(unix+ " ");
            }
            System.out.print("dengan nilai support " + list.getSupport());
            System.out.print("\n");
        }

        printReadableSeq();

    }


    public void printReadableSeq(){
        TxtWriterSeqReadable tws = new TxtWriterSeqReadable();
        tws.buatFile(outputPathTemp);
        String tanggal = null;

        for (OneSeqType oneSeq: allSeq){
            tws.openingWord();
            tws.setSupport(oneSeq.getSupport());
            for(Long unix: oneSeq.getListUnix()){
                try{
                    tanggal = util.convert2Date(unix);
                }catch (ParseException e){
                    System.err.println("Gagal dalam mengconvert tanggal pada "+this.getClass().getName());
                }
                tws.txtTulis(tanggal);
            }
            tws.setOneSeq();
            outputReadablePerLine.add(tws.getOneLine());

        }
        tws.set();
        outputReadable = tws.getText();
    }

    public String getOutputReadable() {
        return outputReadable;
    }

    public ArrayList<String> getOutputReadablePerLine() {
        return outputReadablePerLine;
    }

    public ArrayList<OneSeqType> getAllSeq() {
        return allSeq;
    }

}
