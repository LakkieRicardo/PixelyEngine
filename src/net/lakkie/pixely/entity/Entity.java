package net.lakkie.pixely.entity;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.IEntityRenderer;
import net.lakkie.pixely.i.IRenderable;
import net.lakkie.pixely.i.IUpdatable;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;

public class Entity implements IUpdatable, IRenderable, Nameable
{

	public static final Registry<Entity> entities = new Registry<Entity>();
	public Vector2i pos;
	public Vector2i velocity;
	public String name;
	public List<EntityAttachment> attachments;
	public IEntityRenderer renderer;
	public Level level;
	public boolean isPositionLate = false;
	boolean hasUpdated = false;
	public List<Entity> updateRequires = new ArrayList<>();
	boolean hasPostUpdated = false;
	public List<Entity> postUpdateRequires = new ArrayList<>();

	public Entity(Level level, Sprite sprite, Vector2i pos, String name)
	{
		this.level = level;
		this.pos = pos;
		this.name = name;
		this.attachments = new ArrayList<EntityAttachment>();
		IEntityRenderer renderer = getEntityRenderer(sprite);
		this.renderer = renderer == null ? new DefaultEntityRenderer(sprite) : renderer;
		this.velocity = new Vector2i();
		entities.submit(this);
		if (this.level != null)
		{
			this.level.getEntities().add(this); // extractEntitiesFromLevel returns a reference
		}
	}

	public void addForce(Vector2i force)
	{
		applyForce(force);
	}

	public Vector2i getMovementSpeed()
	{
		return this.velocity;
	}

	/**
	 * Called when the entity gets loaded, or when the level changes, before
	 * {@code Entity#onStart(PixelyContext)}
	 * 
	 * @param ctx
	 */
	public void onLoad(PixelyContext ctx)
	{
	}

	/**
	 * Called after loading the entity, or after a new level is loaded.
	 * 
	 * @param ctx
	 */
	public void onStart(PixelyContext ctx)
	{
	}

	/**
	 * Called before updating the entity. Primarily for random things.
	 * 
	 * @param ctx
	 */
	public void onPreUpdate(PixelyContext ctx)
	{
	}

	/**
	 * Called after preUpdate and before postUpdate. Usually used for the majority
	 * of features.
	 * 
	 * @param ctx
	 */
	public void onUpdate(PixelyContext ctx)
	{
	}

	/**
	 * Called as the last update method. For physics calculations.
	 * 
	 * @param ctx
	 */
	public void onPostUpdate(PixelyContext ctx)
	{
	}

	/**
	 * @param defaultSprite
	 *            The sprite passed in by the constructor.
	 * @return Null if you want {@code DefaultEntityRenderer}, else use specified.
	 */
	public IEntityRenderer getEntityRenderer(Sprite defaultSprite)
	{
		return null;
	}

	public void add(EntityAttachment attach)
	{
		attach.entity = this;
		this.attachments.add(attach);
	}

	public String getName()
	{
		return this.name;
	}

	public final void start(PixelyContext ctx)
	{
		onLoad(ctx);
		for (EntityAttachment attach : this.attachments)
		{
			attach.entity = this;
			attach.load(ctx);
		}
		onStart(ctx);
		for (EntityAttachment attach : this.attachments)
		{
			attach.start(ctx);
		}
	}

	public final void translate(Vector2i translation)
	{
		this.pos = this.pos.add(translation);
	}

	public final void update(PixelyContext ctx)
	{
		if (this.updateRequires.size() != 0)
		{
			for (Entity require : this.updateRequires)
			{
				if (require.hasUpdated == false)
				{
					require.update(ctx);
				}
			}
		}
		this.isPositionLate = false;
		onPreUpdate(ctx);
		for (EntityAttachment attach : this.attachments)
		{
			attach.preUpdate(ctx);
		}
		onUpdate(ctx);
		for (EntityAttachment attach : this.attachments)
		{
			attach.update(ctx);
		}
		this.hasUpdated = true;
	}

	public final void postUpdate(PixelyContext ctx)
	{
		if (this.postUpdateRequires.size() != 0)
		{
			for (Entity require : this.postUpdateRequires)
			{
				if (require.hasPostUpdated == false)
				{
					require.postUpdate(ctx);
				}
			}
		}
		this.pos = this.pos.add(this.velocity);
		this.isPositionLate = true;
		onPostUpdate(ctx);
		for (EntityAttachment attach : this.attachments)
		{
			attach.postUpdate(ctx);
		}
		this.hasPostUpdated = true;
	}

	public void applyForce(Vector2i force)
	{
		this.velocity = this.velocity.add(force);
	}

	public void render(PixelyContext context)
	{
		this.renderer.render(context, this);
	}

	public Vector2i getPositionLate()
	{
		if (this.pos == null) { return new Vector2i(); }

		if (this.isPositionLate)
		{
			return this.pos;
		} else
		{
			return this.pos.add(this.velocity);
		}
	}

	public void resetUpdateSwitches()
	{
		this.hasUpdated = false;
		this.hasPostUpdated = false;
	}

}
