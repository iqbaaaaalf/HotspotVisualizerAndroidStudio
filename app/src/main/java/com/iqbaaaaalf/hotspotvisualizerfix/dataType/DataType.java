package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

public class DataType {
	private double longitude;
	private double latitude;
	private String date;
	
	public void setValue(double longit, double latit, String tanggal){
		this.longitude = longit;
		this.latitude = latit;
		this.date = tanggal;
	}
	
	public void reset(){
		this.longitude = (double) 0;
		this.latitude = (double) 0;
		this.date = null;
	}
	
	public DataType() {
		this.longitude = (double) 0;
		this.latitude = (double) 0;
		this.date = null;
	}

	public double getLong(){
		return this.longitude;
	}
	
	public double getLat(){
		return this.latitude;
	}
	public String getdate(){
		return this.date;
	}
	public boolean isEmpty(){
		if(this.longitude == 0 || this.latitude == 0){
			return false;
		}
		return true;
	}

}
