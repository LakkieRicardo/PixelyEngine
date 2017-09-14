package net.lakkie.pixely.utils;

import java.awt.Graphics;
import java.awt.Image;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.math.Vector2i;

public class AnchorGraphics
{

	private static AnchorGraphicsMode[] anchors;

	public static void setAnchorPoints(AnchorGraphicsMode... anchors)
	{
		AnchorGraphics.anchors = anchors;
	}

	public static Vector2i addAnchors()
	{
		if (anchors == null || anchors.length < 1) { return new Vector2i(); }
		Vector2i result = new Vector2i();
		for (int i = 0; i < anchors.length; i++)
		{
			result.add(anchors[i].asVec());
		}
		return result;
	}

	public static void drawImage(Graphics g, Image image, int x, int y, int width, int height)
	{
		Vector2i anchor = getAnchorPos();
		g.drawImage(image, x + anchor.x, y + anchor.y, width, height, null);
	}

	public static void drawRect(Graphics g, int x, int y, int width, int height)
	{
		Vector2i anchor = getAnchorPos();
		g.fillRect(x + anchor.x, y + anchor.y, width, height);
	}

	public static void drawCircle(Graphics g, int x, int y, int width, int height, int arc)
	{
		Vector2i anchor = getAnchorPos();
		g.fillRoundRect(x + anchor.x, y + anchor.y, width, height, arc, arc);
	}

	public static Vector2i getAnchorPos()
	{
		Vector2i anchor = addAnchors();
		Vector2i r = new Vector2i();
		switch (anchor.x)
		{
		case -1:
			r.x = 0;
			break;
		case 0:
			r.x = Application.targetWidth / 2;
			break;
		case 1:
			r.x = Application.targetWidth;
			break;
		}
		switch (anchor.y)
		{
		case -1:
			r.y = 0;
		case 0:
			r.y = Application.targetHeight / 2;
			break;
		case 1:
			r.y = Application.targetHeight;
			break;
		}
		return r;
	}

}
