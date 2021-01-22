package com.prodaply.engine.util;

public class Sign {
	public static int of(float value) {
		if (value < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public static Vector2f of(Vector2f value) {
		Vector2f signs = new Vector2f(1, 1);
		
		if (value.x < 0.0f) {
			signs.x = -1;
		}
		if (value.y < 0.0f) {
			signs.y = -1;
		}
		
		return signs;
	}
}
