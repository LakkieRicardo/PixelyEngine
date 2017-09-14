package net.lakkie.pixely.mods.js;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.lakkie.pixely.utils.ExtensionFilenameFilter;

public class JSLoader
{

	static final String libs;
	static final ScriptContext ctx;

	private JSLoader()
	{
	}

	private static File[] findLibraries()
	{
		File folder = new File(Paths.get(".").toAbsolutePath().toString());
		return folder.listFiles(new ExtensionFilenameFilter("js"));
	}

	public static String getLibs()
	{
		File[] libs = findLibraries();
		StringBuilder str = new StringBuilder();
		for (File file : libs)
		{
			try
			{
				str.append(FileUtils.readFileToString(file, (String) null));
				str.append("\r\n");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		str.append("\r\n");
		return new String(str);
	}

	private static Object evalScript0(String script) throws ScriptException
	{
		NashornScriptEngine engine = (NashornScriptEngine) new NashornScriptEngineFactory().getScriptEngine();
		engine.setContext(JSContextRetriever.makeContext());
		CompiledScript prgm = engine.compile(script);
		return prgm.eval(ctx);
	}

	public static Object evalScript(String str)
	{
		StringBuilder src = new StringBuilder();
		src.append(libs);
		src.append(str);
		try
		{
			return evalScript0(new String(src));
		} catch (ScriptException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static Object eval(File file)
	{
		try
		{
			return evalScript(FileUtils.readFileToString(file, (String) null));
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	static
	{
		libs = getLibs();
		ctx = JSContextRetriever.makeContext();
	}

}
