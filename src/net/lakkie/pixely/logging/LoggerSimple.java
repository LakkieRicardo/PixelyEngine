package net.lakkie.pixely.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerSimple extends Logger
{

	public LoggerSimple()
	{
		super("Main", null);
	}

	public void log(LogRecord record)
	{
		if (record.getLevel() == Level.OFF)
			return;
		if (!record.getMessage().endsWith("\n"))
		{
			record.setMessage(record.getMessage() + "\n");
		}
		printf("(%s)[%s]: %s", LogUtils.getDateString(), record.getLevel().getLocalizedName(), record.getMessage());
	}

	private static void printf(String msg, Object... objects)
	{
		System.out.printf(msg, objects);
	}

}
