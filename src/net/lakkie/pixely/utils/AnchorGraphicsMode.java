package net.lakkie.pixely.utils;

public enum AnchorGraphicsMode {

	CENTER(0, 0), TOP(0, -1), LEFT(-1, 0), BOTTOM(0, 1), RIGHT(1, 0);
	
	private int x, y;
	
	private AnchorGraphicsMode(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector2i asVec() {
		return new Vector2i(x, y);
	}
	
}
