package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.graphics.shader.ShaderProgram;

public class GenericShader {

	private GenericShader() {
	}
	
	public static void instantiate(ShaderProgram program) {
		program.addComponent(new GenericShaderBounds());
		program.setSpriteRender(new GenericShaderSpriteRender());
		program.setEntityRender(new GenericShaderEntityRender());
		program.setTileRender(new GenericShaderTileRender());
		program.setPixel(new GenericShaderPixel());
		program.init();
	}

}
