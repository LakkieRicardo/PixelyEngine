package net.lakkie.pixely.collision;

import net.lakkie.pixely.utils.Vector2;

public interface ICollisionProvider {

	void addForce(Vector2 force);
	
	Vector2 getMovementSpeed();
	
}
