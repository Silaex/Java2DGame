package com.prodaply.engine;

public abstract class AbstractGameController {
	
	public abstract void fixedUpdate(GameController gc, float deltaTime);
	public abstract void update(GameController gc, Renderer renderer);
}
