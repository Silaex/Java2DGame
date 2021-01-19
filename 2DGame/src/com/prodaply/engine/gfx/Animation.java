package com.prodaply.engine.gfx;

import java.util.ArrayList;

public class Animation {
	private Sprite sprite;
	private ArrayList<Sprite> sprites;
	private int animationSpeed;
	private int animationIndex;
	
	public Animation(Sprite sprite, ArrayList<Sprite> sprites, int animationSpeed) {
		this.sprites = sprites;
		this.animationSpeed = animationSpeed;
		this.animationIndex = 0;
		this.sprite = sprite;
	}
	
	public void play() {
		this.animationIndex++;
		int animationStep = animationIndex / animationSpeed;
		int row = animationStep % sprites.size();
		sprite.setPixels(sprites.get(row).getPixels());
	}
	
	public void reset() {
		this.animationIndex = 0;
	}
}
