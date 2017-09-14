package net.lakkie.pixely.utils;

public class StringFormatter
{

	private StringFormatter()
	{
	}

	/**
	 * 
	 * Does operations like the following<br>
	 * <table border="1">
	 * <tr>
	 * <td>In</td>
	 * <td>Out</td>
	 * </tr>
	 * <tbody>
	 * <tr>
	 * <td><tt>engine</tt></td>
	 * <td><tt>Engine</tt></td>
	 * </tr>
	 * <tr>
	 * <td><tt>COLOR</tt></td>
	 * <td><tt>Color</tt></td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str)
	{
		str = str.toLowerCase();
		char[] text = str.toCharArray();
		text[0] = Character.toUpperCase(text[0]);
		return new String(text);
	}

}
