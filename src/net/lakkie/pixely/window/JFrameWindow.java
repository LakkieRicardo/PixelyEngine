package net.lakkie.pixely.window;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.lakkie.pixely.app.Application;
import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.input.InputRegistry;

public class JFrameWindow extends Window<JFrame> {

	private JFrame frame;
	
	public JFrameWindow(PixelyContext context, String title, int width, int height) {
		super(context, title, width, height, JFrame.class);
		
		this.frame = new JFrame();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle(title);
		setSize(width, height);
	}

	protected void setTitle(String title) {
		frame.setTitle(title);
	}

	protected void setSize(int width, int height) {
		frame.setSize(width, height);
	}

	public void appendContext() {
		frame.add(getContext().getCanvas());
		new InputRegistry(frame);
		new InputRegistry(getContext().getCanvas());
		flagContextAppended();
	}

	protected void setVisible(boolean visible) {
		if (visible) {
			frame.setLocationRelativeTo(null);
		}
		frame.setVisible(visible);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void updateSizeWithApplication() {
		Application.targetWidth = this.getFrame().getWidth();
		Application.targetHeight = this.getFrame().getHeight();
	}

}
