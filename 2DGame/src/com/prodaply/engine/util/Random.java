package com.prodaply.engine.util;

public class Random {
	public static int Int(int min, int max) {
		return (int)Math.floor(Math.random() * (max - min)) + min;
	}
}
