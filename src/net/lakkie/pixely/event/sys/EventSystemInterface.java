package net.lakkie.pixely.event.sys;

import java.lang.reflect.Method;

import net.lakkie.pixely.event.Event;
import net.lakkie.pixely.event.EventListenerGroup;
import net.lakkie.pixely.event.IEventListener;
import net.lakkie.pixely.event.sys.listener.EventListenerCategory;
import net.lakkie.pixely.utils.F;
import net.lakkie.pixely.utils.Registry;

class EventSystemInterface extends EventSystemBase {

	/**
	 * The listener group system is here so that we don't have to iterate through
	 * all the listeners, just listeners under type, which is what we need.
	 */
	public final Registry<EventListenerGroup> listenerGroups;

	public EventSystemInterface() {
		super();
		this.listenerGroups = new Registry<EventListenerGroup>();
	}

	void addListener(Object o) {
		if (!(o instanceof IEventListener)) {
			return;
		}
		IEventListener listener = (IEventListener) o;
		try {
			Method listenMethod = listener.getClass().getMethod("listen", Event.class);
			EventListenerCategory ann = listenMethod.getAnnotation(EventListenerCategory.class);
			Class<? extends Event> paramType = ann.listenerType();
			for (EventListenerGroup group : this.listenerGroups) {
				if (group.listenType.isAssignableFrom(paramType)) {
					group.listeners.submit(listener);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		F.warning("Unable to add listener " + o);
	}

	void onDispatch(Event e) {
		EventListenerGroup group = this.getListenerGroup(e.getClass());
		if (group == null) {
			// Unable to find appropriate group
			return;
		}
		for (IEventListener listener : group.listeners) {
			listener.listen(e);
		}
	}

	public EventListenerGroup getListenerGroup(Class<? extends Event> type) {
		for (EventListenerGroup group : this.listenerGroups) {
			if (type.isAssignableFrom(group.listenType)) {
				return group;
			}
		}
		return null;
	}

}
