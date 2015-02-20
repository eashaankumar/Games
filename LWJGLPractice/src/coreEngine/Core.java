package coreEngine;

import org.lwjgl.opengl.Display;

import errors.GLErrorHandler;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class Core {

	public static void main(String[] args){
		DisplayManager.createDisplay();
		
	
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2,
		};
		
		RawModel model = loader.loadToVao(vertices, indices);
	
		while(!Display.isCloseRequested()){
			renderer.prepare();
			//shader
			shader.start();
			
			renderer.render(model);
			
			//sahder
			shader.stop();
			
			DisplayManager.updateDisplay();
			//GLErrorHandler.checkErrors("");
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}
}
