package net.lakkie.pixely.context;

import java.awt.Canvas;
import java.util.HashMap;
import java.util.Map;

public class PixelyContext {
	
	private Map<String, Object> fields;
	
	public PixelyContext(ContextProvider provider) {
		this.fields = new HashMap<String, Object>();
		provider.setup(this);
	}
	
	public Object get(String name) {
		return fields.get(name);
	}
	
	public void set(String name, Object value) {
		fields.put(name, value);
	}
	
	public void put(Canvas canvas, double ups, int width, int height, boolean debug) {
		fields.put("canvas", canvas);
		fields.put("updates_per_second", ups);
		fields.put("width", width);
		fields.put("height", height);
		fields.put("debug", debug);
	}
	
	public double getUPS() {
		return (double) fields.get("updates_per_second");
	}
	
	public Canvas getCanvas() {
		return (Canvas) fields.get("canvas");
	}
	
	public int getWidth() {
		return (int) fields.get("width");
	}
	
	public int getHeight() {
		return (int) fields.get("height");
	}
	
	public boolean isDebugActive() {
		return (boolean) fields.get("debug");
	}
	
}
