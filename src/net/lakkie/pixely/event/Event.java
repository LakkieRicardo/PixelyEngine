package net.lakkie.pixely.event;

import net.lakkie.pixely.utils.Nameable;

public class Event implements Nameable {

	public final String name;
	
	public Event(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
