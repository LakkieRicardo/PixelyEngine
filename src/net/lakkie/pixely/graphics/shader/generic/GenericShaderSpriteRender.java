package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.graphics.renders.RenderEngineShaded;
import net.lakkie.pixely.graphics.shader.generic.enums.GenericLightingGenerationMode;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentSpriteRender;
import net.lakkie.pixely.graphics.tex.Sprite;

public class GenericShaderSpriteRender extends GenericShaderComponentBase implements IShaderComponentSpriteRender {

	private GenericShaderBounds bounds;
	private RenderEngineShaded render;

	public void init() {
		this.bounds = this.shader.getComponentImplementation(GenericShaderBounds.class);
		this.render = (RenderEngineShaded) Application.renderEngine;
	}

	public void accept(Sprite sprite, int offX, int offY) {

		// Check if out of bounds
		if (bounds.isOutOfBoundsBottomRight(offX, offY) || bounds.isOutOfBoundsTopLeft(offX, offY, sprite)) {
			// If so, return out of method
			return;
		}

		if (GenericShader.genMode != GenericLightingGenerationMode.PIXEL_LEVEL) {
			int vx = offX - Application.renderEngine.viewport.x + sprite.width / 2;
			int vy = offY - Application.renderEngine.viewport.y + sprite.height / 2;
			GenericShader.currentShade = GenericShaderPixel.getShaderDistance(this.render.lightSource, vx, vy);
		}

		for (int y = 0; y < sprite.height; y++) {
			int viewPosY = offY - Application.renderEngine.viewport.y + y;
			if (viewPosY < 0 || viewPosY >= Application.renderEngine.viewport.h)
				continue;

			for (int x = 0; x < sprite.width; x++) {
				int viewPosX = offX - Application.renderEngine.viewport.x + x;
				if (viewPosX < 0 || viewPosX >= Application.renderEngine.viewport.w)
					continue;

				// Draw the pixel
				this.shader.getPixel().accept(sprite, x, y, viewPosX, viewPosY);
			}
		}

		Application.registerSpriteRender();
		GenericShader.currentShade = 0;
	}

}
