package net.lakkie.pixely.window;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.i.IAbstractWindow;
import net.lakkie.pixely.input.InputRegistry;
import net.lakkie.pixely.wrappers.FrameBuilder;

/**
 * 
 * Represents a window that is implemented by {@link IAbstractWindow}
 * 
 * @author Diego
 *
 * @param <T>
 *            The implementation type
 */
public class ImplementedWindow<T extends IAbstractWindow> extends Window<T> {

	private T frame;

	public ImplementedWindow(PixelyContext context, String title, int width, int height, Class<T> type) {
		super(context, title, width, height, type);
		try {
			frame = FrameBuilder.instantiateSafe(title, width, height, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setTitle(String title) {
		getFrame().setTitle(title);
	}

	protected void setSize(int width, int height) {
		getFrame().setSize(width, height);
	}

	public T getFrame() {
		return frame;
	}

	public void appendContext() {
		frame.addCanvas(getContext().getCanvas());
		new InputRegistry(getContext().getCanvas());
		flagContextAppended();
	}

	protected void setVisible(boolean visible) {
		if (visible) {
			frame.center();
		}
		frame.setVisible(visible);
	}

}
