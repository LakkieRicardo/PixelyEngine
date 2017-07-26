package net.lakkie.pixely.window;

import net.lakkie.pixely.context.PixelyContext;

/**
 * 
 * Represents a window that can be closed, opened, rendered, and updated
 * 
 * @author Diego
 *
 * @param <T>
 *            The type object of the window.
 */
public abstract class Window<T> {

	protected PixelyContext context;

	protected String title;
	protected int width, height;
	private Class<T> type;

	public Window(PixelyContext context, String title, int width, int height, Class<T> type) {
		assert context != null;
		assert title != null;
		assert !(width <= 0);
		assert !(height <= 0);

		this.context = context;
		this.title = title;
		this.width = width;
		this.height = height;

		this.type = type;
	}

	protected abstract void setTitle(String title);

	protected abstract void setSize(int width, int height);

	public abstract void appendContext();

	protected abstract void setVisible(boolean visible);

	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		setSize(width, height);
	}

	public void rename(String title) {
		this.title = title;
		setTitle(title);
	}

	public void show() {
		getContext().set("frame_visible", true);
		setVisible(true);
	}

	public void hide() {
		getContext().set("frame_visible", false);
		setVisible(false);
	}

	public boolean isVisible() {
		return (boolean) getContext().get("frame_visible");
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

	public Class<T> getType() {
		return type;
	}

}
