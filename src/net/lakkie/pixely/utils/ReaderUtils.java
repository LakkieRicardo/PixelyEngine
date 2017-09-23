package net.lakkie.pixely.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderUtils
{

	private ReaderUtils()
	{
	}

	public static BufferedReader inputToReader(InputStream input)
	{
		return new BufferedReader(new InputStreamReader(input));
	}

	public static String readReader(BufferedReader reader) throws IOException
	{
		StringBuilder result = new StringBuilder();
		
		while (reader.ready())
		{
			result.append(reader.readLine());
			result.append(System.lineSeparator());
		}
		
		return new String(result);
	}
	
	/**
	 * Guarantees the file to be read, or an empty String will be returned.
	 * @param reader The reader to read from
	 * @return The text file if successfully read, otherwise an empty string
	 */
	public static String readReaderSafe(BufferedReader reader)
	{
		try
		{
			return ReaderUtils.readReader(reader);
		} catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static String readInputStream(InputStream input)
	{
		return ReaderUtils.readReaderSafe(ReaderUtils.inputToReader(input));
	}

}
