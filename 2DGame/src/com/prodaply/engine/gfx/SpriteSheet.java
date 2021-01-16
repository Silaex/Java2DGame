package com.prodaply.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private int width, height;
	private int[] pixels;
	private BufferedImage image;

	public SpriteSheet(String path) {
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);

		image.flush();
	}

	// Get chunk of pixels of the spritesheet (a sprite)
	public int[] getPixelsChunk(int col, int row, int size) {
		int[] pixelsChunk = new int[size * size];
		int tpIndex = 0;
		int beginning = (col * size) + (row * size) * width;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int coord = j + i * width;
				pixelsChunk[tpIndex] = pixels[beginning + coord];
				tpIndex++;
			}
		}

		return pixelsChunk;
	}
}
