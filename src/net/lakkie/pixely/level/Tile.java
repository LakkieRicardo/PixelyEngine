package net.lakkie.pixely.level;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.i.Updatable;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;
import net.lakkie.pixely.utils.Vector2;

public class Tile implements Nameable, Updatable {

	public static final Registry<Tile> tiles = new Registry<Tile>();
	public Sprite sprite;
	public Vector2 pos;
	public String name;
	public Level level;
	private boolean updates = false;
	
	public Tile(Level level, Sprite sprite, int x, int y, String name) {
		this.level = level;
		this.name = name;
		this.sprite = sprite;
		this.pos = new Vector2(x, y);
		tiles.submit(this);
	}
	
	public Tile(Level level, Sprite sprite, Vector2 pos, String name) {
		this(level, sprite, pos.x, pos.y, name);
	}
	
	public void translate(Vector2 translation) {
		this.pos.add(translation);
	}
	
	public void setTranslation(Vector2 translation) {
		this.pos = translation;
	}
	
	public String getName() {
		return name;
	}
	
	public void delete() {
		tiles.kick(this);
	}
	
	public boolean isDeleted() {
		return tiles.getElements().contains(this);
	}
	
	public boolean isUpdating() {
		return updates;
	}
	
	public void submitToUpdates() {
		updates = true;
		Application.getUpdatables().add(this);
	}
	
	public void removeFromUpdates() {
		updates = false;
		Application.getUpdatables().remove(this);
	}

	public void update(PixelyContext context) {
	}
	
}
