package net.lakkie.pixely.graphics.renders;

import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.utils.Vector4;

public class RenderEngineTest extends RenderEngine {

	public RenderEngineTest(Vector4 viewport) {
		super(viewport, "test");
	}

	public void renderSprite(int worldX, int worldY, Sprite sprite) {
		// Out of bounds, right, bottom
		if (worldX - viewport.x >= viewport.x + viewport.w || worldY - viewport.y >= viewport.y + viewport.h)
			return;
		
		// Out of bounds, left, top
		if (worldX - viewport.x + sprite.width < 0 || worldY - viewport.y + sprite.height < 0)
			return;
		
		for (int y = 0; y < sprite.height; y++) {
			int viewPosY = worldY - viewport.y + y;
			if (viewPosY < 0 || viewPosY >= viewport.h)
				continue;
			
			for (int x = 0; x < sprite.width; x++) {
				int viewPosX = worldX - viewport.x + x;
				if (viewPosX < 0 || viewPosX >= viewport.w)
					continue;
				
				// Draw the pixel
				this.pixels[viewPosX + viewPosY * viewport.w] = sprite.pixels[x + y * sprite.width];
			}
		}
	}

}
