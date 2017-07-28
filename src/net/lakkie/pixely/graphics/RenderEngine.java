package net.lakkie.pixely.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;
import net.lakkie.pixely.utils.Vector4;

public abstract class RenderEngine implements Nameable {

	public static final Registry<RenderEngine> engines = new Registry<RenderEngine>();

	public BufferedImage cameraImage;
	public int[] pixels;
	public Vector4 viewport;
	private final String name;

	public RenderEngine(int x, int y, int width, int height, String name) {
		cameraImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) cameraImage.getRaster().getDataBuffer()).getData();
		this.viewport = new Vector4(x, y, width, height);
		this.name = name;
	}

	public RenderEngine(Vector4 viewport, String name) {
		this(viewport.x, viewport.y, viewport.w, viewport.h, name);
	}

	public abstract void renderSprite(int worldX, int worldY, Sprite sprite);

	public abstract void renderTile(Tile tile);

	public void translateViewport(int x, int y) {
		this.viewport.x += x;
		this.viewport.y -= y;
	}

	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public String getName() {
		return this.name;
	}

}
