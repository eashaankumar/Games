package com.base.engine;

public class Time {

	private static double delta;
	
	public static long getTime(){
		return System.nanoTime();
	}
	
	public static double getDelta(){
		return delta;
	}
	
	public static void setDelta(double delta){
		Time.delta = delta;
	}
}