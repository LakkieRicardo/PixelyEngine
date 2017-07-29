package net.lakkie.test.entities;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.EntityRenderer;
import net.lakkie.pixely.input.Buttons;
import net.lakkie.pixely.input.InputManager;
import net.lakkie.pixely.utils.Vector2;

public class EntityPlayer extends Entity {

	public EntityPlayer(Sprite sprite, Vector2 pos, String name) {
		super(sprite, pos, name);
	}

	public void onLoad(PixelyContext ctx) {

	}

	public void onStart(PixelyContext ctx) {

	}

	public void onPreUpdate(PixelyContext ctx) {

	}

	public void onUpdate(PixelyContext ctx) {
		Vector2 translation = new Vector2();
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
