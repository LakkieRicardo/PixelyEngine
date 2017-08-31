package net.lakkie.test.entities;

import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.entity.defaults.physics.AttachmentStaticRigidBody;
import net.lakkie.pixely.graphics.tex.Sprite;
import net.lakkie.pixely.level.Level;
import net.lakkie.pixely.math.Vector2i;

public class EntityBody extends Entity {

	public EntityBody(Level level, Vector2i pos, String name) {
		super(level, Sprite.sprites.get("red"), pos, name);
		this.add(new AttachmentStaticRigidBody());
	}

}
