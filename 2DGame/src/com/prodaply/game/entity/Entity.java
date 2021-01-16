package com.prodaply.game.entity;

import com.prodaply.engine.util.Vector2f;
import com.prodaply.game.level.Level;

public class Entity {
	private Vector2f position;
	private Level level;
	private boolean isRemoved = false;
	
	public void remove() {
		isRemoved = true;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Level getLevel() {
		return level;
	}

	public boolean isRemoved() {
		return isRemoved;
	}
	
}
