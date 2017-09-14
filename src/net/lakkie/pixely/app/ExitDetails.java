package net.lakkie.pixely.app;

import java.io.IOException;
import java.io.OutputStream;

public class ExitDetails
{

	public String details;
	public OutputStream printer;
	public boolean print = true;

	public ExitDetails(String details, OutputStream printer)
	{
		this.details = details;
		this.printer = printer;
	}

	public String toString()
	{
		return String.format("Details: %s", this.details);
	}

	public ExitDetails clone()
	{
		return new ExitDetails(this.details, this.printer);
	}

	public CharSequence getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	public void setPrint(boolean print)
	{
		this.print = print;
	}

	public boolean shouldPrint()
	{
		return print;
	}

	private void printDetails0()
	{
		String string = (String) details;
		try
		{
			this.printer.write(string.getBytes());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void printDetails()
	{
		if (shouldPrint())
		{
			printDetails0();
		}
	}

}
