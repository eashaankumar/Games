package gameEngine;
import static org.lwjgl.opengl.GL11.*;

public class Texture {

	private int id;
	
	public Texture(int id){
		this.id = id;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public int getID(){
		return this.id;
	}
	
	public String toString(){
		return "Texture: ID: " + id;
	}
	
}