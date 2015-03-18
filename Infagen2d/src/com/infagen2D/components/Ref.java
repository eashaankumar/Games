package com.infagen2D.components;

public class Ref {
	public static final int SEED = (int)(Math.random() * Integer.MAX_VALUE);
	
	public static int getRandom(int min, int max){
		return (int)(Math.random() * (max - min) + min);
	}
}