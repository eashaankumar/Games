package com.lwjgl2D.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final int FPS_CAP = 60;
	

	private static long lastFrame;
	private static float delta;
		
	public static void createDisplay(){		
	/*	ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		*/
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			//Display.create(new PixelFormat(), attribs);
			Display.create();
			Display.setTitle("Our First Display!");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0,0, WIDTH, HEIGHT);
	}
	
	public static void setTime(){
		lastFrame = getTime();
	}
	
	public static void updateDisplay(){
		
		Display.sync(FPS_CAP);
		Display.update();
		
	
	
	}
	
	public static float updateDelta(){
		long currentTime = getTime();
		delta = (float)(currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}
	
	public static void closeDisplay(){
		
		Display.destroy();
	}
	
	public static long getTime(){
		return (Sys.getTime() * 1000 ) / Sys.getTimerResolution();
	}

}