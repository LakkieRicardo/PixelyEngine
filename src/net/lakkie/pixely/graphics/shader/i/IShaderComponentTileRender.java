package net.lakkie.pixely.graphics.shader.i;

import net.lakkie.pixely.level.Tile;

public interface IShaderComponentTileRender extends IShaderComponentBase {

	void accept(Tile tile);
	
}
