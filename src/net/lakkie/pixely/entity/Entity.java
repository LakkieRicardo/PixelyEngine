package net.lakkie.pixely.entity;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.EntityRenderer;
import net.lakkie.pixely.i.Renderable;
import net.lakkie.pixely.i.Updatable;
import net.lakkie.pixely.utils.Vector2;

public abstract class Entity implements Updatable, Renderable {

	public Vector2 pos;
	public String name;
	public List<EntityAttachment> attachments;
	public EntityRenderer renderer;

	public Entity(Sprite sprite, Vector2 pos, String name) {
		this.pos = pos;
		this.name = name;
		this.attachments = new ArrayList<EntityAttachment>();
		EntityRenderer renderer = getEntityRenderer(sprite);
		this.renderer = renderer == null ? new DefaultEntityRenderer(sprite) : renderer;
		Application.getUpdatables().add(this);
		Application.getPostUpdatables().add(this);
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
		this.pos.add(translation);
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
		onPostUpdate(ctx);
		for (EntityAttachment attach : this.attachments) {
			attach.postUpdate(ctx);
		}
	}

	public void render(PixelyContext context) {
		this.renderer.render(context, this);
	}

}
