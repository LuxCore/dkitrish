package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Блокировка кода от выполнения его другими потоками.
 */
@ThreadSafe
public class Lock {
	/**
	 * Обладатель блокировки.
	 */
	@GuardedBy("this")
	private Thread threadLocker;

	/**
	 * Блокирует последующий код от использования/выполнения
	 * другими потоками.
	 *
	 * @throws InterruptedException выбрасывается в случае прерывания
	 *                              работы потока исполнения.
	 */
	public synchronized void lock() throws InterruptedException {
		while (threadLocker != null && !threadLocker.equals(Thread.currentThread())) {
			wait();
		}
		threadLocker = Thread.currentThread();
	}

	/**
	 * Разблокирует участок кода после того, как поток исполнения
	 * доделал всю работу на заблокированном участке.
	 */
	public synchronized void unlock() {
		if (threadLocker != null && threadLocker.equals(Thread.currentThread())) {
			threadLocker = null;
			notify();
		}
	}
}
