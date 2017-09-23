package net.lakkie.pixely.saving;

import org.w3c.dom.Document;

public abstract class DatabaseBase
{

	protected String filepath;
	private Document copy;

	public DatabaseBase(String filepath)
	{
		this.filepath = filepath;
		this.open();
		this.copy = this.getDocument();
		this.copy.normalize();
	}

	protected abstract void open();

	public abstract Document getDocument();

	/**
	 * Saves to a specified file relative to the workspace
	 * 
	 * @param filepath
	 *            The file to save to. For example: <tt>/rand/data/prefs.xml</tt>
	 */
	public abstract void save(String filepath);

	/**
	 * Saves with a default filepath
	 */
	public void save()
	{
		this.save(this.filepath);
	}
	
	public Document doc()
	{
		return this.copy;
	}

}
