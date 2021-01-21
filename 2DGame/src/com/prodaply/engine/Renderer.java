package com.prodaply.engine;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;

import com.prodaply.engine.gfx.Font;
import com.prodaply.engine.gfx.ImageRequest;
import com.prodaply.engine.gfx.Sprite;
import com.prodaply.engine.util.Vector2f;

public class Renderer {
	private int pixelsWidth, pixelsHeight;
	private int[] pixels;
	private int[] zBuffer;
	private int currentZIndex = 0;
	private final int TRANSPARENT_COLOR_HEX = 0xffff00ff; // It's pink
	private int backgroundColor = 0xff000000; // 0xffea9a62;

	private Font font;
	private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
	private boolean isAlphaProcessing = false;
	
	public Renderer(GameController gc) {
		pixelsWidth = GameController.getWidth();
		pixelsHeight = GameController.getHeight();
		pixels = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
		font = new Font("/font.png");
		zBuffer = new int[pixels.length];
	}
	
	// Clear the screen
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = this.backgroundColor;
			zBuffer[i] = 0;
		}
	}
	
	// Set a color to a pixel
	public void drawPixel(int x, int y, int value) {
		// Index of the pixel to draw
		int pixelIndex = x + y * pixelsWidth;
		
		int alpha = ((value >> 24) & 0xff);

		if ((x < 0 || x >= pixelsWidth || y < 0 || y >= pixelsHeight) || alpha == 0) {
			return;
		}
		
		if (zBuffer[pixelIndex] > currentZIndex) {
			return;
		} 
		
		zBuffer[pixelIndex] = currentZIndex;
		
		if (alpha == 255) {			
			pixels[pixelIndex] = value;
		} else {
			int pixelColor = pixels[pixelIndex];
			float alphaFactor = (alpha / 255f);
			
			int valueRedColor = (value >> 16) & 0xff;
			int redColor = (pixelColor >> 16) & 0xff;
			int valueGreenColor = (value >> 8) & 0xff;
			int greenColor = (pixelColor >> 8) & 0xff;
			int valueBlueColor = value & 0xff;
			int blueColor = pixelColor & 0xff;
			
			int red = redColor - (int)((redColor - valueRedColor) * alphaFactor);
			int green = greenColor - (int)((greenColor - valueGreenColor) * alphaFactor);
			int blue = blueColor - (int)((blueColor - valueBlueColor) * alphaFactor);
			
			pixels[pixelIndex] = (255 << 24 | red << 16 | green << 8 | blue);
		}
	}
	
	public void alphaProcessing() {
		isAlphaProcessing = true;
		Collections.sort(imageRequest);
		for (int i = 0; i < imageRequest.size(); i++) {
			ImageRequest ir = imageRequest.get(i);
			
			if (ir.sprite.getZIndex() > currentZIndex) {
				setZIndex(ir.zIndex);
			}
			drawSprite(ir.sprite, ir.offsetX, ir.offsetY, ir.sprite.getDirection());
		}
		isAlphaProcessing = false;
		imageRequest.clear();
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
	public void drawSprite(Sprite sprite, int offsetX, int offsetY, Vector2f direction) {
		
		if (direction.x == -1f) {
			for (int i = 0; i < sprite.getH(); i++) {
				for (int j = 0; j < sprite.getW() / 2; j++) {
					int savePixel = sprite.getPixels()[j + i * sprite.getW()];
					int flippedXIndex = ((i + 1) * sprite.getW() - 1) - j;
					sprite.getPixels()[j + i * sprite.getW()] = sprite.getPixels()[flippedXIndex];
					sprite.getPixels()[flippedXIndex] = savePixel;
				}
			}
		}
		
		if (sprite.hasAlpha() && !isAlphaProcessing) {
			imageRequest.add(new ImageRequest(sprite, sprite.getZIndex(), offsetX, offsetY));
			return;
		}
		
		// Out of bounds pixels from window
		if (offsetX < -sprite.getW()) return;
		if (offsetX > pixelsWidth) return;
		if (offsetY < -sprite.getH()) return;
		if (offsetY > pixelsHeight) return;
		
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
	
	public void drawSprite(Sprite sprite, Vector2f position) {
		this.drawSprite(sprite, (int)position.x, (int)position.y, sprite.getDirection());
	}
	
	public void setZIndex(int zIndex) {
		this.currentZIndex = zIndex;
	}
	
	public void setBackgroundColor(int bg) {
		this.backgroundColor = bg;
	}

}
