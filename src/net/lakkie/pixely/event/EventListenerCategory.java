package net.lakkie.pixely.event;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Splits certain listeners into categories to make iterating faster. The
 * listeners split into categories are by the name prefixes in the events, for
 * example:<br>
 * <tt></tt>
 * 
 * @author Diego
 *
 */
public class EventListenerCategory
{

	public final Set<EventListener> listeners = new HashSet<EventListener>();
	public final String catPrefix;
	public final EventSystem parent;

	public EventListenerCategory(EventSystem parent, String catPrefix)
	{
		this.catPrefix = catPrefix;
		this.parent = parent;
	}

	public void dispatchChecked(EventBase event)
	{
		for (EventListener listener : this.listeners)
		{
			listener.listen(event);
		}
	}

}