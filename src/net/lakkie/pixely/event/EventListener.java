package net.lakkie.pixely.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class EventListener
{

	public final int priority;
	private Set<String> listeningFor;

	public EventListener(int priority, Set<String> listeningFor)
	{
		this.priority = priority;
		this.listeningFor = listeningFor;
	}

	public EventListener(int priority, List<String> listeningFor)
	{
		this.priority = priority;
		this.listeningFor = new HashSet<String>();
		for (String s : listeningFor)
		{
			this.listeningFor.add(s);
		}
	}

	public abstract void listen(EventBase ev);

	public Set<String> getListeningFor()
	{
		return this.listeningFor;
	}

}
