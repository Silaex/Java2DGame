package com.prodaply.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private int w, h;
	private int[] pixels;
	
	public Sprite(String path) {
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
		
		image.flush();
	}
	
	public Sprite(int[] p) {
		w = (int)Math.sqrt(p.length);
		h = (int)Math.sqrt(p.length);
		pixels = p;
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
}
