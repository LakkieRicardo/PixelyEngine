package net.lakkie.pixely.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogUtils {

	private static String using = "Main";
	private static final SimpleDateFormat format = new SimpleDateFormat("kk-mm-ss");
	
	private LogUtils() {
	}
	
	public static Logger get() {
		return LogManager.getLogManager().getLogger(LogUtils.using);
	}
	
	public static Logger getMainLogger() {
		return LogManager.getLogManager().getLogger("Main");
	}
	
	public static void makeLogger(Logger inst) {
		LogManager.getLogManager().addLogger(inst);
		using = inst.getName();
	}
	
	public static String getDateString() {
		return format.format(new Date());
	}
	
	static {
		makeLogger(new LoggerSimple());
	}
	
}
