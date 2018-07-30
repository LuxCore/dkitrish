package ru.job4j.monitorsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс счётчик.
 */
@ThreadSafe
public class Count {
	/**
	 * Счётчик.
	 */
	@GuardedBy("this")
	private int value;

	/**
	 * Инкрементирование счётчика.
	 */
	public synchronized void increment() {
		value++;
	}

	/**
	 * Получение значения счётчика.
	 *
	 * @return значение счётчика.
	 */
	public synchronized int getValue() {
		return value;
	}
}
