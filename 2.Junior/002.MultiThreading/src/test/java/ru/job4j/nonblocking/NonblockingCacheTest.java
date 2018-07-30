package ru.job4j.nonblocking;

import org.junit.Test;

/**
 * Тестирование неблокирующего кэша.
 */
public class NonblockingCacheTest {
	/**
	 * Тест добавление объекта в неблокирующий кэш.
	 */
	@Test
	public void testAddition() {
		NonblockingCache nbc = new NonblockingCache();
		Runnable r = () -> {
			for (int i = 0; i < 10; i++) {
				nbc.add(new Base(i));
			}
		};
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(r);
		Thread t4 = new Thread(r);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}