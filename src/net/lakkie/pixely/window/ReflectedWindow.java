package net.lakkie.pixely.window;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.i.opt.IAbstractWindow;
import net.lakkie.pixely.wrappers.FrameBuilder;

/**
 * 
 * This is a window generated by Java reflection. The implementing type does
 * not<br>
 * need to inherit from {@link IAbstractWindow}
 * 
 * @author Diego
 *
 */
public class ReflectedWindow<T> extends Window<T> {

	private Object frame;
	private Class<T> type;

	/**
	 * Generates a new window object to add onto PixelyContext
	 * 
	 * @param toAdd
	 *            The context to add to
	 * @param title
	 *            The title of the frame
	 * @param width
	 *            The width of the frame
	 * @param height
	 *            The height of the frame
	 * @param type The type of the implementation of the frame
	 */
	public ReflectedWindow(PixelyContext toAdd, String title, int width, int height, Class<T> type) {
		super(toAdd, title, width, height, type);
		
		
		try {
			this.frame = FrameBuilder.instantiateUnsafe(title, width, height, type);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object getFrame() {
		return frame;
	}

	public Class<T> getFrameType() {
		return type;
	}

	protected void setTitle(String title) {
		try {
			FrameBuilder.doTitle(frame, title, frame.getClass());
		} catch (Exception e) {
			System.err.printf("Error while setting frame title to \"%s\"\n", title);
			e.printStackTrace();
		}
	}

	protected void setSize(int width, int height) {
		try {
			FrameBuilder.doSize(frame, width, height, frame.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void appendContext() {
		try {
			FrameBuilder.appendGame(frame, getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		flagContextAppended();
	}

	protected void setVisible(boolean visible) {
		try {
			if (visible) {
				// Try to center
				FrameBuilder.tryCenter(frame);
			}
			FrameBuilder.doVisible(frame, getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
