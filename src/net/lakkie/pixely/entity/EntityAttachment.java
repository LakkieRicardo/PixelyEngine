package net.lakkie.pixely.entity;

import net.lakkie.pixely.context.PixelyContext;

public abstract class EntityAttachment {

	Entity entity;
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public abstract String getName();
	
	/**
	 * Called when the entity gets loaded, or when the level changes, before {@code Entity#onStart(PixelyContext)}
	 * @param ctx
	 */
	public void load(PixelyContext ctx) {
	}
	
	/**
	 * Called after loading the entity, or after a new level is loaded.
	 * @param ctx
	 */
	public void start(PixelyContext ctx) {
	}
	
	/**
	 * Called before updating the entity. Primarily for random things.
	 * @param ctx
	 */
	public void preUpdate(PixelyContext ctx) {
	}
	
	/**
	 * Called after preUpdate and before postUpdate. Usually used for the majority of features.
	 * @param ctx
	 */
	public void update(PixelyContext ctx) {
	}
	
	/**
	 * Called as the last update method. For physics calculations.
	 * @param ctx
	 */
	public void postUpdate(PixelyContext ctx) {
	}

}
