package com.prodaply.engine.gfx;

public class ImageRequest implements Comparable<ImageRequest> {
	public Sprite sprite;
	public int zIndex;
	public int offsetX, offsetY;
	
	public ImageRequest(Sprite sprite, int zIndex, int offsetX, int offsetY) {
		this.sprite = sprite;
		this.zIndex = zIndex;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	@Override
	public int compareTo(ImageRequest ir) {
		int compareZIndex = (ir).zIndex;
		return this.zIndex - compareZIndex;
	}
	
//	public ImageRequest(Image image, int zIndex, int offsetX, int offsetY) {
//		this.image = image;
//		this.zIndex = zIndex;
//		this.offsetX = offsetX;
//		this.offsetY = offsetY;
//	}
}
