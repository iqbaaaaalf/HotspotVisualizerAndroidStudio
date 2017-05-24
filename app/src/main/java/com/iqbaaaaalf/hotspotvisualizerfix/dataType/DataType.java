package com.iqbaaaaalf.hotspotvisualizerfix.dataType;

import android.os.Parcel;
import android.os.Parcelable;

public class DataType implements Parcelable {
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



	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	  	dest.writeDouble(this.longitude);
		dest.writeDouble(this.latitude);
		dest.writeString(this.date);
	}

	protected DataType(Parcel in){
		this.longitude = in.readDouble();
		this.latitude = in.readDouble();
		this.date = in.readString();
	}

	public static final Creator<DataType> CREATOR = new Creator<DataType>() {
		@Override
		public DataType createFromParcel(Parcel source) {
			return new DataType(source);
		}

		@Override
		public DataType[] newArray(int size) {
			return new DataType[size];
		}
	};
}
