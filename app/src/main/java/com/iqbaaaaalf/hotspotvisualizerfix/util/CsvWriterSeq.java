package com.iqbaaaaalf.hotspotvisualizerfix.util;

import com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass.CsvWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriterSeq implements CsvWrite {
	PrintWriter pw;
	StringBuilder sb = new StringBuilder();

	
	public CsvWriterSeq(String namaFile){
		try {
			pw = new PrintWriter(new File(namaFile));
		} catch (FileNotFoundException e) {
			System.out.println("pembuatan file bermasalah :(");
			e.printStackTrace();
		}
		label();
		
	}

	@Override
	public void label() {
		sb.append("long");
		sb.append(",");
		sb.append("lat");
		sb.append(",");
		sb.append("Sequence");
		sb.append('\n');
	}

	@Override
	public void set() throws FileNotFoundException {
		pw.write(sb.toString());
        pw.close();
        System.out.println("File berhasil dibuat :)");
	}
	
	public void csvTulis(double longit, double latit, String sequence) {
		sb.append(longit);
		sb.append(",");
		sb.append(latit);
		sb.append(",");
		sb.append(sequence);
		sb.append('\n');
	}

}
