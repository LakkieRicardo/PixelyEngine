package net.lakkie.pixely.graphics.shader;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentBase;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentEntityRender;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentPixel;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentSpriteRender;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentTileRender;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Tile;

/**
 * 
 * This class groups together shader components.
 * 
 * @author Diego
 *
 */
public class ShaderProgram {

	private IShaderComponentEntityRender entityRender;
	private IShaderComponentPixel pixel;
	private IShaderComponentSpriteRender spriteRender;
	private IShaderComponentTileRender tileRender;
	private List<IShaderComponentBase> otherComponents = new ArrayList<IShaderComponentBase>();
	private boolean instantiated = false;

	public void startSpriteRender(int wx, int wy, Sprite sprite, int[] pixels) {
		spriteRender.assignCanvas(pixels);
		pixel.assignCanvas(pixels);
		spriteRender.accept(sprite, wx, wy);
	}

	public void startTileRender(Tile tile, int[] pixels) {
		tileRender.assignCanvas(pixels);
		tileRender.accept(tile);
	}

	public void startEntityRender(Entity entity, int[] pixels) {
		entityRender.assignCanvas(pixels);
		entityRender.accept(entity);
	}

	public IShaderComponentEntityRender getEntityRender() {
		return entityRender;
	}

	public IShaderComponentPixel getPixel() {
		return pixel;
	}

	public IShaderComponentSpriteRender getSpriteRender() {
		return spriteRender;
	}

	public IShaderComponentTileRender getTileRender() {
		return tileRender;
	}

	public void setEntityRender(IShaderComponentEntityRender entityRender) {
		this.entityRender = entityRender;
		this.entityRender.receiveShader(this);
	}

	public void setPixel(IShaderComponentPixel pixel) {
		this.pixel = pixel;
		this.pixel.receiveShader(this);
	}

	public void setSpriteRender(IShaderComponentSpriteRender spriteRender) {
		this.spriteRender = spriteRender;
		this.spriteRender.receiveShader(this);
	}

	public void setTileRender(IShaderComponentTileRender tileRender) {
		this.tileRender = tileRender;
		this.tileRender.receiveShader(this);
	}

	public boolean addComponent(IShaderComponentBase comp) {
		return this.otherComponents.add(comp);
	}

	@SuppressWarnings("unchecked")
	public <T> T getComponentImplementation(Class<T> type) {
		for (IShaderComponentBase comp : this.otherComponents) {
			if (comp.getClass() == type) {
				return (T) comp;
			}
		}
		return null;
	}

	public void init() {
		this.entityRender.init();
		this.pixel.init();
		this.spriteRender.init();
		this.tileRender.init();

		if (this.otherComponents.size() > 0) {
			for (IShaderComponentBase comp : this.otherComponents) {
				comp.init();
			}
		}
		this.instantiated = true;
	}
	
	public boolean isInited() {
		return instantiated;
	}

}
