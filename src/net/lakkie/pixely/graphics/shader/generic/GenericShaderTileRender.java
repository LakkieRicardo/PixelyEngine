package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.graphics.shader.i.IShaderComponentTileRender;
import net.lakkie.pixely.level.Tile;

public class GenericShaderTileRender extends GenericShaderComponentBase implements IShaderComponentTileRender {

	public void accept(Tile tile) {
		this.shader.startSpriteRender(tile.pos.x, tile.pos.y, tile.sprite, this.pixels);
	}

}
