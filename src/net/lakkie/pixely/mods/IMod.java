package net.lakkie.pixely.mods;

import net.lakkie.pixely.utils.Nameable;

public interface IMod extends Nameable {

	void onLoad();
	
	void onUnload();
	
	default String getRealName() {
		return this.getName();
	}
	
	String getDisplayName();
	
}
