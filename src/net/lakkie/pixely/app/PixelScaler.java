package net.lakkie.pixely.app;

import static net.lakkie.pixely.app.Application.*;

import java.awt.Graphics2D;

import net.lakkie.pixely.math.Vector2i;

/**
 * 
 * Handles translation between world points and canvas points, for drawing using
 * {@link Graphics2D} on top of level sprites.
 * 
 * @author Diego
 *
 */
public class PixelScaler
{

	private PixelScaler()
	{
	}

	/**
	 * Alias for {@link PixelScaler#scaleX()}
	 */
	public static float sx()
	{
		return scaleX();
	}

	/**
	 * Alias for {@link PixelScaler#scaleY()}
	 */
	public static float sy()
	{
		return scaleY();
	}

	public static float scaleX()
	{
		return (float) (targetWidth) / (float) (renderEngine.viewport.w);
	}

	public static float scaleY()
	{
		return (float) (targetHeight) / (float) (renderEngine.viewport.h);
	}

	public static Vector2i levelPointToScreenPoint(Vector2i point)
	{
		float lx = (float) point.x;
		float ly = (float) point.y;
		lx *= sy();
		ly *= sy();
		return new Vector2i((int) lx, (int) ly);
	}

}
