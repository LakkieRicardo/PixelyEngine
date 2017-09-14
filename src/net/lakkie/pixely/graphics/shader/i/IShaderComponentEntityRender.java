package net.lakkie.pixely.graphics.shader.i;

import net.lakkie.pixely.entity.Entity;

public interface IShaderComponentEntityRender extends IShaderComponentBase
{

	void accept(Entity entity);

}
