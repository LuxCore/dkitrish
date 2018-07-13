package ru.job4j.jmm;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Рассмотрение гонки потоков.
 */
public class RaceConditionTest {
	/**
	 * Гонка пишущих потоков.
	 */
	@Test
	public void test() {
		SharedObject shared = new SharedObject();
		Thread thread1 = new WriterThread(shared);
		Thread thread2 = new WriterThread(shared);
		Thread thread3 = new WriterThread(shared);
		Thread thread4 = new WriterThread(shared);
		Thread thread5 = new WriterThread(shared);
		Thread thread6 = new WriterThread(shared);
		Thread thread7 = new WriterThread(shared);
		Thread thread8 = new WriterThread(shared);
		Thread thread9 = new WriterThread(shared);
		Thread thread10 = new WriterThread(shared);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
			thread9.join();
			thread10.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(shared.getSharedInt());
	}

	/**
	 * Пример взят с сайта tproger.ru с целью рассмотрения работы
	 * исполнителя ExecutorService.
	 */
	@Test
	public void test2() {
		SharedObject shared = new SharedObject();
		ExecutorService exec = Executors.newFixedThreadPool(2);
		IntStream.range(0, 10_000)
				.forEach(i -> exec.submit(shared::incSharedInt));
		stop(exec);
		System.out.println("shared int : " + shared.getSharedInt());
	}

	/**
	 * Метод остановки исполнителя потоков.
	 *
	 * @param executor Исполнитель потоков.
	 */
	private static void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("termination interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("killing non-finished tasks");
			}
			executor.shutdownNow();
		}
	}
}