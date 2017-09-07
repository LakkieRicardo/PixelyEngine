package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.graphics.renders.RenderEngineShaded;
import net.lakkie.pixely.graphics.shader.generic.enums.GenericLightingGenerationMode;
import net.lakkie.pixely.graphics.shader.generic.enums.GenericLightingMode;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentPixel;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.utils.ColorChannels;

public class GenericShaderPixel extends GenericShaderComponentBase implements IShaderComponentPixel {

	public static final int MAX_PIXEL_DISTANCE = Application.targetWidth / 2;
	public static final int DISTANCE_FACTOR = 4;
	private RenderEngineShaded render;
	public GenericLightingMode lightingMode = GenericLightingMode.ADDITIVE;

	public void init() {
		render = (RenderEngineShaded) Application.renderEngine;
	}

	public void accept(Sprite sprite, int x, int y, int vx, int vy) {
		int color = sprite.pixels[x + y * sprite.width];
		color = getColor(color, vx, vy);
		this.pixels[vx + vy * Application.renderEngine.viewport.w] = color;
	}
	
	public int getColor(int color, int vx, int vy) {
		if (GenericShader.genMode == GenericLightingGenerationMode.PIXEL_LEVEL) {
			int distance = getShaderDistance(this.render.lightSource, vx, vy);
			color = changeColor(color, distance);
		}
		if (GenericShader.genMode == GenericLightingGenerationMode.SPRITE_LEVEL) {
			color = changeColor(color, GenericShader.currentShade);
		}
		if (GenericShader.genMode == GenericLightingGenerationMode.SPRITE_PIXEL_ADD_LEVEL) {
			int distance = getShaderDistance(this.render.lightSource, vx, vy);
			color = changeColor(color, distance + GenericShader.currentShade);
		}
		return color;
	}
	
	public int changeColor(int color, int factor) {
		// Using a switch statement here decreases performance severely
		if (lightingMode == GenericLightingMode.ADDITIVE) {
			color = ColorChannels.addColor(color, factor);
		} else if (lightingMode == GenericLightingMode.MULTIPLICATIVE) {
			color = ColorChannels.multiplyColor(color, factor);
		}
		return color;
	}

	public static int getShaderDistance(Vector2i lightSource, int vx, int vy) {
		int dx = lightSource.x - vx;
		int dy = lightSource.y - vy;

		dx = Math.abs(dx);
		dy = Math.abs(dy);

		int result = dx + dy;
		return (MAX_PIXEL_DISTANCE - result) / DISTANCE_FACTOR;
	}

}
