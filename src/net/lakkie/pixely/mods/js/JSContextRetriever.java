package net.lakkie.pixely.mods.js;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

import net.lakkie.pixely.mods.NativeModHook;
import net.lakkie.pixely.utils.F;

public class JSContextRetriever
{

	private JSContextRetriever()
	{
	}

	public static ScriptContext makeContext()
	{
		SimpleScriptContext ctx = new SimpleScriptContext();
		ctx.setBindings(makeBindings(), SimpleScriptContext.GLOBAL_SCOPE);
		return ctx;
	}

	public static Bindings makeBindings()
	{
		SimpleBindings bindings = new SimpleBindings();
		bindings.put("ntv", NativeModHook.inst);
		bindings.put("dlog", F.get());
		return bindings;
	}

}
