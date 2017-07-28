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
	
	public Sprite(String loadPath, String name) {
		this(loadPath, TextureLoadMode.DIRECT_BUFFERED_IMAGE, name);
	}

	public String getName() {
		return this.name;
	}

}
