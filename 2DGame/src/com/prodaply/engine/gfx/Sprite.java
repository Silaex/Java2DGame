package com.prodaply.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.prodaply.engine.util.Vector2f;

public class Sprite {
	private int w, h;
	private int[] pixels;
	private Vector2f direction = new Vector2f(1, 1);
	private int zIndex = 0;
	
	public Sprite(String path, int zIndex) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Sprite.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = image.getWidth();
		h = image.getHeight();
		pixels = image.getRGB(0, 0, w, h, null, 0, w);
		this.zIndex = zIndex;
		
		image.flush();
	}

	public Sprite(int[] p, int zIndex) {
		w = (int)Math.sqrt(p.length);
		h = (int)Math.sqrt(p.length);
		pixels = p;
		
		this.zIndex = zIndex;
	}
	
	public Sprite(int[] p) {
		w = (int)Math.sqrt(p.length);
		h = (int)Math.sqrt(p.length);
		pixels = p;
		
		this.zIndex = -999;
	}
	
	public Vector2f getDirection() {
		return direction;
	}
	
	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}
	
	public boolean hasAlpha() {
		return this.zIndex != -999;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
}
