package ru.job4j.waitnotify;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Простейший бассейн нитей.
 */
public class ThreadPool {
	/**
	 * Флаг приостановки работы потока.
	 */
	private boolean suspendFlag;
	/**
	 * Ограничение количества нитей.
	 */
	private static final int THREAD_LIMIT = Runtime.getRuntime().availableProcessors();
	/**
	 * Список нитей.
	 */
	private final List<Thread> threads = new LinkedList<>();

	/**
	 * Очередь заданий на выполнение.
	 */
	private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

	/**
	 * Пустой конструктор.
	 */
	public ThreadPool() {
	}

	/**
	 * Метод денчика потом удалить.
	 */
	public void start() {
		for (int i = 0; i < THREAD_LIMIT; i++) {
			threads.add(new Thread(() -> {
				while (!this.suspendFlag) {
					try {
						this.tasks.take().run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}));
		}
		for (Thread t : this.threads) {
			t.start();
		}
	}

	/**
	 * Завершить выполнение всех потоков, дождавшись окончания выполнения
	 * каждого.
	 */
	public void shutdown() {
		this.suspendFlag = true;
		for (Thread t : this.threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Выполнение работы.
	 *
	 * @param job Задание, которое должно быть выполнено.
	 */
	public void work(Runnable job) {
		try {
			tasks.put(job);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
