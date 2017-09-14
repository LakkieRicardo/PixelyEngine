package net.lakkie.pixely.input;

/**
 * 
 * This class handles input of keys and mouse.
 * 
 * @author Diego
 *
 */
public class InputManager
{

	static int mx, my;
	static boolean[] mouseButtons = new boolean[50];
	static boolean[] mouseButtonsClick = new boolean[50];
	static boolean[] keys = new boolean[600];
	static boolean[] keysClick = new boolean[600];

	public static boolean isKeyPressed(int key)
	{
		return keys[key];
	}

	public static boolean isMouseButtonPressed(int mb)
	{
		return mouseButtons[mb];
	}

	public static int getMouseX()
	{
		return mx;
	}

	public static int getMouseY()
	{
		return my;
	}

	public static boolean isKeyFirstDown(int key)
	{
		return keysClick[key];
	}

	public static boolean isMouseButtonFirstDown(int mb)
	{
		return mouseButtonsClick[mb];
	}

	/**
	 * <b>Only use this during <code>postUpdate</code></b>
	 */
	public static void clearFirstClicks()
	{
		for (int i = 0; i < mouseButtonsClick.length; i++)
		{
			mouseButtonsClick[i] = false;
		}
		for (int i = 0; i < keysClick.length; i++)
		{
			keysClick[i] = false;
		}
	}

}
