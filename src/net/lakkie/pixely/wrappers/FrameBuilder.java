package net.lakkie.pixely.wrappers;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Frame;

import net.lakkie.pixely.context.PixelyContext;
import net.lakkie.pixely.exceptions.BuilderException;
import net.lakkie.pixely.exceptions.ReflectionCastException;
import net.lakkie.pixely.i.IAbstractWindow;
import net.lakkie.pixely.input.InputRegistry;

public class FrameBuilder {

	private FrameBuilder() {
	}

	/**
	 * 
	 * This will use reflection to create a frame. For titles, it will
	 * search for the following things(in order):
	 * <ol>
	 * <li><code>setTitle(String) method</code></li>
	 * <li><code>Set title in constructor</code></li>
	 * </ol>
	 * 
	 * When this function tries to set the size, it will use the following
	 * methods to do so(in order):
	 * <ol>
	 * <li><code>setSize(int, int) using width and height</code></li>
	 * <li><code>setSize(Dimension) using a java.awt.Dimension</code></li>
	 * </ol>
	 * 
	 * This function supports anything inheriting {@link java.awt.Frame}
	 * 
	 * @param title
	 *            The title of the frame
	 * @param width
	 *            The width of the frame
	 * @param height
	 *            The height of the frame
	 * @param type
	 *            The frame type
	 * @return A generated frame under title, width, and height
	 * @throws Exception
	 *             Under multiple circumstances, look at source code for furthur
	 *             details
	 */
	public static Object instantiateUnsafe(String title, int width, int height, Class<?> type) throws Exception {
		Object frame = type.newInstance();
		if (frame.getClass() != type) {
			throw new ReflectionCastException("Unable to create instance of " + type.getName());
		}
		doTitle(frame, title, type);
		doSize(frame, width, height, type);

		return frame;
	}

	public static void doTitle(Object frame, String title, Class<?> type) throws Exception {
		if (!methodExists(frame.getClass(), "setTitle", java.lang.String.class)) {
			// Try to instantiate with title parameter as a fallback
			if (!constructorExists(frame.getClass(), String.class)) {
				// All failed, throw exception
				throw new BuilderException("Unable to find method to set the frame title for " + type.getName());
			} else {
				// Use constructor
				frame = (Frame) type.getConstructor(String.class).newInstance(title);
			}
		} else {
			// Use setTitle method
			frame.getClass().getMethod("setTitle", java.lang.String.class).invoke(frame, title);
		}
	}

	public static void doSize(Object frame, int width, int height, Class<?> type) throws Exception {
		// Check for setSize(int, int)
		if (!methodExists(frame.getClass(), "setSize", int.class, int.class)) {

			if (type.getMethod("setSize", java.awt.Dimension.class) == null) {
				throw new BuilderException("Unable to find method to set frame size for " + type.getName());
			} else {
				// Use setSize(Dimension)
				type.getMethod("setSize", java.awt.Dimension.class).invoke(frame,
						new java.awt.Dimension(width, height));
			}

		} else {
			// Check success, using method
			type.getMethod("setSize", int.class, int.class).invoke(frame, width, height);
		}
	}

	/**
	 * 
	 * Currently only appends the canvas. Checks for the following functions to
	 * be able to append it:
	 * <ol>
	 * <li><code>add(java.awt.Component)</code></li>
	 * <li><code>add(java.awt.Canvas)</code></li>
	 * <li><code>setCanvas(java.awt.Canvas)</code></li>
	 * </ol>
	 * 
	 * @param context
	 */
	public static void appendGame(Object frame, PixelyContext context) throws Exception {

		// Does add(java.awt.Component) exist in this class?
		if (!methodExists(frame.getClass(), "add", Component.class)) {

			// It doesn't, let's check for add(java.awt.Canvas)
			if (!methodExists(frame.getClass(), "add", Canvas.class)) {

				// That doesn't exist? Finally we'll check for
				// setCanvas(java.awt.Canvas)
				if (!methodExists(frame.getClass(), "setCanvas", Canvas.class)) {
					// None of these exist, this is an invalid class, let's
					// throw an exception
					throw new BuilderException(
							"Unable to find method to add canvas to frame for " + frame.getClass().getName());
				} else {

					// Use setCanvas(Canvas)
					frame.getClass().getMethod("setCanvas", Canvas.class).invoke(frame, context.getCanvas());
					return;
				}
			} else {

				// Use add(Canvas)
				frame.getClass().getMethod("add", Canvas.class).invoke(frame, context.getCanvas());
			}
		} else {

			// Use add(Component)
			frame.getClass().getMethod("add", Component.class).invoke(frame, context.getCanvas());
		}
		
		new InputRegistry(context.getCanvas());
	}

	/**
	 * 
	 * Sets a frame visible or hidden. Checks for a
	 * <code>setVisible(boolean)</code> method, then <code>show()</code> or
	 * <code>hide()</code>
	 * 
	 * @param frame
	 * @param context
	 */
	public static void doVisible(Object frame, PixelyContext context) throws Exception {
		boolean visible = (boolean) context.get("frame_visible");
		if (frame.getClass().getMethod("setVisible", boolean.class) == null) {
			
			if (!methodExists(frame.getClass(), "show") || !methodExists(frame.getClass(), "hide")) {
				throw new BuilderException("Unable to find setVisible(boolean), show(), or hide() in " + frame.getClass().getName());
			} else {
				if (visible) {
					// hide
					frame.getClass().getMethod("hide").invoke(frame);
				} else {
					// show
					frame.getClass().getMethod("show").invoke(frame);
				}
			}
			
		} else {
			
			// Use setVisible(boolean)
			frame.getClass().getMethod("setVisible", boolean.class).invoke(frame, visible);
		}
	}

	/**
	 * 
	 * A much more safe option for creating windows, based off of interfaces
	 * instead of reflection. <br>
	 * <b>WARN: </b>This function still uses reflection to create the window,
	 * and as such is not entirely safe.
	 * 
	 * @param <T>
	 *            The implementation type
	 * @param title
	 *            The title of the window
	 * @param width
	 *            The width of the window
	 * @param height
	 *            The height of the window
	 * @param type
	 *            The implementation class of the window
	 * @return An implementation
	 * @throws Exception
	 *             When the instantiation of the window was unsuccessful
	 * @see {@link java.lang.Class#newInstance()}
	 */
	public static <T extends IAbstractWindow> T instantiateSafe(String title, int width, int height, Class<T> type)
			throws Exception {
		T t = type.newInstance();
		t.setTitle(title);
		t.setSize(width, height);
		return t;
	}

	public static void tryCenter(Object frame) {
		
		try {
			if (!methodExists(frame.getClass(), "setLocationRelativeTo", java.awt.Component.class)) {
				
				if (!methodExists(frame.getClass(), "center")) {
					return;
				} else {
					frame.getClass().getMethod("center").invoke(frame);
				}
				
			} else {
				frame.getClass().getMethod("setLocationRelativeTo", java.awt.Component.class).invoke(frame, new Object[] { null });
			}
		} catch (Exception e) {
			System.out.println("WARNING: Unable to center frame of type " + frame.getClass().getName());
		}
		
	}
	
	public static boolean methodExists(Class<?> type, String name, Class<?>... args) {
		try {
			type.getMethod(name, args);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
	
	public static boolean constructorExists(Class<?> type, Class<?>... args) {
		try {
			type.getConstructor(args);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

}
