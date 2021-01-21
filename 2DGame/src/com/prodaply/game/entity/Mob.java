package com.prodaply.game.entity;

import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.util.Vector2f;

public class Mob  {
	private Sprite sprite;
	
	public Mob(Sprite sp, Vector2f position) {
		this.sprite = sp;
	}
	
	public Mob(Sprite sp) {
		this.sprite = sp;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
