package com.infagen.core;

import org.lwjgl.opengl.Display;

import com.infagen.loaders.Loader;
import com.infagen.renderEngines.DisplayManager;
import com.infagen.renderEngines.Renderer;
import com.infagen.renderEngines.Time;

public class Core {

	public static final int WIDTH = 1280, HEIGHT = 800;
	public static final String TITLE = "Infagen";
	public static final int FPS_CAP = 120;
	
	private static Game game;
	
	public static void main(String[] args){
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		game = new Game(loader);
		
		Time.initalize();
		while(!Display.isCloseRequested()){
			renderer.prepare();
			Time.updateDelta();
			//game logic
			game.update(renderer);
			//render
			DisplayManager.updateDisplay();

		}
		game.close();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}