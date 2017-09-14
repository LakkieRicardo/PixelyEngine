package net.lakkie.pixely.utils;

import net.lakkie.pixely.input.Buttons;

public enum MovementInputLayout
{

	WASD(Buttons.VK_A, Buttons.VK_D, Buttons.VK_W, Buttons.VK_S), ARROWS(Buttons.VK_LEFT, Buttons.VK_RIGHT, Buttons.VK_UP, Buttons.VK_DOWN);

	private int leftKey, rightKey, upKey, downKey;

	private MovementInputLayout(int leftKey, int rightKey, int upKey, int downKey)
	{
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.downKey = downKey;
	}

	public int getDownKey()
	{
		return downKey;
	}

	public int getLeftKey()
	{
		return leftKey;
	}

	public int getRightKey()
	{
		return rightKey;
	}

	public int getUpKey()
	{
		return upKey;
	}

}
