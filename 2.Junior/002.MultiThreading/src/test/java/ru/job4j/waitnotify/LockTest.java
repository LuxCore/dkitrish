package ru.job4j.waitnotify;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование блокировки участка кода с помощью шаблона Lock.
 */
public class LockTest {
	/**
	 * Объект для проверки блока.
	 */
	private static class SomeClass {
		/**
		 * Счётчик, который инкрементируют потоки.
		 */
		private int count;

		/**
		 * Блокировка.
		 */
		private Lock lock = new Lock();

		/**
		 * Метод, использующий Lock объекта, из которого вызывается.
		 */
		void inc1() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				lock.lock();
				for (int i = 0; i < 100; i++) {
					count++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		/**
		 * Незащищённый метод блокировкой.
		 */
		void inc2() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 100; i++) {
				count++;
			}
		}

		/**
		 * Получение итогового значения счётчика.
		 *
		 * @return Итоговое значение счётчика.
		 */
		int getCount() {
			return count;
		}
	}

	/**
	 * Проверка блокировки Lock, когда она присутствует в методе объекта.
	 *
	 * @throws InterruptedException выбрасывается при прерывании
	 *                              работы потока исполнения.
	 */
	@Test
	public void testLockWhenItInsideObject() throws InterruptedException {
		SomeClass clazz = new SomeClass();
		for (int i = 0; i < 50; i++) {
			Thread t = new Thread(clazz::inc1);
			t.start();
			t.join();
		}
		assertEquals(5000, clazz.getCount());
	}

	/**
	 * Проверка блокировки Lock, когда её нет в нужном классе, но нам
	 * необходимо блокировать выполнение кода другими потоками
	 * исполнения.
	 *
	 * @throws InterruptedException выбрасывается при прерывании
	 *                              * работы потока исполнения.
	 */
	@Test
	public void testLockWhenItOutsideObject() throws InterruptedException {
		SomeClass clazz = new SomeClass();
		Lock lock = new Lock();
		for (int i = 0; i < 50; i++) {
			Thread t = new Thread(() -> {
				try {
					lock.lock();
					clazz.inc2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			});
			t.start();
			t.join();
		}
		assertEquals(5000, clazz.getCount());
	}
}
