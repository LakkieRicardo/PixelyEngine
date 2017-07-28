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
		resizeViewport(new Vector4(x, y, width, height));
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
	
	public void resizeViewport(Vector4 newView) {
		if (this.viewport != null) {
			this.viewport = new Vector4(this.viewport.x, this.viewport.y, newView.w, newView.h);
		} else {
			this.viewport = newView;
		}
		cameraImage = new BufferedImage(this.viewport.w, this.viewport.h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) cameraImage.getRaster().getDataBuffer()).getData();
	}
	
	public boolean hasViewportChanged(Vector4 original) {
		Vector4 dis = new Vector4(0, 0, this.viewport.w, this.viewport.h);
		return !dis.equals(original);
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
