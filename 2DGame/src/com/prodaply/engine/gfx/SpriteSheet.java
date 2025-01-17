package com.prodaply.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private int width, height;
	private int[] pixels;
	public int offsetX;
	public int offsetY;
	private BufferedImage image;

	public SpriteSheet(String path, int offX, int offY) {
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		offsetX = offX;
		offsetY = offY;

		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);

		image.flush();
	}

	// Get chunk of pixels of the spritesheet (a sprite)
	public int[] getPixelsChunk(int col, int row, int xSize, int ySize) {
		int[] pixelsChunk = new int[xSize * ySize];
		int tpIndex = 0;
		int beginning = (col * this.offsetX) + (row * this.offsetY) * width;

		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				int coord = j + i * width;
				pixelsChunk[tpIndex] = pixels[beginning + coord];
				tpIndex++;
			}
		}

		return pixelsChunk;
	}
	
	public Sprite getSprite(int col, int row, int xSize, int ySize, int zIndex) {
		int[] pixelsChunk = new int[xSize * ySize];
		int tpIndex = 0;
		int beginning = (col * this.offsetX) + (row * this.offsetY) * width;
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				System.out.println(j);
				int coord = j + i * width;
				pixelsChunk[tpIndex] = pixels[beginning + coord];
				tpIndex++;
			}
		}

		return new Sprite(pixelsChunk, 0);
	}
}
