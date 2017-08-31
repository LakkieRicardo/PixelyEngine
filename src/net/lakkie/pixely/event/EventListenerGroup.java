package net.lakkie.pixely.event;

import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;

public class EventListenerGroup implements Nameable {

	public final String name;
	public final Registry<IEventListener> listeners;
	public final Class<? extends Event> listenType;
	
	public EventListenerGroup(String name, Class<? extends Event> listenType) {
		this.name = name;
		this.listeners = new Registry<IEventListener>();
		this.listenType = listenType;
	}
	
	public String getName() {
		return this.name;
	}
	
}
