package com.prodaply.engine;

public abstract class AbstractGameObject extends GameObject {
	public abstract void Start();
	public abstract void FixedUpdate(float dt);
	public abstract void Update();
}
