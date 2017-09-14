package net.lakkie.pixely.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lakkie.pixely.utils.DataPair;
import net.lakkie.pixely.utils.MappingException;
import net.lakkie.pixely.utils.Nameable;
import net.lakkie.pixely.utils.Registry;

/**
 * 
 * This class is a template for instantiating an {@link EventBase}<br>
 * A registry of all of these templates are created, so <tt>clone()</tt> is not
 * available.
 * 
 * @author Diego
 *
 */
public class EventTemplate implements Nameable
{

	public static final Registry<EventTemplate> templates = new Registry<EventTemplate>();

	/**
	 * This holds information about which index in the parameters corresponds to
	 * which name.
	 */
	public final Map<Integer, String> indexNames = new HashMap<Integer, String>();
	public final String name;

	public EventTemplate(String name)
	{
		this.name = name;
	}

	public EventTemplate(String name, List<DataPair<Integer, String>> mappings)
	{
		this(name);
		DataPair.apply(mappings, this.indexNames);
	}

	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException("This is a template, and should not be duplicated, instead, instantiated.");
	}

	public EventBase make(Object... values) throws MappingException
	{
		EventBase result = new EventBase(this.name, null);
		if (values != null)
		{
			for (int i = 0; i < values.length; i++)
			{
				if (!indexNames.containsKey(i)) { throw new MappingException("No index name specified for: " + i); }
				result.set(indexNames.get(i), values[i]);
			}
		}
		return result;
	}

	public String getName()
	{
		return this.name;
	}

}
