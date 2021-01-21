package com.prodaply.game.entity;

import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.gfx.SpriteSheet;

public class SpriteManager {
	public static final SpriteSheet SPRITESHEET = new SpriteSheet("/game_spritesheet.png", 16, 16);
	public static final Sprite PLAYER = SPRITESHEET.getSprite(0, 2, 16, 16, 0);
	public static final Sprite BOX = SPRITESHEET.getSprite(0, 7, 16, 16, 0);
	public static final Sprite EARTH = SPRITESHEET.getSprite(2, 7, 16, 16, 0);
	public static final Sprite RED_CIRCLE = SPRITESHEET.getSprite(1, 8, 16, 16, 0);
	public static final Sprite MOON = SPRITESHEET.getSprite(1, 7, 8, 8, 0);
}
