package net.lakkie.pixely.graphics.tex;

import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;

public class Sprite extends SimplifiedImage implements Nameable {

	public static final Registry<Sprite> sprites = new Registry<Sprite>();

	private final String name;

	public Sprite(String loadPath, TextureLoadMode mode, String name) {
		super(loadPath, mode);
		this.name = name;
		sprites.submit(this);
	}

	public Sprite(int color, int w, int h, String name) {
		super(w, h, getColoredPixelData(color, w, h));
		this.name = name;
		sprites.submit(this);
	}

	public Sprite(String loadPath, String name) {
		this(loadPath, TextureLoadMode.DIRECT_BUFFERED_IMAGE, name);
	}

	public String getName() {
		return this.name;
	}

	public static int[] getColoredPixelData(int color, int w, int h) {
		int[] result = new int[w * h];
		for (int i = 0; i < result.length; i++) {
			result[i] = color;
		}
		return result;
	}

}
