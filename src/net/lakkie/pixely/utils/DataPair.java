package net.lakkie.pixely.utils;

import java.util.List;
import java.util.Map;

public class DataPair<A, B>
{

	public A a;
	public B b;

	public DataPair(A a, B b)
	{
		this.a = a;
		this.b = b;
	}

	public DataPair()
	{
	}

	public static <A, B> void apply(List<DataPair<A, B>> mappings, Map<A, B> map)
	{
		if (mappings != null)
		{
			for (DataPair<A, B> pair : mappings)
			{
				map.put(pair.a, pair.b);
			}
		}
	}

}
