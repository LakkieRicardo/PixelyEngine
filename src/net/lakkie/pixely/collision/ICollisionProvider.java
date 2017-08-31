package net.lakkie.pixely.collision;

import net.lakkie.pixely.math.Vector2i;

public interface ICollisionProvider {

	void addForce(Vector2i force);
	
	Vector2i getMovementSpeed();
	
	Vector2i getPositionLate();
	
}
