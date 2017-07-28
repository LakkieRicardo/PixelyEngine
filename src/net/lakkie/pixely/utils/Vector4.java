package net.lakkie.pixely.utils;

public class Vector4 {

	public int x, y, w, h;
	
	public Vector4() {
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
	
	public String toString() {
		return String.format("(%s, %s, %s, %s0", this.x, this.y, this.w, this.h);
	}
	
}
