package com.prodaply.engine;

public abstract class AbstractGameController {
	
	public abstract void update(GameController gc, float deltaTime);
	public abstract void render(GameController gc, Renderer renderer);
}
