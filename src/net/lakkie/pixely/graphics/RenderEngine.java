package net.lakkie.pixely.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;
import net.lakkie.pixely.utils.Vector2;
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
	
	public abstract void renderEntity(Entity entity);

	public void translateViewport(int x, int y) {
		this.viewport.x += x;
		this.viewport.y -= y;
	}
	
	private void resize(Vector4 newView) {
		Vector2 diff = new Vector4(newView).subtract(new Vector4(this.viewport)).toSizeVector();
		int padX = diff.x / 2;
		int padY = diff.y / 2;
		this.viewport = new Vector4(this.viewport.x - padX, this.viewport.y - padY, newView.w + padX, newView.h + padY);
		
		// Remainder handling
		Vector2 remain = this.viewport.toSizeVector();
		remain.subtract(newView.toSizeVector());
		if (!remain.equals(new Vector2())) {
			
			if (remain.x % 2 == 0) {
				// Fill in X
				int padding = remain.x / 2;
				this.viewport.x -= padding;
				this.viewport.w += padding;
			}
			if (remain.y % 2 == 0) {
				// Fill in Y
				int padding = remain.y / 2;
				this.viewport.y -= padding;
				this.viewport.h += padding;
			}
			
		}
	}
	
	public void resizeViewport(Vector4 newView) {
		if (this.viewport != null) {
			resize(newView);
		} else {
			this.viewport = newView;
		}
		cameraImage = new BufferedImage(this.viewport.w, this.viewport.h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) cameraImage.getRaster().getDataBuffer()).getData();
	}
	
	public boolean hasViewportChanged(Vector4 windowSize) {
		return !this.viewport.equalsSize(windowSize);
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
