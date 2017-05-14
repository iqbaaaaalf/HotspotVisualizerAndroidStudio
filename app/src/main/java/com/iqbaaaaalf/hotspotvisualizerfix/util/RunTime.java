package com.iqbaaaaalf.hotspotvisualizerfix.util;

public class RunTime {
	Long StartTime = (long) 0.0;
	Long time = (long) 0.0;
	
	public void start(){
		StartTime = System.currentTimeMillis();
	}
	
	public Long end(){
		time = System.currentTimeMillis() - StartTime;
		StartTime = (long) 0.0;
		return time;
	}
	
}
