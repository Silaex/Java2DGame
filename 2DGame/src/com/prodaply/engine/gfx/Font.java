package com.prodaply.engine.gfx;

public class Font {
	private Sprite font;
	private int[] offsets;
	private int[] widths;
	private final String characters = " _0123456789abcdefghijklmnopqrstuvwxyzABCDEFHIJKLMNOPQRSTUVWXYZ?!.,;:/$*+=()[]{}#\"'-<>";
	
	public Font(String path) {
		font = new Sprite(path, 0);
		
		offsets = new int[characters.length()];
		widths = new int[characters.length()];
		
		int unicode = 0;
		
		for (int i = 0; i < font.getW(); i++) {
			if (font.getPixels()[i] == 0xff0000ff) {
				offsets[unicode] = i;
			}
			if (font.getPixels()[i] == 0xffff0000) {
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
	}
	
	public Sprite getFont() {
		return font;
	}
	
	public String getCharacters() {
		return characters;
	}
	
	public int[] getOffsets() {
		return offsets;
	}
	
	public int[] getWidths() {
		return widths;
	}
}
