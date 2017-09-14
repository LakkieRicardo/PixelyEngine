package net.lakkie.pixely.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table2<K, V>
{

	private Set<TableEntry2> entries;
	private List<TableEntrySubmitCallback<K, V>> handlers;
	private int maxSize = -1;

	public Table2()
	{
		this.entries = new HashSet<TableEntry2>();
		this.handlers = new ArrayList<TableEntrySubmitCallback<K, V>>();
	}

	public boolean submit(K key, V v0)
	{
		if (entries.size() == maxSize) { return false; }
		TableEntry2 entry = new TableEntry2(key, v0);
		return entry.submit(this);
	}

	public V getFirstValue(K key)
	{
		for (TableEntry2 entry : entries)
		{
			if (entry.key.equals(key)) { return entry.v0; }
		}
		return null;
	}

	public K getFirstKey(V v0)
	{
		for (TableEntry2 entry : entries)
		{
			if (entry.v0.equals(v0)) { return entry.key; }
		}
		return null;
	}

	public void addHandler(TableEntrySubmitCallback<K, V> handler)
	{
		handlers.add(handler);
	}

	class TableEntry2
	{

		public K key;
		public V v0;

		private TableEntry2(K key, V v0)
		{
			this.key = key;
			this.v0 = v0;
		}

		private boolean submit(Table2<K, V> to)
		{
			boolean result = true;
			for (TableEntrySubmitCallback<K, V> callback : to.handlers)
			{
				result = result && callback.onSubmit(key, v0);
			}
			if (result)
			{
				to.entries.add(this);
			}
			return result;
		}

	}

	public interface TableEntrySubmitCallback<K, V>
	{

		boolean onSubmit(K from, V to);

	}

}
