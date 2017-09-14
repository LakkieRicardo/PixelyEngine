package net.lakkie.pixely.graphics.tex.loadDirect;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DirectTextureLoader
{

	public static BufferedImage readImage(String filePath)
	{
		try
		{
			return ImageIO.read(DirectTextureLoader.class.getResource(filePath));
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static DirectImage asDirectImage(BufferedImage img)
	{
		return new DirectImage(img);
	}

}
