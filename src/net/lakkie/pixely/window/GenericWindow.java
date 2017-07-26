package net.lakkie.pixely.window;

import net.lakkie.pixely.wrappers.FrameBuilder;
import net.lakkie.pixely.wrappers.PixelyContext;

/**
 * 
 * Represents a window that can be closed, opened, rendered, and updated
 * 
 * @author Diego
 *
 */
public class GenericWindow<T> {

	private PixelyContext context;

	private String title;
	private int width, height;
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
	 */
	public GenericWindow(PixelyContext toAdd, String title, int width, int height, Class<?> type) {
		assert toAdd != null;
		assert title != null;
		assert !(width <= 0);
		assert !(height <= 0);

		this.title = title;
		this.width = width;
		this.height = height;
		try {
			this.frame = FrameBuilder.instantiateUnsafe(title, width, height, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new PixelyContext.
	 * 
	 * @see GenericWindow#Window(PixelyContext, String, int, int, Class)
	 */
	public GenericWindow(String title, int width, int height, Class<?> type) {
		this(new PixelyContext(), title, width, height, type);
	}

	public PixelyContext getContext() {
		return context;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public Object getFrame() {
		return frame;
	}

	public Class<T> getFrameType() {
		return type;
	}

}
