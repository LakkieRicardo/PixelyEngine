package net.lakkie.pixely.utils;

import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.tex.Sprite;

public class Registries {

	public static void registerRenderEngine(RenderEngine engine) {
		RenderEngine.engines.enter(engine);
	}
	
	public static void registerSprite(Sprite sprite) {
		Sprite.sprites.enter(sprite);
	}
	
}
