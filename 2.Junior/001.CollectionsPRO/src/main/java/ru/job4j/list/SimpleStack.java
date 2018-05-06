package ru.job4j.list;

/**
 * Простая реализация стопки.
 *
 * @param <T> type of elements.
 */
public class SimpleStack<T> {
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
		return list.removeLast();
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
