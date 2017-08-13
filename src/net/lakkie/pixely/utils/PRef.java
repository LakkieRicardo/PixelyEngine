package net.lakkie.pixely.utils;

public class PRef<T> {
	
	private volatile T var;

	public void set(T v) {
		this.var = v;
	}

	public T get() {
		return this.var;
	}
	
}
