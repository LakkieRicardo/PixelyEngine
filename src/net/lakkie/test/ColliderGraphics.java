package net.lakkie.test;

import java.awt.Color;
import java.awt.Graphics;

import net.lakkie.pixely.app.PixelScaler;
import net.lakkie.pixely.collision.Collider;
import net.lakkie.pixely.math.Vector4;

public class ColliderGraphics {

	private ColliderGraphics() {
	}

	public static void drawCollider(Collider collider, Graphics g) {
		Vector4 pos = new Vector4(PixelScaler.levelPointToScreenPoint(collider.getBox().toPositionVector()),
				PixelScaler.levelPointToScreenPoint(collider.getBox().toSizeVector()));
		g.setColor(Color.red);
		g.drawRect(pos.x, pos.y, pos.w, pos.h);
	}

}
