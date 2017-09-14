package net.lakkie.pixely.saving;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SimpleDatabase
{

	/**
	 * Using list to fix reverse loading order issue
	 */
	private final List<DBNode> nodeSet = new ArrayList<DBNode>();

	/**
	 * Makes a new SimpleDatabase and loads to a file
	 * 
	 * @param filepath
	 *            The file to load
	 */
	public SimpleDatabase(String filepath)
	{
		this.load(filepath);
	}

	public SimpleDatabase()
	{
	}

	public String get(String key, boolean ignoreCase)
	{
		for (DBNode node : this.nodeSet)
		{
			if (ignoreCase ? node.key.equalsIgnoreCase(key) : node.value.equals(key)) { return node.value; }
		}
		return null;
	}

	public void put(String key, String value)
	{
		for (DBNode node : this.nodeSet)
		{
			if (node.key.equals(key))
			{
				node.value = value;
				return;
			}
		}
		new DBNode(key, value); // Constructing a new DBNode automatically adds it to the list. See constructor
								// with no parameters in DBNode
	}

	public List<DBNode> nodeRef()
	{
		return this.nodeSet;
	}

	/**
	 * Loads from a certain file, usually called during
	 * {@link SimpleDatabase#SimpleDatabase(String)} constructor
	 * 
	 * @param filepath
	 */
	public void load(String filepath)
	{
		/*
		 * Loading a resource as a stream from the current class works when exporting
		 * the jar, while using java.io.File doesn't work. I don't know why, if someone
		 * knows message me.
		 */
		InputStream res = SimpleDatabase.class.getResourceAsStream(filepath);
		// This is incase the resource(file) was unable to be found
		if (res == null) { return; }
		Scanner scanner = new Scanner(res);

		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			this.parseLine(line); // Split up into multiple methods to be more clean, I think
		}

		/*
		 * Through java.lang.Object#finalize(), this should normally close. We will just
		 * close it in the case that it's not closed.
		 */
		scanner.close();

		// There is a bug where it reads the file backwards, so let's flip it to fix
		// that issue.
		Collections.reverse(this.nodeSet);
	}

	private void parseLine(String line)
	{
		if (line.contains(":"))
		{
			String key = line.substring(0, line.indexOf(':'));
			String value = line.substring(line.indexOf(':') + 1);
			new DBNode(key, value);
		}
	}

	/**
	 * Saves to a certain file
	 * 
	 * @param filepath
	 *            The relative path to the file
	 */
	public void save(String filepath)
	{
		try
		{
			FileOutputStream out = new FileOutputStream(SimpleDatabase.class.getResource(filepath).toURI().toString().substring(6));

			StringBuilder export = new StringBuilder();
			Collections.reverse(this.nodeSet); // Export in the correct order
			for (DBNode node : this.nodeSet)
			{
				export.append(node.key);
				export.append(":");
				export.append(node.value);
				export.append("\r\n");
			}
			Collections.reverse(this.nodeSet); // Fix the collection

			out.write(new String(export).getBytes());

			out.flush();
			out.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * A simple PoD to hold an entry in the database, initialized to <tt>""</tt>
	 * 
	 * @author Diego
	 */
	public class DBNode
	{
		public String key = "", value = "";

		public DBNode(String key, String value)
		{
			this(); // Call the constructor with no parameters to add to node set
			this.key = key;
			this.value = value;
		}

		public DBNode()
		{
			SimpleDatabase.this.nodeSet.add(this);
			/*
			 * Get parent nested class and add to node set(we are able to do this because
			 * this is a non-static nested class, if you don't understand, see:
			 * https://stackoverflow.com/questions/1353309/java-static-vs-inner-class
			 */
		}

		public String toString()
		{
			return "(" + this.key + ", " + this.value + ")"; // For debugging
		}

	}

}
