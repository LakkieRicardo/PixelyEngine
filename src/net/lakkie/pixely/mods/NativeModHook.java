package net.lakkie.pixely.mods;

import java.util.Scanner;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.lakkie.pixely.mods.js.JSLoader;
import net.lakkie.pixely.mods.js.JSMod;
import net.lakkie.pixely.utils.F;
import net.lakkie.pixely.utils.Registry;

public class NativeModHook {

	public static final NativeModHook inst = new NativeModHook();

	public Registry<IMod> mods = new Registry<IMod>();

	private NativeModHook() {
	}

	public void submit(IMod mod) {
		mods.submit(mod);
	}

	public void submitJS(ScriptObjectMirror obj) {
		this.submit(JSMod.wrapMod(obj));
	}

	public void kick(IMod mod) {
		mods.kick(mod);
	}
	
	public void kickJS(ScriptObjectMirror obj) {
		mods.kick(JSMod.wrapMod(obj));
	}
	
	public void logInfo(String msg) {
		F.info(msg);
	}
	
	public void logWarning(String msg) {
		F.warning(msg);
	}
	
	public void logSevere(String msg) {
		F.severe(msg);
	}
	
	public ScriptObjectMirror includeJS(String filepath) {
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(NativeModHook.class.getResourceAsStream(filepath));
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine());
			sb.append('\n');
		}
		scanner.close();
		Object obj = JSLoader.evalScript(new String(sb));
		if (obj instanceof ScriptObjectMirror) {
			return (ScriptObjectMirror) obj;
		} else {
			return null;
		}
	}

}
