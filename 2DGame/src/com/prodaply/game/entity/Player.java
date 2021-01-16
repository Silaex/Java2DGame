package com.prodaply.game.entity;

import com.prodaply.engine.util.Vector2f;

public class Player extends Mob {
	private Vector2f speed;

	public Player(Vector2f position) {
		super(SpriteManager.PLAYER, position);
		speed = new Vector2f(60);
	}
	
	public void move(Vector2f direction) {
		// We move only if there is a direction right ? Seems normal
		if (!direction.isSame(new Vector2f(0, 0))) {
			this.setPosition(this.getPosition().add(direction.mul(this.speed)));
		}
	}
	
	public Vector2f getSpeed() {
		System.out.println("oaihzeh");
		return speed;
	}
	
	public void setSpeed(Vector2f speed) {
		this.speed = speed;
	}
}
