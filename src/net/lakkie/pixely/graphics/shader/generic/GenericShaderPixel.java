package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.graphics.renders.RenderEngineShaded;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentPixel;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.utils.ColorChannels;

public class GenericShaderPixel extends GenericShaderComponentBase implements IShaderComponentPixel {

	public static final int MAX_PIXEL_DISTANCE = Application.targetWidth / 2;
	public static final int DISTANCE_FACTOR = 4;
	private RenderEngineShaded render;
	public LightingMode lightingMode = LightingMode.ADDITIVE;

	public void init() {
		render = (RenderEngineShaded) Application.renderEngine;
	}

	public void accept(Sprite sprite, int x, int y, int vx, int vy) {
		int color = sprite.pixels[x + y * sprite.width];

		int distance = getDistance(vx, vy);
		// TODO: Switch case lowers the FPS by about 50, might be better or
		// worse on some computers, should test.
		if (lightingMode == LightingMode.ADDITIVE) {
			color = ColorChannels.addColor(color, distance);
		} else if (lightingMode == LightingMode.MULTIPLICATIVE) {
			color = ColorChannels.multiplyColor(color, distance);
		}

		this.pixels[vx + vy * Application.renderEngine.viewport.w] = color;
	}

	public int getDistance(int vx, int vy) {
		int dx = render.lightSource.x - vx;
		int dy = render.lightSource.y - vy;

		dx = Math.abs(dx);
		dy = Math.abs(dy);

		int result = dx + dy;
		return (MAX_PIXEL_DISTANCE - result) / DISTANCE_FACTOR;
	}

	public static enum LightingMode {
		ADDITIVE, MULTIPLICATIVE;
	}

}
