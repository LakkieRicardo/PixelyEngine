package net.lakkie.pixely.graphics.renders;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.math.Vector4;

public class RenderEngineBasic extends RenderEngine {

	public static final String name = "basic";
	
	public RenderEngineBasic(Vector4 viewport) {
		super(viewport, RenderEngineBasic.name);
	}

	public void renderSprite(int worldX, int worldY, Sprite sprite) {
		// Out of bounds, right, bottom
		if (isOutOfBoundsBottomRight(worldX, worldY)) {
			return;
		}

		// Out of bounds, left, top
		if (isOutOfBoundsTopLeft(worldX, worldY, sprite)) {
			return;
		}

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
		Application.registerSpriteRender();
	}
	
	public boolean isOutOfBoundsBottomRight(int worldX, int worldY) {
		return worldX >= viewport.w + viewport.x || worldY >= viewport.h + viewport.y;
	}
	
	public boolean isOutOfBoundsTopLeft(int worldX, int worldY, Sprite sprite) {
		return worldX - viewport.x + sprite.width < 0 || worldY - viewport.y + sprite.height < 0;
	}

	public void renderTile(Tile tile) {
		renderSprite(tile.pos.x, tile.pos.y, tile.sprite);
	}
	
	public void renderEntity(Entity entity) {
		entity.renderer.render(Application.ctx, entity);
	}

}
