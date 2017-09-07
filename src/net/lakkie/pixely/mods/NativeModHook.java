package net.lakkie.pixely.mods;
import net.lakkie.pixely.utils.Registry;

public class NativeModHook {

	public static final NativeModHook inst = new NativeModHook();
	
	public Registry<IMod> mods = new Registry<IMod>();
	
	private NativeModHook() {
	}
	
	public void submit(IMod mod) {
		mods.submit(mod);
	}
	
	public void kick(IMod mod) {
		mods.kick(mod);
	}
	
}
