package net.lakkie.pixely.utils;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.io.FilenameUtils;

public class ExtensionFilenameFilter implements FilenameFilter
{

	private final String ext;

	public ExtensionFilenameFilter(String ext)
	{
		this.ext = ext;
	}

	public boolean accept(File dir, String name)
	{
		return FilenameUtils.getExtension(name).equals(this.ext);
	}

	public String getExtension()
	{
		return this.ext;
	}

}
