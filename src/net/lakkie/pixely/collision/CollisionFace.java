package net.lakkie.pixely.collision;

import net.lakkie.pixely.math.Vector2i;

public enum CollisionFace {

	LEFT(-1, 0), RIGHT(1, 0), TOP(0, -1), BOTTOM(0, 1);

	/**
	 * Modifier of to use multiply when colliding
	 */
	private final Vector2i m;

	private CollisionFace(int mx, int my) {
		this.m = new Vector2i(mx, my);
	}
	
	public Vector2i getMultiplier() {
		return new Vector2i(this.m);
	}

}
