package com.infagen.gameObject;

import org.lwjgl.util.vector.Vector3f;
import com.infagen.loaders.Loader;
import com.infagen.model.RawModel;
import com.infagen.texture.ModelTexture;
import com.infagen.world.World;

public class Terrain {

	public static final float SIZE = 800;
	private static final int VERTEX_COUNT = 128;
	private static final float MAX_HEIGHT = 100;
	
	private Transform tranform;
	private RawModel model;
	
	private ModelTexture texture;
	
	
	public Terrain(int gridX, int gridZ, Loader loader, ModelTexture texture){
		this.texture = texture;
		this.tranform = new Transform(new Vector3f(gridX * SIZE, 0, gridZ * SIZE), 0, 0, 0, 1);
		this.model = generateTerrain(loader);
	}
	
	
	private RawModel generateTerrain(Loader loader){
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = this.getHeight(i, j);
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVao(vertices, textureCoords, normals, indices);
	}
	
	public float getHeight(int i, int j){
		/*double xStart = this.tranform.getPosition().x/SIZE * this.VERTEX_COUNT;
		double xEnd = xStart + this.VERTEX_COUNT;
		double yStart = this.tranform.getPosition().y/SIZE* this.VERTEX_COUNT;
		double yEnd = yStart + this.VERTEX_COUNT;

		int xResolution = this.VERTEX_COUNT;
		int yResolution = this.VERTEX_COUNT;
		int x = (int) (xStart + (i * (xEnd - xStart) / xResolution));
		int y = (int) (yStart + (j * (yEnd - yStart) / yResolution));*/
		int x = (int)(i + this.tranform.getX());
		int y = (int)(j + this.tranform.getZ());
		
		return (float) (World.testingNoise.getNoise(x, y, 0) * this.MAX_HEIGHT);
	}
	
	public Transform getTranform() {
		return tranform;
	}


	public RawModel getModel() {
		return model;
	}


	public ModelTexture getTexture() {
		return texture;
	}
	
}
