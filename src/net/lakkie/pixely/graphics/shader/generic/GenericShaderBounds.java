package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.graphics.tex.Sprite;

public class GenericShaderBounds extends GenericShaderComponentBase
{

	public boolean isOutOfBoundsBottomRight(int worldX, int worldY)
	{
		return worldX >= Application.renderEngine.viewport.w + Application.renderEngine.viewport.x
				|| worldY >= Application.renderEngine.viewport.h + Application.renderEngine.viewport.y;
	}

	public boolean isOutOfBoundsTopLeft(int worldX, int worldY, Sprite sprite)
	{
		return worldX - Application.renderEngine.viewport.x + sprite.width < 0 || worldY - Application.renderEngine.viewport.y + sprite.height < 0;
	}

}
