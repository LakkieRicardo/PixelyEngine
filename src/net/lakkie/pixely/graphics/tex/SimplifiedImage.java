package net.lakkie.pixely.graphics.tex;

import net.lakkie.pixely.graphics.tex.loadBuffered.BufferedTextureLoader;
import net.lakkie.pixely.graphics.tex.loadBuffered.IntBufferExtractor;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectImage;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectTextureLoader;

public class SimplifiedImage {

	public int width, height;
	public int[] pixels;

	public SimplifiedImage(int w, int h, int[] pixels) {
		this.width = w;
		this.height = h;
		this.pixels = pixels;
	}

	public SimplifiedImage(String loadPath, TextureLoadMode mode) {
		switch (mode) {
		case INTO_BUFFER_READ_BUFFER:
			IntBufferExtractor tex = BufferedTextureLoader.asExtractor(BufferedTextureLoader.loadTexture(loadPath));
			this.width = tex.readAndMoveInteger();
			this.height = tex.readAndMoveInteger();
			this.pixels = tex.readAndMoveIntegerArray(tex.getRemainingIntegers());
			break;
		case DIRECT_BUFFERED_IMAGE:
			DirectImage dImg = DirectTextureLoader.asDirectImage(DirectTextureLoader.readImage(loadPath));
			this.width = dImg.width();
			this.height = dImg.height();
			this.pixels = dImg.pixels();
		}
	}
	
	public SimplifiedImage(String loadPath) {
		this(loadPath, TextureLoadMode.DIRECT_BUFFERED_IMAGE);
	}

	public void set(int x, int y, int color) {
		this.pixels[x + y * width] = color;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
