package net.lakkie.pixely.event.sys.listener;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.lakkie.pixely.event.Event;

@Retention(RUNTIME)
@Target(METHOD)
public @interface EventListenerCategory {

	Class<? extends Event> listenerType();
	
}
