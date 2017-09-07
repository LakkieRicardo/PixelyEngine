package net.lakkie.pixely.event.sys;

import net.lakkie.pixely.event.Event;
import net.lakkie.pixely.utils.Registry;

abstract class EventSystemBase {

	public final Registry<Event> eventRecord;

	public EventSystemBase() {
		this.eventRecord = new Registry<Event>();
	}
	
	abstract void onDispatch(Event e);
	
	abstract void addListener(Object o);
	
	public void dispatchEvent(Event e) {
		this.onDispatch(e);
		this.eventRecord.submit(e);
	}

}
