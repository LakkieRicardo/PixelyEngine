package net.lakkie.pixely.entity;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.EntityRenderer;

public class DefaultEntityRenderer implements EntityRenderer {

	private Sprite sprite;
	
	public DefaultEntityRenderer(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(PixelyContext ctx, Entity entity) {
		RenderEngine engine = (RenderEngine) ctx.get(PixelyContext.renderEngine);
		engine.renderSprite(entity.pos.x, entity.pos.y, sprite);
	}
	
}
