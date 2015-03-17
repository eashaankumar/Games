package com.infagen2D.level;

import com.infagen2D.graphics.Screen;

public class BasicTile extends Tile {

	protected int tileId;
	protected int tileColour;

	public BasicTile(int id, int x, int y, int tileColour, int levelColor) {
		super(id, false, false, levelColor);
		this.tileId = x + y;
		this.tileColour = tileColour;
	}

	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColour, 0x00, 1);
	}

}