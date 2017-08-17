package net.lakkie.pixely.collision;

import net.lakkie.pixely.utils.Vector2;

public enum CollisionFace {

	LEFT(-1, 0), RIGHT(1, 0), TOP(0, -1), BOTTOM(0, 1);

	/**
	 * Modifier of to use multiply when colliding
	 */
	private final Vector2 m;

	private CollisionFace(int mx, int my) {
		this.m = new Vector2(mx, my);
	}
	
	public Vector2 getMultiplier() {
		return new Vector2(this.m);
	}

}
