package net.lakkie.pixely.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogUtils {

	private static boolean inited = false;
	private static String using = "Main";
	private static final SimpleDateFormat format = new SimpleDateFormat("kk-mm-ss");
	
	public static Logger get() {
		runAction();
		return LogManager.getLogManager().getLogger(LogUtils.using);
	}
	
	public static void info(Object msg) {
		runAction();
		Logger logger = get();
		if (logger == null) { // Scala is sensitive about null checks, it throws a NullPointer if no null check, or it could be something else
			return;
		}
		if (msg == null) {
			logger.info("null");
			return;
		}
		logger.info(msg.toString());
	}
	
	public static void info(String msg, Object... args) {
		info(String.format(msg, args));
	}
	
	public static void warning(Object msg) {
		runAction();
		get().warning(msg.toString());
	}
	
	public static void severe(Object msg) {
		runAction();
		get().severe(msg.toString());
	}
	
	public static Logger getMainLogger() {
		runAction();
		return LogManager.getLogManager().getLogger("Main");
	}
	
	public static void makeLogger(Logger inst) {
		LogManager.getLogManager().addLogger(inst);
		using = inst.getName();
	}
	
	public static String getDateString() {
		runAction();
		return format.format(new Date());
	}
	
	private static void runAction() {
		if (!inited) {
			init();
		}
	}
	
	public static void init() {
		makeLogger(new LoggerSimple());
		inited = true;
	}
	
}
