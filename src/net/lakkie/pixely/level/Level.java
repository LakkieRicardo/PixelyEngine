package net.lakkie.pixely.level;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.i.IRenderable;
import net.lakkie.pixely.i.IUpdatable;

/**
 * 
 * <i>A level is a container that is tagged by entities and tiles, with only one
 * being loaded at a time.</i><br>
 * This basically just means that a <code>Level</code> is a container of
 * entities and tiles. Levels also implement {@link IUpdatable} and
 * {@link IRenderable}<br>
 * Another notable thing about levels is that they are not all loaded into
 * memory, and instead are loaded and unloaded. This is because we do not want
 * to waste space storing levels that are not going to be used when rendering or
 * updating. For this reason it is recommended to not use static member
 * variables, but instead use {@link PixelyContext} to store data.
 * 
 * @author Diego
 *
 */
public class Level implements IUpdatable, IRenderable
{

	private boolean unloaded = false;
	public final String name;

	public Level(String name)
	{
		this.name = name;
	}

	/**
	 * Prevents any method from this class from being successfully called. This also
	 * removes all tile and entity references to this level.
	 */
	public final void unload()
	{
		if (unloaded)
			return;
		clearReferencesTile();
		clearReferencesEntity();
		unloaded = true;
	}

	private final void clearReferencesTile()
	{
		if (unloaded)
			return;
		// TODO: Say something
		for (Tile tile : extractTilesFromLevel())
		{
			tile.level = null;
		}
	}

	private final void clearReferencesEntity()
	{
		if (unloaded)
			return;
		// TODO: Say something
		for (Entity entity : extractEntitiesFromLevel())
		{
			entity.level = null;
		}
	}

	/**
	 * This is the only method that will work be successfully called when the level
	 * is unloaded, for obvious reasons.
	 * 
	 * @return The unloaded variable, in a new {@link Boolean} class.
	 */
	public final Boolean isUnloaded()
	{
		return new Boolean(unloaded);
	}

	public final void switchLevel(Tile tile)
	{
		if (unloaded)
			return;
		tile.level = this;
	}

	public final void switchLevel(Entity entity)
	{
		if (unloaded)
			return;
		entity.level = this;
	}

	/**
	 * Extracts all the entities from the entity registry.
	 * 
	 * @return A new list of all of the entities in this level.
	 */
	public final List<Entity> extractEntitiesFromLevel()
	{
		if (unloaded)
			return null;
		List<Entity> result = new ArrayList<Entity>();
		for (Entity entity : Entity.entities)
		{
			if (entity.level == this)
			{
				result.add(entity);
			}
		}
		return result;
	}

	/**
	 * Extracts all the tiles from the level registry.
	 * 
	 * @return A new list of all of the levels in this level.
	 */
	public final List<Tile> extractTilesFromLevel()
	{
		if (unloaded)
			return null;
		List<Tile> result = new ArrayList<Tile>();
		for (Tile tile : Tile.tiles)
		{
			if (tile.level == this)
			{
				result.add(tile);
			}
		}
		return result;
	}

	public final void render(PixelyContext context)
	{
		if (unloaded)
			return;
	}

	public final void update(PixelyContext context)
	{
		if (unloaded)
			return;
	}

	public String getName()
	{
		return name;
	}

}
