package net.lakkie.pixely.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Registry<T extends Nameable> implements Iterable<T>
{

	private Set<T> elems;

	public Registry(T[] startingElements)
	{
		elems = new HashSet<T>();
		if (startingElements != null)
		{
			for (T t : startingElements)
			{
				elems.add(t);
			}
		}
	}

	public Registry()
	{
		this(null);
	}

	/**
	 * Uses {@link HashSet} to implement the elements.
	 * 
	 * @return A copy of the elements in the registry.
	 */
	public Set<T> getElements()
	{
		return new HashSet<T>(elems);
	}

	/**
	 * Finds an object with a name of <tt>name</tt>
	 * 
	 * @param name
	 *            The name of the object
	 * @return Null if no object was found, an object if one was found.
	 */
	public T get(String name, boolean ignoreCase)
	{
		Iterator<T> it = elems.iterator();
		while (it.hasNext())
		{
			T next = it.next();
			if (ignoreCase && next.getName().equalsIgnoreCase(name))
			{
				return next;
			} else if (!ignoreCase && next.getName().equals(name))
			{
				return next;
			} else
			{
				continue;
			}
		}
		return null;
	}

	/**
	 * Defaults to ignoreCase being true.
	 * 
	 * @param name
	 *            The name of the object
	 * @return Null if no object was found, an object if one was found.
	 */
	public T get(String name)
	{
		return get(name, true);
	}

	public void submit(T t)
	{
		elems.add(t);
	}

	public boolean kick(T t)
	{
		return elems.remove(t);
	}

	/**
	 * Displays all the elements in the registry, like the following:<br>
	 * <tt>[something, b, element, 43]</tt>
	 */
	public String toString()
	{
		StringBuilder result = new StringBuilder('[');
		int i = 0;
		for (T t : this.elems)
		{
			if (i == 0)
			{
				result.append(t.getName());
			} else
			{
				result.append(", ");
				result.append(t.getName());
			}
			i++;
		}
		result.append(']');
		return new String(result);
	}

	public Iterator<T> iterator()
	{
		return getElements().iterator();
	}

}
