package ru.job4j.list;

/**
 * Простая релизация очереди.
 *
 * @param <T> type of elements.
 */
public class SimpleQueue<T> {
	/**
	 * Связный список.
	 */
	private SimpleLinkedList<T> list = new SimpleLinkedList<>();
	/**
	 * Возвращает первое значение в очереди и удаляет его.
	 *
	 * @return Значение для удаления.
	 */
	public T poll() {
		return list.removeFirst();
	}

	/**
	 * Вставка значения в очередь.
	 *
	 * @param value Значение, которое необходимо поместить в очередь.
	 */
	public void push(T value) {
		list.add(value);
	}
}
