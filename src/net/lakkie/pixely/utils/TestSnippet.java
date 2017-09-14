package net.lakkie.pixely.utils;

import net.lakkie.pixely.graphics.RenderEngine;
import net.lakkie.pixely.input.InputManager;

public class TestSnippet
{

	public static void moveUsingButtons(MovementInputLayout layout, int speed, RenderEngine engine)
	{
		if (InputManager.isKeyPressed(layout.getUpKey()))
		{
			engine.translateViewport(0, speed);
		}

		if (InputManager.isKeyPressed(layout.getLeftKey()))
		{
			engine.translateViewport(-speed, 0);
		}

		if (InputManager.isKeyPressed(layout.getDownKey()))
		{
			engine.translateViewport(0, -speed);
		}

		if (InputManager.isKeyPressed(layout.getRightKey()))
		{
			engine.translateViewport(speed, 0);
		}
	}

}
