package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceLoader {

	public static String loadShader(String filename){
		StringBuilder shaderSource = new StringBuilder();
		BufferedReader shaderReader = null;
		
		try{
			shaderReader = new BufferedReader(new FileReader("src/res/shaders/" + filename));
			String line= null;
			
			while((line = shaderReader.readLine()) != null){
				shaderSource.append(line).append("\n");
			}
			
			shaderReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		return shaderSource.toString();
	}
}
