package com.iqbaaaaalf.hotspotvisualizerfix.util;

import com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass.CsvWrite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter implements CsvWrite {
	PrintWriter pw;
	StringBuilder sb = new StringBuilder();

	
	public CsvWriter(String namaFile) {
		try {
			pw = new PrintWriter(new File(namaFile));
		} catch (FileNotFoundException e) {
			System.out.println("Pembuatan file bermasalah :(");
			e.printStackTrace();
		}
		label();
		
	}

	@Override
	public void label(){
		sb.append("long");
		sb.append(",");
		sb.append("lat");
		sb.append(",");
		sb.append("tanggal");
		sb.append(",");
		sb.append("unixDate");
		sb.append('\n');
	}
	
	@Override
	public void set() throws FileNotFoundException {
		pw.write(sb.toString());
        pw.close();
        System.out.println("File berhasil dibuat :)");
	}

	public void csvTulis(double longit, double latit, String tanggalBaru, long tanggalUnix) {
		sb.append(longit);
		sb.append(",");
		sb.append(latit);
		sb.append(",");
		sb.append(tanggalBaru);
		sb.append(",");
		sb.append(tanggalUnix);
		sb.append('\n');
	}

}
