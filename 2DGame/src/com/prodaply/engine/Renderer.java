package com.prodaply.engine;

import java.awt.image.DataBufferInt;

import com.prodaply.engine.gfx.Font;
import com.prodaply.engine.gfx.Sprite;

public class Renderer {
	private int pixelsWidth, pixelsHeight;
	private int[] pixels;
	private final int TRANSPARENT_COLOR_HEX = 0xffff00ff; // It's pink
	private final int BACKGROUND_COLOR = 0xffea9a62;
	private Font font;
	
	public Renderer(GameController gc) {
		pixelsWidth = gc.getWidth();
		pixelsHeight = gc.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		font = new Font("/font.png");
	}
	
	// Clear the screen
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = BACKGROUND_COLOR;
		}
	}
	
	// Set a color to a pixel
	public void drawPixel(int x, int y, int value) {
		if ((x < 0 || x >= pixelsWidth || y < 0 || y >= pixelsHeight) || value == TRANSPARENT_COLOR_HEX) {
			return;
		}

		pixels[x + y * pixelsWidth] = value;
	}
	
	public void drawText(String text, int offsetX, int offsetY, int color) {
		int offset = 0;
		int drawOffset = 0;
		
		for (int i = 0; i < text.length(); i++) {
			// Getting characters index
			offset = font.getCharacters().indexOf(text.charAt(i));
			for (int y = 0; y < font.getFont().getH(); y++) {
				for (int x = 0; x < font.getWidths()[offset]; x++) {
					if (font.getFont().getPixels()[(x + font.getOffsets()[offset]) + y * font.getFont().getW()] == 0xffffffff) {
						drawPixel(x + offsetX + drawOffset, y + offsetY, color);
					}
				}
			}
			
			drawOffset += font.getWidths()[offset];
		}
	}
	
	// Draw a single Sprite
	public void drawSprite(Sprite sprite, int offsetX, int offsetY) {
		int bX = 0;
		int bY = 0;
		int visibleW = sprite.getW();
		int visibleH = sprite.getH();
		
		// Too far left
		if (offsetX < 0) {
			bX = -offsetX;
		}
		// Too far top
		if (offsetY < 0) {
			bY = -offsetY;
		}
		// Too far right
		if ((offsetX + visibleW) >= pixelsWidth) {
			visibleW -= visibleW + offsetX - pixelsWidth;
		}
		// Too far bottom
		if ((offsetY + visibleH) >= pixelsHeight) {
			visibleH -= visibleH + offsetY - pixelsHeight;
		}
				
		for (int y = bY; y < visibleH; y++) {
			for (int x = bX; x < visibleW; x++) {
				drawPixel(x + offsetX, y + offsetY, sprite.getPixels()[x + y * sprite.getW()]);
			}
		}
	}
}
