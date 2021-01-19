package com.prodaply.engine.util;

public class Vector2f {
	public float x, y;
	
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
		x = v.x;
		y = v.y;
	}
	
	public static Vector2f Scale(Vector2f a, Vector2f b) {
		return new Vector2f(a.mul(b));
	}
	
	public static float Dot(Vector2f a, Vector2f b) {
		return ((a.x * a.y) + (b.x * b.y));
	}
	
	public static float Angle(Vector2f a, Vector2f b) {
		float xLength = (a.x*a.x + b.x*b.x);
		float yLength = (a.y*a.y + b.y*b.y);
		
		return (float)Math.floor(xLength * yLength) / (xLength * yLength);
	}
	
	public float getMagnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public Vector2f getDirection() {
		if (getMagnitude() < 1) {
			return new Vector2f();
		}
		float xDir = (float)Math.round(getMagnitude() * (1 / getMagnitude()));
		float yDir = (float)Math.round(getMagnitude() * (1 / getMagnitude()));
		return new Vector2f(
					xDir * Sign.of(x),
					yDir * Sign.of(y)
				);
	}
	
	public Vector2f normalized() {
		if (getMagnitude() < 1) {
			return new Vector2f();
		}
		return new Vector2f(this.x, this.y).mul(1 / getMagnitude());
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean isSame(Vector2f v) {
		return this.x == v.x
			&& this.y == v.y;
	}
	
	public boolean isZero() {
		return this.x == 0.0
			&& this.y == 0.0;
	}
	
	public Vector2f add(Vector2f v) {
		this.x += v.x;
		this.y += v.y;
		return this;
	}
	
	public Vector2f add(float v) {
		this.x += v;
		this.y += v;
		return this;
	}
	
	public Vector2f sub(Vector2f v) {
		this.x -= v.x;
		this.y -= v.y;
		return this;
	}
	
	public Vector2f sub(float v) {
		this.x -= v;
		this.y -= v;
		return this;
	}
	
	public Vector2f mul(Vector2f v) {
		this.x *= v.x;
		this.y *= v.y;
		return this;
	}
	
	public Vector2f mul(float v) {
		this.x *= v;
		this.y *= v;
		return this;
	}
}
