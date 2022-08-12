package sm.ciscoop.preno.utils;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import sm.ciscoop.stdlibs.baseutils.logging.StdLog;
import sm.ciscoop.stdlibs.baseutils.logging.loggers.CISLoggerEntity;
import sm.ciscoop.stdlibs.baseutils.logging.loggers.CISStdLogger;


@CISLoggerEntity(AppLogger.NAME)
public class AppLogger extends CISStdLogger {
	public static final String	NAME	= "sm.ciscoop.preno";
	private static AppLogger		_instance;

	public static AppLogger get() {
		if (AppLogger._instance == null) {
			AppLogger._instance = new AppLogger();
		}
		return AppLogger._instance;
	}

	static {
		StdLog.register(AppLogger.class);
	}

	protected AppLogger() {
		_logger = Logger.getLogger(AppLogger.NAME);
	}

	public static void log(Level l, String message) {
		AppLogger.get().getLogger().log(l, message);
	}

	public static void debug(String message) {
		AppLogger.get().getLogger().debug(message);
	}

	public static void info(String message) {
		AppLogger.get().getLogger().info(message);
	}

	public static void warn(String message) {
		AppLogger.get().getLogger().warn(message);
	}

	public static void error(String message) {
		AppLogger.get().getLogger().error(message);
	}

	public static void error(String message, Throwable ex) {
		AppLogger.get().getLogger().error(message, ex);
	}

	public static void fatal(String message, Throwable ex) {
		AppLogger.get().getLogger().fatal(message);
	}
}
