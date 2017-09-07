package net.lakkie.pixely.mods.js;

import javax.script.ScriptContext;
import javax.script.SimpleScriptContext;

import net.lakkie.pixely.mods.NativeModHook;

public class JSContextRetriever {

	private JSContextRetriever() {
	}
	
	public static ScriptContext makeContext() {
		SimpleScriptContext ctx = new SimpleScriptContext();
		ctx.setAttribute("js_ntv", NativeModHook.inst, SimpleScriptContext.GLOBAL_SCOPE);
		return ctx;
	}
	
}
