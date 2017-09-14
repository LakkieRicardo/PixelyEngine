package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.graphics.shader.ShaderProgram;
import net.lakkie.pixely.graphics.shader.generic.enums.GenericLightingGenerationMode;

public class GenericShader
{

	public static GenericLightingGenerationMode genMode = GenericLightingGenerationMode.PIXEL_LEVEL;
	public static int currentShade = 0;

	private GenericShader()
	{
	}

	public static void instantiate(ShaderProgram program)
	{
		program.addComponent(new GenericShaderBounds());
		program.setSpriteRender(new GenericShaderSpriteRender());
		program.setEntityRender(new GenericShaderEntityRender());
		program.setTileRender(new GenericShaderTileRender());
		program.setPixel(new GenericShaderPixel());
		program.init();
	}

}
