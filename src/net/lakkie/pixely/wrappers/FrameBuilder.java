package net.lakkie.pixely.wrappers;

import java.awt.Frame;

import net.lakkie.pixely.exceptions.BuilderException;
import net.lakkie.pixely.exceptions.ReflectionCastException;

public class FrameBuilder {

	private FrameBuilder() {
	}

	/**
	 * 
	 * This will use reflection in Java to create a frame. For titles, it will
	 * search for the following things(in order):
	 * <ol>
	 * <li>setTitle(String) method</li>
	 * <li>Set title in constructor</li>
	 * <ol>
	 * 
	 * When this function tries to set the size, it will use the following
	 * methods to do so(in order):
	 * <ol>
	 * <li>setSize(int, int) using width and height</li>
	 * <li>setSize(Dimension) using a java.awt.Dimension</li>
	 * <ol>
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

	private static void doTitle(Object frame, String title, Class<?> type) throws Exception {
		if (type.getMethod("setTitle", java.lang.String.class) == null) {
			// Try to instantiate with title parameter as a fallback
			if (type.getConstructor(String.class) == null) {
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

	private static void doSize(Object frame, int width, int height, Class<?> type) throws Exception {
		// Check for setSize(int, int)
		if (type.getMethod("setSize", int.class, int.class) == null) {

			if (type.getMethod("setSize", java.awt.Dimension.class) == null) {
				throw new BuilderException("Unable to find method to set frame size for " + type.getName());
			} else {
				// Use setSize(Dimension)
				type.getMethod("setSize", java.awt.Dimension.class).invoke(frame, new java.awt.Dimension(width, height));
			}
			
		} else {
			// Check success, using method
			type.getMethod("setSize", int.class, int.class).invoke(frame, width,
					height);
		}
	}

}
