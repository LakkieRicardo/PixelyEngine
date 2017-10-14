package net.lakkie.pixely.level;

import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.utils.Registry;

public class TileTemplate
{
	
	public static final Registry<Tile> tileTemplates = new Registry<Tile>();

	private final Sprite sprite;
	private final String name;
	private final Vector2i defaultPosition;
	
	public TileTemplate(Sprite sprite, String name, Vector2i defaultPosition)
	{
		this.sprite = sprite;
		this.name = name;
		this.defaultPosition = defaultPosition;
	}
	
	public Tile make(Level level)
	{
		return new Tile(level, this.sprite, this.defaultPosition, this.name);
	}
	
	public Tile make(Level level, Vector2i pos)
	{
		return new Tile(level, this.sprite, pos, this.name);
	}
	
	public Vector2i getDefaultPosition()
	{
		return defaultPosition;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
	
}
