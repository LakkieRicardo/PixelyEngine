package net.lakkie.pixely.collision;

import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.math.Vector4;

public final class CollisionService {

	public static boolean inclusiveCollision = true;

	private CollisionService() {
	}

	public static CollisionFace[] getCollidingFacesInFirst(Collider origin, Collider testing) {
		CollisionFace[] result = new CollisionFace[4];
		/*
		 * Resultant array contains 4 faces to collide with in the following order: top,
		 * right, bottom, left
		 */
		result[0] = isColliding(CollisionFace.TOP, origin, testing) == true ? CollisionFace.TOP : null;
		result[1] = isColliding(CollisionFace.RIGHT, origin, testing) == true ? CollisionFace.RIGHT : null;
		result[2] = isColliding(CollisionFace.BOTTOM, origin, testing) == true ? CollisionFace.BOTTOM : null;
		result[3] = isColliding(CollisionFace.LEFT, origin, testing) == true ? CollisionFace.LEFT : null;
		return result;
	}

	public static boolean isColliding(CollisionFace face, Collider origin, Collider testing) {
		Vector4 ob = origin.getBox();
		Vector4 tb = origin.getBox();
		if (face == CollisionFace.BOTTOM) {
			int max = ob.y + ob.h;
			int input = tb.y;
			return testMax(max, input);
		}
		if (face == CollisionFace.TOP) {
			int min = ob.y;
			int input = tb.y + tb.h;
			return testMin(min, input);
		}
		if (face == CollisionFace.LEFT) {
			int max = ob.x;
			int input = tb.x + tb.w;
			return testMax(max, input);
		}
		if (face == CollisionFace.RIGHT) {
			int min = ob.x + ob.w;
			int input = tb.x;
			return testMin(min, input);
		}
		return false;
	}

	private static boolean testMax(int max, int input) {
		return inclusiveCollision ? input >= max : input > max;
	}

	private static boolean testMin(int min, int input) {
		return inclusiveCollision ? input <= min : input < min;
	}

	public static boolean isColliderNearby(Collider origin, Collider other) {
		Vector2i delta = other.getBox().toPositionVector().subtract(origin.getBox().toPositionVector());
		if (delta.length() <= 0) {
			// TODO: Handle for other is top/left/both
			delta = delta.abs();
			return delta.x <= origin.getBox().w + 1 && delta.y <= origin.getBox().h + 1;
		} else {
			// TODO: Handle for other is bottom/right/both
			delta = delta.abs();
			return delta.x <= other.getBox().w + 1 && delta.y <= other.getBox().h + 1;
		}
	}

}
