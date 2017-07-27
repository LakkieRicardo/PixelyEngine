package net.lakkie.pixely.graphics.tex.loadDirect;

import java.awt.image.BufferedImage;

public class DirectImage {

	private BufferedImage image;

	public DirectImage(BufferedImage image) {
		this.image = image;
	}

	public int width() {
		return this.image.getWidth();
	}

	public int height() {
		return this.image.getHeight();
	}

	public int[] pixels() {
		return this.image.getRGB(0, 0, width(), height(), new int[width() * height()], 0, width());
	}

}
