package com.iqbaaaaalf.hotspotvisualizerfix.util;

import com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass.TxtWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class TxtWriter implements TxtWrite {
	StringBuilder sb = new StringBuilder();
	PrintWriter pw;

	@Override
	public void buatFile(String namaFile){
		try {
			pw = new PrintWriter(new File(namaFile));
		} catch (FileNotFoundException e) {
			System.out.println("pembuatan awal file bermasalah :(");
			e.printStackTrace();
		}
	}
	
	public void txtTulis(Long unixTime){
		sb.append(unixTime+" -1 ");
	}

	@Override
	public void setOneSeq(){
		sb.append(-2);
		sb.append('\n');
	}

	@Override
	public void set() throws FileNotFoundException {
		String text = sb.toString();
		text = text.trim();
		pw.write(text);
        pw.close();
        System.out.println("File seq berhasil dibuat :)");
	}
}
