package ru.job4j.list;

/**
 * Узел, с помощью которого строится независимый свзяный список.
 *
 * @param <T> тип элементов.
 */
public class Node<T> {
	/**
	 * Значение, которое содержит один узел списка.
	 */
	private T value;

	/**
	 * Ссылка на следующий узел списка.
	 */
	private Node<T> next;

	/**
	 * Конструктор узла.
	 *
	 * @param pValue Значение узла.
	 */
	public Node(T pValue) {
		value = pValue;
	}

	/**
	 * Возвращает следующий узел списка.
	 *
	 * @return Следующий узел списка.
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Устанавливает следующий узел списка.
	 *
	 * @param next Следующий узел списка.
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
