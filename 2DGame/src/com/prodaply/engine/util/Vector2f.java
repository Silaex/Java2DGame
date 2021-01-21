package com.prodaply.engine.util;

public class Vector2f {
	public float x, y;
	public static Vector2f up = new Vector2f(0, -1);
	public static Vector2f right = new Vector2f(1, 0);
	
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
	
	public Vector2f inverse() {
		return new Vector2f(-this.x, -this.y);
	}
	
	public static float Dot(Vector2f a, Vector2f b) {
		return ((a.x * b.x) + (a.y * b.y));
	}
	
	public static float Angle(Vector2f a, Vector2f b) {
		return (float)Dot(a.normalized(), b.normalized()); //Dot(a, b) / new Vector2f(a.normalized()).mul(b.normalized()).getMagnitude();
	}
	
	public float getMagnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public static float Distance(Vector2f a, Vector2f b) {
		Vector2f dxy = new Vector2f(a).sub(b);
		float distance = dxy.getMagnitude();
		return distance;
	}
	
	public Vector2f getDirection() {
		if (this.isZero()) {
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
		float coeff = 1 / getMagnitude();
		float nx = (this.x * coeff);
		float ny = (this.y * coeff);
		return new Vector2f(nx, ny);
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
