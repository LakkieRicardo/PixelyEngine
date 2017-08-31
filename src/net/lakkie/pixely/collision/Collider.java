package net.lakkie.pixely.collision;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.math.Vector4;

public final class Collider {

	private static List<Collider> colliders = new ArrayList<Collider>();

	private Vector4 box;
	private ICollisionProvider provider;
	public int responseMultiplier = 1;
	private Vector2i responseMultiplierVector;

	public Collider(Vector2i pos, Vector2i size, ICollisionProvider provider) {
		this.box = new Vector4(pos, size);
		this.provider = provider;
		this.responseMultiplierVector = new Vector2i(this.responseMultiplier);
		Collider.colliders.add(this);
	}

	public void collide(Collider other) {
		CollisionFace[] collidingFaces = CollisionService.getCollidingFacesInFirst(this, other);
		if (collidingFaces.length != 0) {
			Vector2i collisionResponse = new Vector2i();
			for (int i = 0; i < collidingFaces.length; i++) {
				CollisionFace current = collidingFaces[i];
				if (current == null)
					continue;
				collisionResponse = collisionResponse.add(current.getMultiplier());
			}

			collisionResponse = collisionResponse.multiply(this.provider.getMovementSpeed().multiply(this.responseMultiplierVector));
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

	public void updatePositionWithProvider() {
		this.box = new Vector4(this.provider.getPositionLate(), this.box.toSizeVector());
	}

	public static List<Collider> getColliders() {
		return colliders;
	}

}
