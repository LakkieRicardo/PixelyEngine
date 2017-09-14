package net.lakkie.pixely.graphics.shader.i;

import net.lakkie.pixely.graphics.tex.Sprite;

public interface IShaderComponentPixel extends IShaderComponentBase
{

	/**
	 * Draws a pixel to the screen.
	 * 
	 * @param sprite
	 *            The sprite that contains all the color data.
	 * @param x
	 *            The X of the sprite.
	 * @param y
	 *            The Y of the sprite.
	 * @param vx
	 *            The calculated view offset of the sprite, x.
	 * @param vy
	 *            The calculated view offset of the sprite, y.
	 */
	void accept(Sprite sprite, int x, int y, int vx, int vy);

}
