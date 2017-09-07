package net.lakkie.pixely.mods.js;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.lakkie.pixely.mods.IMod;

public class JSMod implements IMod {

	public ScriptObjectMirror obj;

	private JSMod() {
	}

	public void onLoad() {
		obj.callMember("on_load");
	}

	public void onUnload() {
		obj.callMember("on_unload");
	}

	public static JSMod wrapMod(ScriptObjectMirror obj) {
		assert obj.containsKey("on_load") : "No on_load method";
		assert obj.containsKey("on_unload") : "No on_unload method";
		JSMod result = new JSMod();
		result.obj = obj;
		return result;
	}

	public String getName() {
		return (String) obj.getMember("rname");
	}

	public String getDisplayName() {
		return (String) obj.getMember("dname");
	}

}
