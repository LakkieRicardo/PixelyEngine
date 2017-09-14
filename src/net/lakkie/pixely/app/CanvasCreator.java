package net.lakkie.pixely.app;

import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class CanvasCreator
{

	/**
	 * Simplifies instantiating new {@link Canvas}'s.
	 * 
	 * @param width
	 *            The width of the canvas
	 * @param height
	 *            The height of the canvas
	 * @return
	 */
	public static Canvas createCanvas(int width, int height)
	{
		return createCanvas(width, height, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
	}

	/**
	 * Simplifies instantiating new {@link Canvas}'s. This specifies a device to
	 * display graphics with.<br>
	 * 
	 * @param width
	 *            The width of the canvas
	 * @param height
	 *            The height of the canvas
	 * @param graphicDisplayer
	 *            The graphics device. Used for settings.
	 * @return The instantiated canvas.
	 */
	public static Canvas createCanvas(int width, int height, GraphicsDevice graphicDisplayer)
	{
		Canvas result = new Canvas(graphicDisplayer.getDefaultConfiguration());
		result.setSize(width, height);
		return result;
	}

}
