package net.lakkie.pixely.graphics.renders;

import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.shader.ShaderProgram;
import net.lakkie.pixely.graphics.shader.generic.GenericShader;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.math.Vector2i;
import net.lakkie.pixely.math.Vector4;

/**
 * 
 * This class simply keeps track of a {@link ShaderProgram}. Calling a method
 * like {@link RenderEngineShaded#renderSprite(int, int, Sprite)} will only call
 * {@link ShaderProgram#startSpriteRender(int, int, Sprite, int[])}. This class
 * does not do any rendering operations by itself.
 * 
 * @author Diego
 *
 */
public class RenderEngineShaded extends RenderEngine {

	public static final String name = "shaded";

	private ShaderProgram shader;
	public Vector2i lightSource = new Vector2i(400, 200);
	
	public RenderEngineShaded(Vector4 viewport, ShaderProgram assembledShader) {
		super(viewport, name);
		this.shader = assembledShader;
	}

	public RenderEngineShaded(Vector4 viewport) {
		this(viewport, null);
		this.shader = new ShaderProgram();
	}
	
	public void firstStart() {
		if (!this.shader.isInited()) {
			GenericShader.instantiate(this.shader);
		}
	}

	public void renderSprite(int worldX, int worldY, Sprite sprite) {
		this.shader.startSpriteRender(worldX, worldY, sprite, this.pixels);
	}

	public void renderTile(Tile tile) {
		this.shader.startTileRender(tile, this.pixels);
	}

	public void renderEntity(Entity entity) {
		this.shader.startEntityRender(entity, this.pixels);
	}

	public ShaderProgram getShader() {
		return this.shader;
	}
	
	public int[] processSprite(int wx, int wy, Sprite sprite) {
		int[] pixels = new int[sprite.pixels.length];
		System.arraycopy(sprite.pixels, 0, pixels, 0, pixels.length);
		this.shader.getPixel().assignCanvas(pixels);
		for (int y = 0; y < sprite.height; y++) {
			for (int x = 0; x < sprite.width; x++) {
				this.shader.getPixel().accept(sprite, wx, wy, x, y);
			}
		}
		return pixels;
	}

}
