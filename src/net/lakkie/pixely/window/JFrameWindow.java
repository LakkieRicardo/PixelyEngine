package net.lakkie.pixely.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.input.InputRegistry;
import net.lakkie.pixely.math.Vector4;

public class JFrameWindow extends Window<JFrame> implements WindowListener
{

	private JFrame frame;

	public JFrameWindow(PixelyContext context, String title, int width, int height)
	{
		super(context, title, width, height, JFrame.class);

		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.addWindowListener(this);
		setTitle(title);
		setSize(width, height);
	}

	protected void setTitle(String title)
	{
		frame.setTitle(title);
	}

	protected void setSize(int width, int height)
	{
		frame.setSize(width, height);
	}

	public void appendContext()
	{
		frame.add(getContext().getCanvas());
		new InputRegistry(frame);
		new InputRegistry(getContext().getCanvas());
		flagContextAppended();
	}

	protected void setVisible(boolean visible)
	{
		if (visible)
		{
			frame.setLocationRelativeTo(null);
		}
		frame.setVisible(visible);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void updateCanvasWithFrame()
	{
		Application.targetWidth = this.getFrame().getWidth();
		Application.targetHeight = this.getFrame().getHeight();
	}

	public Vector4 getSize()
	{
		return new Vector4(0, 0, this.getFrame().getWidth(), this.getFrame().getHeight());
	}

	public void windowOpened(WindowEvent e)
	{

	}

	public void windowClosing(WindowEvent e)
	{
		Application.exit();
	}

	public void windowClosed(WindowEvent e)
	{

	}

	public void windowIconified(WindowEvent e)
	{

	}

	public void windowDeiconified(WindowEvent e)
	{

	}

	public void windowActivated(WindowEvent e)
	{

	}

	public void windowDeactivated(WindowEvent e)
	{

	}

}
