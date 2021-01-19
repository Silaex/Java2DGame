package com.prodaply.engine.util;

public class Sign {
	public static int of(float value) {
		if (value < 0) {
			return -1;
		} else {
			return 1;
		}
	}
}
