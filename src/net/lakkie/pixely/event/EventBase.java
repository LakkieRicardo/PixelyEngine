package net.lakkie.pixely.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lakkie.pixely.utils.DataPair;

public final class EventBase
{

	public final String name;
	public final Map<String, Object> values = new HashMap<String, Object>();

	public EventBase(String name, List<DataPair<String, Object>> pairs)
	{
		this.name = name;
		if (pairs != null)
		{
			for (DataPair<String, Object> pair : pairs)
			{
				this.set(pair.a, pair.b);
			}
		}
	}

	public Object get(String name)
	{
		return values.get(name);
	}

	public void set(String name, Object value)
	{
		this.values.put(name, value);
	}

}
