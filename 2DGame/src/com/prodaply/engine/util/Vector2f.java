package com.prodaply.engine.util;

public class Vector2f {
	private float x, y;
	
	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f(float value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f v) {
		x = v.getX();
		y = v.getY();
	}
	
	public boolean isSame(Vector2f v) {
		return this.getX() == v.getX()
			&& this.getY() == v.getY();
	}
	
	public Vector2f add(Vector2f v) {
		this.x += v.getX();
		this.y += v.getY();
		return this;
	}
	
	public Vector2f sub(Vector2f v) {
		this.x -= v.getX();
		this.y -= v.getY();
		return this;
	}
	
	public Vector2f mul(Vector2f v) {
		this.x *= v.getX();
		this.y *= v.getY();
		return this;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
