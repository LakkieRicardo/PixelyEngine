package net.lakkie.pixely.mods.js;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.lakkie.pixely.utils.CollectionUtils;
import net.lakkie.pixely.utils.ReaderUtils;

public class JSLoader
{

	static final String libs;
	static final ScriptContext ctx;

	private JSLoader()
	{
	}

	private static List<InputStream> findLibraries()
	{
		// Using a list because order matters
		List<InputStream> result = new ArrayList<InputStream>();

		// TODO: Read a file and look for all libraries
		result.add(JSLoader.class.getResourceAsStream("/js/lib_0_bindings.js"));
		result.add(JSLoader.class.getResourceAsStream("/js/lib_1_utils.js"));
		result.add(JSLoader.class.getResourceAsStream("/js/lib_2_mods.js"));
		
		CollectionUtils.nullClear(result);
		
		return result;
	}

	public static String getLibs()
	{
		List<InputStream> libs = findLibraries();
		StringBuilder str = new StringBuilder();
		for (InputStream input : libs)
		{
			str.append(ReaderUtils.readInputStream(input));
			str.append("\r\n");
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
