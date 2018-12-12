package ru.job4j.logging;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test of logging.
 */
public class LogbackDemoTest {
	/**
	 * Testing of all log methods.
	 * Stub assertion.
	 */
	@Test
	public void testLogs() {
		LogbackDemo log = new LogbackDemo();
		log.doLog();
		assertTrue(true);
	}
}