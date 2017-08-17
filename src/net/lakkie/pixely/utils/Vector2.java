package net.lakkie.pixely.utils;

public class Vector2 {

	public int x, y;

	public Vector2() {
	}

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}

	public Vector2(int val) {
		this.x = val;
		this.y = val;
	}

	public Vector2 add(Vector2 other) {
		Vector2 result = new Vector2(this);
		result.x += other.x;
		result.y += other.y;
		return result;
	}

	public Vector2 subtract(Vector2 other) {
		Vector2 result = new Vector2(this);
		result.x -= other.x;
		result.y -= other.y;
		return result;
	}

	public Vector2 multiply(Vector2 other) {
		Vector2 result = new Vector2(this);
		result.x *= other.x;
		result.y *= other.y;
		return result;
	}

	public Vector2 divide(Vector2 other) {
		Vector2 result = new Vector2(this);
		result.x /= other.x;
		result.y /= other.y;
		return result;
	}

	public Vector2 modulus(Vector2 other) {
		Vector2 result = new Vector2(this);
		result.x %= other.x;
		result.y %= other.y;
		return result;
	}

	/**
	 * An alias for {@link Vector2#modulus(Vector2)}
	 */
	public Vector2 remainder(Vector2 other) {
		return modulus(other);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Vector2)) {
			return obj == this;
		}
		Vector2 other = (Vector2) obj;
		return this.x == other.x && this.y == other.y;
	}

	public int length() {
		return this.x + this.y;
	}

	public String toString() {
		return String.format("(%s, %s)", x, y);
	}

	public Vector2 toAbsoluteVector() {
		return new Vector2(Math.abs(this.x), Math.abs(this.y));
	}

}
