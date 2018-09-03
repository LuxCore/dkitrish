package ru.job4j.waitnotify;

/**
 * Демонстрация правильной остановки потребителя, когда поставщик
 * завершил свою работу.
 */
public class ParallelSearchSimple {
	/**
	 * Пример остановки потребителя после окончания работы поставщика.
	 *
	 * @param args Аргументы из командной строки.
	 */
	public static void main(String[] args) {
		SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
		final Thread consumer = new Thread(
				() -> {
					while (true) {
						Integer number = queue.poll();
						System.out.println(number);
						if (Integer.compare(number, Integer.MIN_VALUE) == 0) {
							break;
						}
					}
				}
		);
		consumer.start();
		new Thread(
				() -> {
					for (int index = 0; index < 3; index++) {
						queue.offer(index);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					queue.offer(Integer.MIN_VALUE);
				}

		).start();
	}
}
