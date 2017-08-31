package net.lakkie.pixely.event;

import net.lakkie.pixely.utils.Registry;

public class EventSystem {

	public final Registry<Event> eventRecord;
	/**
	 * The listener group system is here so that we don't have to iterate through
	 * all the listeners, just listeners under type, which is what we need.
	 */
	public final Registry<EventListenerGroup> listenerGroups;

	public EventSystem() {
		this.eventRecord = new Registry<Event>();
		this.listenerGroups = new Registry<EventListenerGroup>();
	}

	public EventListenerGroup getListenerGroup(Class<? extends Event> type) {
		for (EventListenerGroup group : this.listenerGroups) {
			if (type.isAssignableFrom(group.listenType)) {
				return group;
			}
		}
		return null;
	}

	public void dispatchEvent(Event event) {
		EventListenerGroup group = this.getListenerGroup(event.getClass());
		if (group == null) {
			// No listeners
			return;
		}
		
	}

}
