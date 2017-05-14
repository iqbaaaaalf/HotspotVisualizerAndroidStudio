package com.iqbaaaaalf.hotspotvisualizerfix.util;

import com.iqbaaaaalf.hotspotvisualizerfix.dataType.DataType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * temoat kumpulan modul
 * bantuan
 * 
 */
public class Util {
	CSVReader csvreader = new CSVReader();
	ArrayList<List<Long>> listSeq = new ArrayList<List<Long>>();
	/*
	 * mengubah format tanggal YYYY-MM-DD menjadi format unix time
	 */
	public long convert2Unix1(String tanggal){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = dateFormat.parse(tanggal);
		} catch (ParseException e) {
			System.out.println("tanggal tidak dapat di parsing");
			e.printStackTrace();
		}
		long unixTime = (long) date.getTime()/1000;
		return unixTime;
	}
	/*
	 * mengubah format tanggal DD-MM-YYYY menjadi format unix time
	 */
	public long convert2Unix2(String tanggal){
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(tanggal);
		} catch (ParseException e) {
			System.out.println("tanggal tidak dapat di parsing");
			e.printStackTrace();
		}
		long unixTime = (long) date.getTime()/1000;
		return unixTime;
	}
	/*
	 * mengubah format unix time ke tanggal dengan format dd-mm-yyyy
	 */
	public String convert2Date(long tanggalUnix) throws ParseException {
		Date date = new Date(tanggalUnix*1000L);
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		String tanggal = null;
		tanggal = dateFormat.format(date);
		long unixTime = (long) date.getTime()/1000;
		return tanggal;
	}
	
	
	/*
	 * melakukan pengambilan data kolom dari format pertama yang didapat
	 * pada kolom pertama file csv - data riau 0-12
	 * hasil = 
	 */
	public DataType getdata(String kolom){
		/* regex, group 1 = latit
		 * group 2 = longit
		 * group 3 = aqr_date
		 */
		Double latit = (double) 0;
		Double longit = (double) 0;
		DataType dataKolom = new DataType();
		Pattern p = Pattern.compile("((?:\\-|)\\d.*?)\\;((?:\\-|)\\d.*?)\\;(\\d.*?)\\;(?:.)");
		Matcher m = p.matcher(kolom);
		if(m.find()){
			longit = Double.parseDouble(m.group(2));
			latit = Double.parseDouble(m.group(1));
			dataKolom.setValue(longit,latit,m.group(3));
		}
		return dataKolom;
	}
	/*
	 * melakukan pengambilan data kolom dari format kedua
	 * yang hanya pada data 14AllNew
	 */
	public DataType getdata2(String kolom){
		/* regex, group 1 = latit
		 * group 2 = longit
		 * group 3 = aqr_date
		 */
		Double latit = (double) 0;
		Double longit = (double) 0;
		DataType dataKolom = new DataType();
		Pattern p = Pattern.compile("((?:\\-|)\\d.*?)\\;\"((?:\\-|)\\d.*?)\"\\;\"(\\d.*?)\"\\;(?:.*)");
		Matcher m = p.matcher(kolom);
		if(m.find()){
			longit = Double.parseDouble(m.group(2));
			latit = Double.parseDouble(m.group(1));
			dataKolom.setValue(longit,latit,m.group(3));
		}
		return dataKolom;
	}
	
	public void cariKolom(double longit, double latit, String inputPath){
//		List<String> temp = new ArrayList<String>();
		csvreader.csvSearch(longit, latit, inputPath);
	}
	
	public ArrayList<List<Long>> ambilListSeq(){
		listSeq = csvreader.ambilListSeq();
		return this.listSeq;
	}

	public ArrayList<OneSeqType> getUnixFromSeq(ArrayList<String> seq){
		String tanggal = "";
		ArrayList<OneSeqType> allSeq = new ArrayList<>();
		for (String line: seq){
			OneSeqType oneSeq = new OneSeqType();
			ArrayList<Long> unix = new ArrayList<>();
			Pattern p = Pattern.compile("(?:(\\d{9}) -1 ){1}");
			Matcher m = p.matcher(line);

			while(m.find()){
				unix.add(Long.parseLong(m.group(1)));
			}

			oneSeq.setListUnix(unix);

			//support hanya mengcover sampai 2 digit
			Pattern q = Pattern.compile("(?:#SUP: )((\\d.|\\d))");
			Matcher n = q.matcher(line);

			if(n.find()){
				oneSeq.setSupport(Long.parseLong(n.group(1)));
			}
			System.out.println("Jumlah unix pada line ada " + unix.size() + " dan jumlah support " + oneSeq.getSupport());
			allSeq.add(oneSeq);

		}


		return allSeq;
	}


}
