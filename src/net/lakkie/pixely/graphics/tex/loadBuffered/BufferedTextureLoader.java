package net.lakkie.pixely.graphics.tex.loadBuffered;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

/**
 * 
 * Uses a more procedural-like approach to loading textures.<br>
 * This reads an image, and puts three main variables into its buffer, and those
 * are in the following order;
 * <ol>
 * <li><code>int width</code></li>
 * <li><code>int height</code></li>
 * <li><code>int[] pixels</code></li>
 * </ol>
 * 
 * @author Diego
 *
 */
public class BufferedTextureLoader {

	/**
	 * Puts an image into an integer buffer.
	 * 
	 * @param loadPath
	 *            The path to load the image from.
	 * @return The integer data.
	 */
	public static IntBuffer loadTexture(String loadPath) {
		try {
			BufferedImage img = ImageIO.read(BufferedTextureLoader.class.getResource(loadPath));
			IntBuffer result = IntBuffer.allocate(img.getWidth() * img.getHeight() + 2);
			result.put(img.getWidth());
			result.put(img.getHeight());
			result.put(img.getRGB(0, 0, img.getWidth(), img.getHeight(), new int[img.getWidth() * img.getHeight()], 0,
					img.getWidth()));
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static IntBufferExtractor asExtractor(IntBuffer img) {
		return new IntBufferExtractor(img);
	}

}
