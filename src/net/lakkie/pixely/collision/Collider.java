package net.lakkie.pixely.collision;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.utils.Vector2;
import net.lakkie.pixely.utils.Vector4;

public final class Collider {

	private static List<Collider> colliders = new ArrayList<Collider>();

	private Vector4 box;
	private ICollisionProvider provider;
	public int responseMultiplier = 1;
	private Vector2 responseMultiplierVector;

	public Collider(Vector2 pos, Vector2 size, ICollisionProvider provider) {
		this.box = new Vector4(pos, size);
		this.provider = provider;
		this.responseMultiplierVector = new Vector2(this.responseMultiplier);
	}

	public void collide(Collider other) {
		CollisionFace[] collidingFaces = CollisionService.getCollidingFacesInFirst(this, other);
		if (collidingFaces.length != 0) {
			Vector2 collisionResponse = new Vector2();
			for (int i = 0; i < collidingFaces.length; i++) {
				CollisionFace current = collidingFaces[i];
				if (current == null)
					continue;
				collisionResponse = collisionResponse + current.getMultiplier();
			}
			
			collisionResponse = collisionResponse * this.provider.getMovementSpeed() * this.responseMultiplierVector;
			this.provider.addForce(collisionResponse);
		}
	}

	public boolean colliding(Collider other) {
		return CollisionService.getCollidingFacesInFirst(this, other).length != 0;
	}

	public Vector4 getBox() {
		return box;
	}
	
	public ICollisionProvider getProvider() {
		return provider;
	}

	public static List<Collider> getColliders() {
		return colliders;
	}

}
