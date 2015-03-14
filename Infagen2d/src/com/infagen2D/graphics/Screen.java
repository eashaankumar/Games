package com.infagen2D.graphics;

import java.awt.Point;

public class Screen {

	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	public int[] pixels;
			
	private int xOffset = 0;
	private int yOffset = 0;

	public int width, height;

	public SpriteSheet sheet;

	public Screen(int w, int h, SpriteSheet sheet) {
		this.width = w;
		this.height = h;
		this.sheet = sheet;
		
		pixels = new int[width * height];
		
	}
	
	/**
	 * tile is for actual tile
	 * @param xPos
	 * @param yPos
	 * @param tile
	 * @param color
	 */
	public void render(int xPos, int yPos, int tile, int color, boolean mirrorX, boolean mirrorY){
		xPos -= xOffset;
		yPos -= yOffset;

		int xTile = tile % 32;
		int yTile = tile / 32;

		int tileOffset = (xTile << 3) + (yTile <<3) * sheet.width;
		for(int y = 0; y < 8; y++){
			if(y+yPos < 0 || y + yPos >= height)continue;
			int ySheet = y;
			if(mirrorY)ySheet = 7-y;
			for(int x = 0; x < 8; x++){
				if(x+xPos < 0 || x + xPos >= width)continue;
				int xSheet = x;
				if(mirrorX)xSheet = 7-x;
				int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;
				if(col < 255)pixels[(x + xPos) + (y + yPos) * width] = col; 
			}
		}
	}
	
	public Point getCenter(){
		return new Point(xOffset + width/2 , yOffset + height/2);
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	
	public void setOffset(int xO, int yO){
		this.setxOffset(xO);
		this.setyOffset(yO);
	}
	
	public void moveOffset(int dxO, int dyO){
		this.xOffset += dxO;
		this.yOffset += dyO;
	}
}
