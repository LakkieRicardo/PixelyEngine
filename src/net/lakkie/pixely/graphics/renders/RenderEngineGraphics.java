package net.lakkie.pixely.graphics.renders;

import java.awt.Graphics;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;
import net.lakkie.pixely.math.Vector4;

/**
 * 
 * This RenderEngine is focused on editing BufferedImage's, and providing them
 * to be rotated and scaled. This takes in sprites as BufferedImage's
 * 
 * @author Diego
 */
public class RenderEngineGraphics extends RenderEngine
{
	
	public static final String name = "graphics";

	private Sprite[] spriteQueue;
	private int[] xQueue;
	private int[] yQueue;
	private int queueSize = 0;
	
	public RenderEngineGraphics(Vector4 viewport)
	{
		super(viewport, name);
	}
	
	public void firstStart()
	{
		this.spriteQueue = new Sprite[Application.ctx.getMaxSprites()];
		this.xQueue = new int[Application.ctx.getMaxSprites()];
		this.yQueue = new int[Application.ctx.getMaxSprites()];
	}

	public void renderSprite(int worldX, int worldY, Sprite sprite)
	{
		this.spriteQueue[this.queueSize  ] = sprite;
		this.xQueue		[this.queueSize  ] = worldX;
		this.yQueue		[this.queueSize++] = worldY;
	}

	public void finishRender()
	{
		for (int i = 0; i < this.queueSize; i++)
		{
			Sprite sprite = this.spriteQueue[i];
			int x = this.xQueue[i];
			int y = this.yQueue[i];
			Graphics canvas = Application.graphics;
			// Ratio of width, Ratio of height
			int rw = Application.currentWindow.getWidth() / Application.targetWidth;
			int rh = Application.currentWindow.getHeight() / Application.targetHeight;
			// Scale up the image
			canvas.drawImage(sprite.src, x * rw, y * rh, sprite.width * rw, sprite.height * rh, null);
		}
		
		this.spriteQueue = new Sprite[Application.ctx.getMaxSprites()];
		this.xQueue = new int[Application.ctx.getMaxSprites()];
		this.yQueue = new int[Application.ctx.getMaxSprites()];
		this.queueSize = 0;
	}

	public void renderTile(Tile tile)
	{
		this.renderSprite(tile.pos.x, tile.pos.y, tile.sprite);
	}

	public void renderEntity(Entity entity)
	{
		entity.renderer.render(Application.ctx, entity);
	}

}
