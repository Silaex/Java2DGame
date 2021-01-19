package com.prodaply.game.entity;

import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.gfx.SpriteSheet;

public class SpriteManager {
	public static final SpriteSheet SPRITESHEET = new SpriteSheet("/game_spritesheet.png");
	public static final Sprite PLAYER = SPRITESHEET.getSprite(0, 2, 16, 16, 0);
	public static final Sprite BOX = SPRITESHEET.getSprite(0, 7, 16, 16, 0);
}
