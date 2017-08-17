package net.lakkie.pixely.entity;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.collision.ICollisionProvider;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.EntityRenderer;
import net.lakkie.pixely.i.Renderable;
import net.lakkie.pixely.i.Updatable;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;
import net.lakkie.pixely.utils.Vector2;

public class Entity implements Updatable, Renderable, Nameable, ICollisionProvider {

	public static final Registry<Entity> entities = new Registry<Entity>();
	public Vector2 pos;
	public Vector2 velocity;
	public String name;
	public List<EntityAttachment> attachments;
	public EntityRenderer renderer;
	public Level level;

	public Entity(Level level, Sprite sprite, Vector2 pos, String name) {
		this.level = level;
		this.pos = pos;
		this.name = name;
		this.attachments = new ArrayList<EntityAttachment>();
		EntityRenderer renderer = getEntityRenderer(sprite);
		this.renderer = renderer == null ? new DefaultEntityRenderer(sprite) : renderer;
		entities.submit(this);
	}

	public void addForce(Vector2 force) {
		applyForce(force);
	}
	
	public Vector2 getMovementSpeed() {
		return this.velocity;
	}

	/**
	 * Called when the entity gets loaded, or when the level changes, before
	 * {@code Entity#onStart(PixelyContext)}
	 * 
	 * @param ctx
	 */
	public void onLoad(PixelyContext ctx) {
	}

	/**
	 * Called after loading the entity, or after a new level is loaded.
	 * 
	 * @param ctx
	 */
	public void onStart(PixelyContext ctx) {
	}

	/**
	 * Called before updating the entity. Primarily for random things.
	 * 
	 * @param ctx
	 */
	public void onPreUpdate(PixelyContext ctx) {
	}

	/**
	 * Called after preUpdate and before postUpdate. Usually used for the
	 * majority of features.
	 * 
	 * @param ctx
	 */
	public void onUpdate(PixelyContext ctx) {
	}

	/**
	 * Called as the last update method. For physics calculations.
	 * 
	 * @param ctx
	 */
	public void onPostUpdate(PixelyContext ctx) {
	}

	/**
	 * @param defaultSprite
	 *            The sprite passed in by the constructor.
	 * @return Null if you want {@code DefaultEntityRenderer}, else use
	 *         specified.
	 */
	public EntityRenderer getEntityRenderer(Sprite defaultSprite) {
		return null;
	}
	
	public void add(EntityAttachment attach) {
		this.attachments.add(attach);
	}
	
	public String getName() {
		return this.name;
	}

	public final void start(PixelyContext ctx) {
		onLoad(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.entity = this;
			attach.load(ctx);
		}
		onStart(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.start(ctx);
		}
	}

	public final void translate(Vector2 translation) {
		this.pos = this.pos + translation;
	}

	public final void update(PixelyContext ctx) {
		onPreUpdate(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.preUpdate(ctx);
		}
		onUpdate(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.update(ctx);
		}
	}

	public final void postUpdate(PixelyContext ctx) {
		this.pos.add(this.velocity);
		onPostUpdate(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.postUpdate(ctx);
		}
	}
	
	public void applyForce(Vector2 force) {
		this.velocity.add(force);
	}

	public void render(PixelyContext context) {
		this.renderer.render(context, this);
	}

}
