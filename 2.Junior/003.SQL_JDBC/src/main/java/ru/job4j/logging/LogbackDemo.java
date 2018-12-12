package ru.job4j.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logging demo.
 */
public class LogbackDemo {
	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LogbackDemo.class);

	/**
	 * Call of log methods of all possible levels.
	 */
	public void doLog() {
		int version = 1;
		LOG.trace("trace message {}", version);
		LOG.debug("debug message {}", version);
		LOG.info("info message {}", version);
		LOG.warn("warn message {}", version);
		LOG.error("error message {}", version);
	}
}
