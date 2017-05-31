package com.iqbaaaaalf.hotspotvisualizerfix.util;

import android.util.ArraySet;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.DataType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.OneSeqType;
import com.iqbaaaaalf.hotspotvisualizerfix.dataType.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * temoat kumpulan modul
 * bantuan
 * 
 */
public class Util {
	private CSVReader csvreader = new CSVReader();
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
		return (long) date.getTime()/1000;
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
		return (long) date.getTime()/1000;
	}
	/*
	 * mengubah format unix time ke tanggal dengan format dd-mm-yyyy
	 */
	public String convert2Date(long tanggalUnix) throws ParseException {
		Date date = new Date(tanggalUnix*1000L);
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		String tanggal;
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
		csvreader.csvSearchForSeq(longit, latit, inputPath);
	}
	
	public ArrayList<List<Long>> ambilListSeq(){
		listSeq = csvreader.ambilListSeq();
		return this.listSeq;
	}

	public ArrayList<LatLng> pointlatLngList (ArrayList<Point> listTitik){
		ArrayList<LatLng> list = new ArrayList<LatLng>();
		for (Point titik : listTitik){
			LatLng latLng = new LatLng(titik.getLatitude(),titik.getLongitude());
			list.add(latLng);
		}
		return list;
	}

	public JSONObject listToGeoJson(ArrayList<Point> listTitik) {
        JSONObject featureCollection = new JSONObject();
        try {
            featureCollection.put("type", "featureCollection");
            JSONArray featureList = new JSONArray();
            // iterate through your list
            for (Point titik : listTitik) {
                // {"type": "Feature", "geometry": {"type": "Point", "coordinates": [-94.149, 36.33]}

                JSONObject point = new JSONObject();
                point.put("type", "Point");
                // construct a JSONArray from a string; can also use an array or list
                JSONArray koor = null;

                koor = new JSONArray("["+titik.getLongitude()+","+titik.getLatitude()+"]");

                point.put("coordinates", koor);
                JSONObject feature = new JSONObject();
				feature.put("type", "Feature");
                feature.put("geometry", point);
                featureList.put(feature);
                featureCollection.put("features", featureList);
            }
        } catch (JSONException e) {
            Log.e("geoJson Exception","tidak dapat save koordinat kedalam geojson: "+e.toString());
        }
        // output the result
        System.out.println(featureCollection.toString());

        return featureCollection;
	}

	public String longLatToString(Double longit, Double latit){
        return longit + ";" +latit;
    }

    public Point stringToPointLongLat(String stringLongLat){
        Point titik = new Point();
        Double latit = (double) 0;
        Double longit = (double) 0;
        Pattern p = Pattern.compile("((?:\\-|)\\d.*?);((?:\\-|)\\d.*)");
        Matcher m  = p.matcher(stringLongLat);
        if(m.find()){
            longit = Double.parseDouble(m.group(1));
            latit = Double.parseDouble(m.group(2));
			System.out.println("Check Parsing in stringToPointLongLat");
			System.out.println("Long : " + longit + " , Lat: "+ latit);
        }
        titik.setLongitude(longit.doubleValue());
        titik.setLatitude(latit.doubleValue());

        return titik;
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
			}else{
                System.out.println("Tidak ditemukan pattern Long Lat dalam parsing string ke Doublw");
            }
			System.out.println("Jumlah unix pada line ada " + unix.size() + " dan jumlah support " + oneSeq.getSupport());
			allSeq.add(oneSeq);

		}


		return allSeq;
	}

	public ArrayList<Point> getCommonPoint(OneSeqType oneSeq, String alamatFile){
		ArrayList<Point> listPoint = new ArrayList<Point>();
		Set<Point> setPoint1 = new HashSet<Point>();
		Set<Point> setPoint2 = new HashSet<Point>();

        Set<String> stringLongLatList1 = new HashSet<String>();
        Set<String> stringLongLatList2 = new HashSet<String>();
        Set<String> stringLongLatListTemp = new HashSet<String>();
        ArrayList<Set<String>> stringListTemp = new ArrayList<Set<String>>();

		System.out.println("===== Jumlah unix dalam Seq : " + oneSeq.getListUnix().size() + " =====");

		if(oneSeq.getListUnix().size() == 1){

			listPoint = csvreader.csvSearchForVisual(oneSeq.getListUnix().get(0), alamatFile);

		}else if(oneSeq.getListUnix().size() == 2){
			setPoint1.addAll(csvreader.csvSearchForVisual(oneSeq.getListUnix().get(0),alamatFile));

			////////////////////// --- TEST --- ///////////////////
//			System.out.println("===== TEST ambil titik dari csv berdasarkan unix time 1 ====");
//			for (Point testTitik : setPoint1){
//				System.out.println("Long : " + testTitik.getLongitude() + " , Lat : " + testTitik.getLatitude());
//			}
//			///////////////////////////////////////////////////////

            for (Point titik:setPoint1){
                stringLongLatList1.add(longLatToString(titik.getLongitude(),titik.getLatitude()));
            }

			////////////////////// --- TEST --- ///////////////////
//			System.out.println("===== TEST ambil String titik  1 ====");
//			for (String stringTitik : stringLongLatList1){
//				System.out.println("Long Lat : " + stringTitik);
//			}
			///////////////////////////////////////////////////////

			setPoint2.addAll(csvreader.csvSearchForVisual(oneSeq.getListUnix().get(1),alamatFile));

			////////////////////// --- TEST --- ///////////////////
//			System.out.println("===== TEST ambil titik dari csv berdasarkan unix time 2 ====");
//			for (Point testTitik : setPoint2){
//				System.out.println("Long : " + testTitik.getLongitude() + " , Lat : " + testTitik.getLatitude());
//			}
			///////////////////////////////////////////////////////

            for (Point titik:setPoint2){
                stringLongLatList2.add(longLatToString(titik.getLongitude(),titik.getLatitude()));
            }

			////////////////////// --- TEST --- ///////////////////
//			System.out.println("===== TEST ambil String titik  2 ====");
//			for (String stringTitik : stringLongLatList2){
//				System.out.println("Long Lat : " + stringTitik);
//			}
			///////////////////////////////////////////////////////

			if(stringLongLatList1.size()<stringLongLatList2.size()){
				stringLongLatList1.retainAll(stringLongLatList2);

				////////////////////// --- TEST --- ///////////////////
				System.out.println("===== TEST kalau stringLongLatList1 lebih kecil ====");

				for (String la : stringLongLatList1){
					System.out.println("String Long Lat : " + la);
				}

				///////////////////////////////////////////////////////

                for (String titikString : stringLongLatList1){
                    listPoint.add(stringToPointLongLat(titikString));
                }

                stringLongLatList1.clear();
                stringLongLatList2.clear();
                stringLongLatListTemp.clear();
                setPoint1.clear();
                setPoint2.clear();

			}else{
				stringLongLatList2.retainAll(stringLongLatList1);

				////////////////////// --- TEST --- ///////////////////
                System.out.println("===== TEST kalau stringLongLatList2 lebih kecil ====");
                for (String la : stringLongLatList2){
                    System.out.println("String Long Lat : " + la);
                }
				System.out.println("\n\n");
				///////////////////////////////////////////////////////

                for (String titikString : stringLongLatList2){
                    listPoint.add(stringToPointLongLat(titikString));
                }

                stringLongLatList1.clear();
                stringLongLatList2.clear();
                stringLongLatListTemp.clear();
                setPoint1.clear();
                setPoint2.clear();
			}
		}else{
			for (Long unix : oneSeq.getListUnix()){
				setPoint1.addAll(csvreader.csvSearchForVisual(unix,alamatFile));
                for (Point titik: setPoint1){
                    stringLongLatList1.add(longLatToString(titik.getLongitude(),titik.getLatitude()));
                }
                stringListTemp.add(stringLongLatList1);
			}

			stringLongLatList1.clear();

			// masukin list string pertama sekalian nanti
			// dijadiin tampungan
			stringLongLatListTemp = stringListTemp.get(0);
			for (int index = 1; index<=stringListTemp.size(); index++){
				stringLongLatListTemp.retainAll(stringListTemp.get(index));
			}

            for (String titikString : stringLongLatListTemp){
                listPoint.add(stringToPointLongLat(titikString));
            }

			stringLongLatListTemp.clear();
			setPoint1.clear();
		}

		return listPoint;
	}


}
