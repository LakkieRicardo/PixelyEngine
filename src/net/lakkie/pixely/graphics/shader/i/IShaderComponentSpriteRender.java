package net.lakkie.pixely.graphics.shader.i;

import net.lakkie.pixely.graphics.tex.Sprite;

public interface IShaderComponentSpriteRender extends IShaderComponentBase {

	void accept(Sprite sprite, int offX, int offY);
	
}
