package ru.job4j.jmm;

import org.junit.Test;

/**
 * Рассмотрение проблемы видимости общих ресурсов для потоков.
 */
public class VisibilityProblemTest {
	/**
	 * Рассмотрение проблемы видимости общих ресурсов для потоков.
	 */
	@Test
	public void test() {
		SharedObject sharedObject = new SharedObject();
		Thread writerThread = new WriterThread(sharedObject);
		Thread readerThread1 = new ReaderThread(sharedObject);
		Thread readerThread2 = new ReaderThread(sharedObject);
		writerThread.start();
		readerThread1.start();
		readerThread2.start();
		try {
			writerThread.join();
			readerThread1.join();
			readerThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
