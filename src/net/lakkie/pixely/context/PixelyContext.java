package net.lakkie.pixely.context;

import java.awt.Canvas;
import java.util.HashMap;
import java.util.Map;

import net.lakkie.pixely.graphics.RenderEngine;

public class PixelyContext
{

	public static final String canvas = "canvas";
	public static final String ups = "updates_per_second";
	public static final String width = "width";
	public static final String height = "height";
	public static final String debug = "debug";
	public static final String renderEngine = "render_engine";
	public static final String graphics = "graphics";
	public static final String maxSprites = "max_sprites";

	Map<String, Object> fields;

	public PixelyContext(IContextProvider provider)
	{
		this.fields = new HashMap<String, Object>();
		provider.setup(this);
	}

	public Object get(String name)
	{
		return fields.get(name);
	}

	public void set(String name, Object value)
	{
		fields.put(name, value);
	}

	public void put(Canvas canvas, double ups, int width, int height, boolean debug, RenderEngine engine, int maxSprites)
	{
		fields.put(PixelyContext.canvas, canvas);
		fields.put(PixelyContext.ups, ups);
		fields.put(PixelyContext.width, width);
		fields.put(PixelyContext.height, height);
		fields.put(PixelyContext.debug, debug);
		fields.put(PixelyContext.renderEngine, engine);
		fields.put(PixelyContext.maxSprites, maxSprites);
	}

	public double getUPS()
	{
		return (double) fields.get(PixelyContext.ups);
	}

	public Canvas getCanvas()
	{
		return (Canvas) fields.get(PixelyContext.canvas);
	}

	public int getWidth()
	{
		return (int) fields.get(PixelyContext.width);
	}

	public int getHeight()
	{
		return (int) fields.get(PixelyContext.height);
	}

	public int getMaxSprites()
	{
		return (int) fields.get(PixelyContext.maxSprites);
	}

	public boolean isDebugActive()
	{
		return (boolean) fields.get(PixelyContext.debug);
	}

	/**
	 * @return A copy of all of the fields.
	 */
	public Map<String, Object> getFields()
	{
		return new HashMap<>(this.fields);
	}

}
