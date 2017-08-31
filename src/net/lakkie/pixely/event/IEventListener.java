package net.lakkie.pixely.event;

import net.lakkie.pixely.utils.Nameable;

public interface IEventListener extends Nameable {

	void listen(Event event);
	
}
