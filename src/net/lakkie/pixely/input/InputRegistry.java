package net.lakkie.pixely.input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputRegistry implements KeyListener, MouseListener, MouseMotionListener
{

	public InputRegistry(Component component)
	{
		component.addKeyListener(this);
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
	}

	public void mouseDragged(MouseEvent e)
	{
		InputManager.mx = e.getX();
		InputManager.my = e.getY();
	}

	public void mouseMoved(MouseEvent e)
	{
		InputManager.mx = e.getX();
		InputManager.my = e.getY();
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		if (!InputManager.mouseButtons[e.getButton()])
		{
			InputManager.mouseButtonsClick[e.getButton()] = true;
		}
		InputManager.mouseButtons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		InputManager.mouseButtons[e.getButton()] = false;
	}

	public void keyPressed(KeyEvent e)
	{
		if (!InputManager.keys[e.getKeyCode()])
		{
			InputManager.keysClick[e.getKeyCode()] = true;
		}
		InputManager.keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e)
	{
		InputManager.keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e)
	{
	}

}
