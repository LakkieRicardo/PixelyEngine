package net.lakkie.pixely.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils
{

	public static final int TYPE_LIST = 1;
	public static final int TYPE_SET = 2;

	private CollectionUtils()
	{
	}

	public static <T> void copyTo(Collection<T> orig, Collection<T> to)
	{
		for (T t : orig)
		{
			to.add(t);
		}
	}

	public static <T> List<T> convertToList(Collection<T> orig)
	{
		List<T> noo = new ArrayList<T>();
		for (T t : orig)
		{
			noo.add(t);
		}
		return noo;
	}

	public static <T> Set<T> convertToSet(Collection<T> orig)
	{
		Set<T> noo = new HashSet<T>();
		for (T t : orig)
		{
			noo.add(t);
		}
		return noo;
	}

	public static <T> Collection<T> convertTo(Collection<T> orig, int mode)
	{
		switch (mode)
		{
		case TYPE_LIST:
			return convertToList(orig);
		case TYPE_SET:
			return convertToSet(orig);
		default:
			return null;
		}
	}

	public static <T> List<T> copyToList(Collection<T> from)
	{
		List<T> list = new ArrayList<T>();
		for (T t : from)
		{
			list.add(t);
		}
		return list;
	}

	public static <T> Set<T> copyToSet(Collection<T> from)
	{
		Set<T> list = new HashSet<T>();
		for (T t : from)
		{
			list.add(t);
		}
		return list;
	}

	/**
	 * Removes all <tt>null</tt> elements from a collection
	 * 
	 * @param coll
	 *            The collection to remove <tt>null</tt> from
	 */
	public static <T> void nullClear(Collection<T> coll)
	{
		while (coll.contains(null))
		{
			coll.remove(null);
		}
	}

}
