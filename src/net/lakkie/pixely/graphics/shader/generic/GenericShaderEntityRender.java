package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.entity.Entity;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentEntityRender;

public class GenericShaderEntityRender extends GenericShaderComponentBase implements IShaderComponentEntityRender {

	public void accept(Entity entity) {
		entity.render(Application.ctx);
	}

}
