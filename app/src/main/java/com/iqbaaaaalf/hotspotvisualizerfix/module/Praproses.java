package com.iqbaaaaalf.hotspotvisualizerfix.module;

import com.iqbaaaaalf.hotspotvisualizerfix.dataType.DataType;
import com.iqbaaaaalf.hotspotvisualizerfix.util.CSVReader;
import com.iqbaaaaalf.hotspotvisualizerfix.util.CsvWriter;
import com.iqbaaaaalf.hotspotvisualizerfix.util.CsvWriterSeq;
import com.iqbaaaaalf.hotspotvisualizerfix.util.TxtWriter;
import com.iqbaaaaalf.hotspotvisualizerfix.util.Util;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * class utama dalam praproses parsing tanggal
 */

public class Praproses {
	/*
	 * @param
	 * pathInput = alamat file csv data penelitian sebelumnya
	 * pathOutput = alamat file csv output
	 * tipeFormat = tipe format yang dilambangkan dengan int (0 : data riau 1-12, 1 : data riau 13-14)
	 */

	private List<Double> listLatit = new ArrayList<Double>();
	private List<Double> listLongit = new ArrayList<Double>();
	public void penyesuaianData(String pathInput, String pathOutput) throws FileNotFoundException {
		Util util = new Util();
		CSVReader csvreader = new CSVReader();
		List<String> kolom = new ArrayList<String>();
		DataType dataKolom = new DataType();
		long tanggalUnix = 0;
		String tanggalBaru = null; // format dd-mm-yyyy (disamaratakan)) -- TERNYATA BELOM BISA
		CsvWriter csvwriter = new CsvWriter(pathOutput);
		
		kolom = csvreader.CsvBaca(pathInput);
		for(String line : kolom){
			dataKolom = util.getdata(line); //untuk data 1-14
			//dataKolom = util.getdata2(line); // untuk data all
			// mengubah tanggal menjadi unix date
			// convert2Unix1 untuk data 1-12
			// convert2Unix2 untuk data 13-14
			try{
			tanggalUnix = util.convert2Unix1(dataKolom.getdate());
			} catch (Exception e) {
			tanggalUnix = util.convert2Unix2(dataKolom.getdate());
			}
			
			try {
				// mengubah unix date menjadi tanggal
				tanggalBaru = util.convert2Date(tanggalUnix);
			} catch (ParseException e) {
				System.out.println("proses parsing tanggal unix ke tanggal gagal :( ");
				e.printStackTrace();
			}
			//memasukan record ke dalam csv
			csvwriter.csvTulis(dataKolom.getLong(), dataKolom.getLat(), tanggalBaru , tanggalUnix);
			dataKolom.reset();
		}
		dataKolom.reset();
		csvwriter.set();
	
	}
	
	/*
	 * @param
	 * pathInput = alamat file csv data penelitian sebelumnya
	 * pathOutput = alamat file csv output
	 */
	public void convert2Sequential(String pathInput, String pathOutputTxt, String pathOutputCsv) throws FileNotFoundException {
		Util util = new Util();
		TxtWriter txtwriter = new TxtWriter();
		CSVReader csvreader = new CSVReader();
		List<String> kolom = new ArrayList<>();
		List<String> oneSeq = new ArrayList<>();
		kolom = csvreader.CsvSequence(pathInput);
		Set<String> hs = new HashSet<>();
		ArrayList<List<Long>> listSeq = new ArrayList<List<Long>>();
		Double latit = (double) 0;
		Double longit = (double) 0;
		hs.addAll(kolom);
		kolom.clear();
		kolom.addAll(hs);
		
		for(String line : kolom){
			Pattern p = Pattern.compile("((?:\\-|)\\d.*?)(?: )((?:\\-|)\\d.*)");
			Matcher m = p.matcher(line);
			if(m.find()){
				longit = Double.parseDouble(m.group(1));
				latit = Double.parseDouble(m.group(2));
				listLongit.add(longit);
				listLatit.add(latit);
				util.cariKolom(longit, latit, pathInput);
			}
		}
		listSeq = util.ambilListSeq();
		txtwriter.buatFile(pathOutputTxt);
		CsvWriterSeq WriteSeq = new CsvWriterSeq(pathOutputCsv);
		for(List<Long> list : listSeq){
            StringBuilder sb = new StringBuilder();
			for(Long unix : list){
				txtwriter.txtTulis(unix);
				sb.append(unix+" ");
			}
			txtwriter.setOneSeq();
            String seq = sb.toString();
            System.out.println(seq);
            oneSeq.add(seq);
		}
		txtwriter.set();

		for(int index = 0; index<listLongit.size(); index++){
			WriteSeq.csvTulis(listLongit.get(index),listLatit.get(index),oneSeq.get(index));
		}
		WriteSeq.set();

	}

}
