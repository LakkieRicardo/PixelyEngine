package net.lakkie.test.entities;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.EntityRenderer;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.math.Vector2i;

public class EntityPlayer extends Entity {

	public EntityPlayer(Level level, Sprite sprite, Vector2i pos, String name) {
		super(level, sprite, pos, name);
	}

	public void onLoad(PixelyContext ctx) {

	}

	public void onStart(PixelyContext ctx) {

	}

	public void onPreUpdate(PixelyContext ctx) {

	}

	public void onUpdate(PixelyContext ctx) {
		Vector2i translation = new Vector2i();
		int speed = InputManager.isKeyPressed(Buttons.VK_SHIFT) ? 2 : 1;
		if (InputManager.isKeyPressed(Buttons.VK_W)) {
			translation.y -= speed;
		}
		if (InputManager.isKeyPressed(Buttons.VK_A)) {
			translation.x -= speed;
		}
		if (InputManager.isKeyPressed(Buttons.VK_S)) {
			translation.y += speed;
		}
		if (InputManager.isKeyPressed(Buttons.VK_D)) {
			translation.x += speed;
		}
		translate(translation);
	}

	public void onPostUpdate(PixelyContext ctx) {

	}

	public EntityRenderer getEntityRenderer(Sprite defaultSprite) {
		return null;
	}

}
