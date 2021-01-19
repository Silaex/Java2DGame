package com.prodaply.engine;

public abstract class Component {
	protected GameObject gameObject = null;
	
	public abstract void start();
	public abstract void update(float dt);
	public abstract void render(Renderer r);
}
