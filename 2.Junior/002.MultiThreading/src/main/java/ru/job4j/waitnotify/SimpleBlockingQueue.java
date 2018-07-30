package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Простая блокирующая очередь.
 *
 * @param <T> Тип объектов, которые могут содержаться в очереди.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
	/**
	 * Очередь объектов.
	 */
	@GuardedBy("this")
	private Queue<T> queue = new LinkedList<>();

	/**
	 * Лимит на количество объектов в очереди.
	 */
	private static final byte FILL_LIMIT = 7;

	/**
	 * Флаг, указывающий нужно ли приостанавливать поток исполнения.
	 */
	private boolean suspendFlag;

	/**
	 * Помещение нового объекта в очередь.
	 *
	 * @param value Новый объект, который необходимо поместить в очередь.
	 */
	public synchronized void offer(T value) {
		while (queue.size() >= FILL_LIMIT) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.offer(value);
		suspendFlag = false;
		notify();
	}

	/**
	 * Получение объекта из очереди с последующим его удалением.
	 *
	 * @return Следующий объект.
	 */
	public synchronized T poll() {
		while (queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T result = queue.poll();
		suspendFlag = true;
		notify();
		return result;
	}
}
