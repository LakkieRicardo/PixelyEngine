package net.lakkie.pixely.utils;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.graphics.RenderUtility;
import net.lakkie.pixely.graphics.tex.SimplifiedImage;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.graphics.tex.TextureLoadMode;

public class Spritesheet
{

	private Spritesheet()
	{
	}

	public static List<Sprite> loadSpritesheet(SpriteMapping mapping, String filepath, TextureLoadMode mode)
	{
		// Create a result list
		List<Sprite> sprites = new ArrayList<Sprite>();
		// Load the image into memory
		SimplifiedImage image = new SimplifiedImage(filepath, mode);
		// Go through the sprites
		for (SpritesheetSprite ssSprite : mapping)
		{
			// Copy out the new sprite and add it to the result list
			int[] pixels = RenderUtility.copyOut(image.pixels, ssSprite.pos.x, ssSprite.pos.y, ssSprite.pos.w, ssSprite.pos.h, image.width, image.height);
			Sprite sprite = new Sprite(pixels, ssSprite.pos.w, ssSprite.pos.h, ssSprite.name);
			sprites.add(sprite);
		}
		// Return the result list
		return sprites;
	}

	public static List<Sprite> loadSpritesheet(SpriteMapping mapping, String filepath)
	{
		return loadSpritesheet(mapping, filepath, TextureLoadMode.DIRECT_BUFFERED_IMAGE);
	}

	public static class SpriteMapping extends ArrayList<SpritesheetSprite>
	{

		private static final long serialVersionUID = -8340987138052012034L;

		public SpriteMapping(int capacity)
		{
			super(capacity);
		}

		public SpriteMapping()
		{
			super();
		}

	}

}
