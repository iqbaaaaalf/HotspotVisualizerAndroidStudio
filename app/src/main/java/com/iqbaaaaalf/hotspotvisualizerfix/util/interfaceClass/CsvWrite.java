package com.iqbaaaaalf.hotspotvisualizerfix.util.interfaceClass;

import java.io.FileNotFoundException;

public interface CsvWrite {
	String namaFile = "test.csv";
	
	public abstract void label();
	
	public abstract void set() throws FileNotFoundException;


}
