package net.lakkie.pixely.graphics;

public class RenderUtility {

	/**
	 * 
	 * Creates a new image, (<code>int[]</code>), out of a rectangle from a bigger image.
	 * 
	 * @param img The bigger image.
	 * @param x The X starting coordinate.
	 * @param y The Y starting coordinate.
	 * @param width The width of the new image
	 * @param height The height of the new image
	 * @param origW The width of the original image
	 * @param origH The height of the original image
	 * @return The cut image. An integer array that is in the same format as the specified <code>int[]</code>
	 */
	public static int[] copyOut(int[] img, int x, int y, int width, int height, int origW, int origH) {
		assert !(x < 0);
		assert !(y < 0);
		assert !(width <= origW);
		assert !(height <= origH);
		
		int[] newPixels = new int[width * height];
		for (int cy = 0; cy < height; cy++) {
			int yy = cy + y;
			for (int cx = 0; cx < width; cx++) {
				int xx = cx + x;
				newPixels[cx + cy * width] = img[xx + yy * origW];
			}
		}
		return newPixels;
	}
	
}
