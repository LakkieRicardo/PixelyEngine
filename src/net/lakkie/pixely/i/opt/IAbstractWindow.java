package net.lakkie.pixely.i.opt;

import java.awt.Canvas;

public interface IAbstractWindow {

	public abstract void setTitle(String title);
	
	public abstract void setSize(int width, int height);
	
	public abstract void setVisible(boolean visible);
	
	public abstract void addCanvas(Canvas canvas);
	
	default void center() {
	}
	
}
