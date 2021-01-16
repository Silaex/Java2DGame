package com.prodaply.game.entity;

import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.gfx.SpriteSheet;

public class SpriteManager {
	public static final SpriteSheet SPRITESHEET = new SpriteSheet("/game_spritesheet.png");
	public static final Sprite PLAYER = new Sprite(SPRITESHEET.getPixelsChunk(0, 1, 16));
	public static final Sprite BOX = new Sprite(SPRITESHEET.getPixelsChunk(0, 7, 16));
}
