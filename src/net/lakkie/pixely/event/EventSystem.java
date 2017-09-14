package net.lakkie.pixely.event;

import java.util.ArrayList;
import java.util.List;

public class EventSystem
{

	public final List<EventListenerCategory> cats = new ArrayList<EventListenerCategory>();

	public EventSystem()
	{
	}

	public EventListenerCategory matchStarting(String eventName)
	{
		for (EventListenerCategory cat : this.cats)
		{
			if (eventName.startsWith(cat.catPrefix)) { return cat; }
		}
		return null;
	}

	public void dispatch(EventBase event)
	{
		EventListenerCategory cat = this.matchStarting(event.name);
		cat.dispatchChecked(event);
	}

	public void addListener(EventListener listener)
	{
		for (String input : listener.getListeningFor())
		{
			EventListenerCategory current = this.matchStarting(input);
			if (current != null)
				current.listeners.add(listener);
		}
	}

	public void removeListener(EventListener listener)
	{
		for (EventListenerCategory cat : this.cats)
		{
			if (cat.listeners.contains(listener))
			{
				cat.listeners.remove(listener);
			}
		}
	}

}
