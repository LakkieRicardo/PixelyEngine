package net.lakkie.pixely.graphics.tex;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.lakkie.pixely.graphics.tex.loadBuffered.BufferedTextureLoader;
import net.lakkie.pixely.graphics.tex.loadBuffered.IntBufferExtractor;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectImage;
import net.lakkie.pixely.graphics.tex.loadDirect.DirectTextureLoader;

public class SimplifiedImage {

	public int width, height;
	public final int[] pixels;

	public SimplifiedImage(int w, int h, int[] pixels) {
		this.width = w;
		this.height = h;
		this.pixels = pixels;
	}

	public SimplifiedImage(String loadPath, TextureLoadMode mode) {
		this.pixels = null;
		switch (mode) {
		case INTO_BUFFER_READ_BUFFER:
			IntBufferExtractor tex = BufferedTextureLoader.asExtractor(BufferedTextureLoader.loadTexture(loadPath));
			this.width = tex.readAndMoveInteger();
			this.height = tex.readAndMoveInteger();
			__forceChangePixels(tex.readAndMoveIntegerArray(tex.getRemainingIntegers()), this);
			break;
		case DIRECT_BUFFERED_IMAGE:
			DirectImage dImg = DirectTextureLoader.asDirectImage(DirectTextureLoader.readImage(loadPath));
			this.width = dImg.width();
			this.height = dImg.height();
			__forceChangePixels(dImg.pixels(), this);
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

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Uses java reflection to change pixels on a {@link SimplifiedImage}. Use
	 * sparingly.
	 */
	public static void __forceChangePixels(int[] pixels, SimplifiedImage obj) {
		try {
			__modifiersField.setInt(__pixelField, Modifier.PUBLIC);
			__pixelField.set(obj, pixels);
			__modifiersField.setInt(__pixelField, Modifier.PUBLIC | Modifier.FINAL);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private static final Field __pixelField;
	private static final Field __modifiersField;

	static {
		Field _modifiersField = null;
		Field _pixelField = null;
		try {
			_modifiersField = Field.class.getDeclaredField("modifiers");
			_pixelField = SimplifiedImage.class.getDeclaredField("pixels");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		__pixelField = _pixelField;
		__modifiersField = _modifiersField;
		__modifiersField.setAccessible(true);
		__pixelField.setAccessible(true);
	}

}
