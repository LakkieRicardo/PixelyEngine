package net.lakkie.pixely.utils;

public class Vector4 {

	public int x, y, w, h;
	
	public Vector4() {
	}
	
	public Vector4(Vector2 pos, Vector2 size) {
		this.x = pos.x;
		this.y = pos.y;
		this.w = size.x;
		this.h = size.y;
	}
	
	public Vector4(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Vector4(Vector4 other) {
		this.x = other.x;
		this.y = other.y;
		this.w = other.w;
		this.h = other.h;
	}
	
	public Vector4 add(Vector4 other) {
		this.x += other.x;
		this.y += other.y;
		this.w += other.w;
		this.h += other.h;
		return this;
	}
	
	public Vector4 subtract(Vector4 other) {
		this.x -= other.x;
		this.y -= other.y;
		this.w -= other.w;
		this.h -= other.h;
		return this;
	}
	
	public Vector4 multiply(Vector4 other) {
		this.x *= other.x;
		this.y *= other.y;
		this.w *= other.w;
		this.h *= other.h;
		return this;
	}
	
	public Vector4 divide(Vector4 other) {
		this.x /= other.x;
		this.y /= other.y;
		this.w /= other.w;
		this.h /= other.h;
		return this;
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Vector4)) {
			return obj == this;
		} else {
			Vector4 other = (Vector4) obj;
			return this.x == other.x && this.y == other.y && this.w == other.w && this.h == other.h;
		}
	}
	
	public boolean equalsSize(Vector4 other) {
		return this.w == other.w && this.h == other.h;
	}
	
	public Vector4 toAbsoluteVector() {
		return new Vector4(Math.abs(x), Math.abs(y), Math.abs(w), Math.abs(h));
	}
	
	public Vector2 toSizeVector() {
		return new Vector2(this.w, this.h);
	}
	
	public Vector2 toPositionVector() {
		return new Vector2(this.x, this.y);
	}
	
	public String toString() {
		return String.format("(%s, %s, %s, %s)", this.x, this.y, this.w, this.h);
	}
	
}
