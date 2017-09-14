package net.lakkie.pixely.graphics;

public enum RenderEngineResizeMode
{

	/**
	 * Currently very buggy. Adds padding to the corners to keep the smaller piece
	 * in the middle
	 */
	PADDING_ALL_DIRECTIONS,
	/**
	 * Works. Adds padding to the bottom and right.
	 */
	PADDING_BOTTOM_RIGHT;

}
