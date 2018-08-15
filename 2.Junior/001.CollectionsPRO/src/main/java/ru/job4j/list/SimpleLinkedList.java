package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Упрощённый связный список.
 *
 * @param <T> Тип элементов, содержащихся в связном списке.
 */
public class SimpleLinkedList<T> implements Iterable<T> {
	/**
	 * Содержит количество изменений в списке.
	 */
	private int modCount;

	/**
	 * Размер списка или количество элементов списка.
	 */
	private int size;

	/**
	 * Ссылка на первый элемент списка.
	 */
	private Node<T> first;

	/**
	 * Ссылка на последний элемент списка.
	 */
	private Node<T> last;

	/**
	 * Добавление нового значения в конец списока.
	 *
	 * @param value Новый объект, добавляемый в список.
	 */
	public void add(T value) {
		Node<T> node = new Node<>(value);

		if (size > 0) {
			last.next = node;
			node.prev = last;
		} else {
			first = node;
		}
		last = node;
		this.size++;
		this.modCount++;
	}

	/**
	 * Получение элементов из списка по индексу.
	 *
	 * @param index Индекс искомого элемента.
	 * @return Необходимый элемент списка.
	 */
	public T get(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> node;
		if (index <= Math.ceil(size / 2)) {
			node = this.first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		} else {
			node = this.last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
		}
		return node.value;
	}

	/**
	 * Получение длины/количества элементов связного списка.
	 *
	 * @return Количество элементов связного списка.
	 */
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

	@Override
	public String toString() {
		Iterator<T> it = iterator();
		if (!it.hasNext()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(',').append(' ');
			}
		}
		return sb.append(']').toString();
	}

	/**
	 * Элемент связного списка.
	 *
	 * @param <E> Тип элемента связного списка.
	 */
	private class Node<E extends T> {
		/**
		 * Предыдущий элемент по отношению к текущему.
		 */
		private Node<E> prev;

		/**
		 * Следующий элемент по отношению к текущему.
		 */
		private Node<E> next;

		/**
		 * Значение текущего элемента.
		 */
		private E value;

		/**
		 * Конструктор узла связного списка.
		 *
		 * @param value Значение узла.
		 */
		Node(E value) {
			this.value = value;
		}
	}

	/**
	 * Итератор по связному списку.
	 */
	private class Itr implements Iterator<T> {
		/**
		 * Количество изменений в списке перед созданием итератора.
		 */
		private int expectedModCount = modCount;

		/**
		 * Курсор итератора.
		 */
		private Node<T> cursor = first;

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public T next() {
			if (this.expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T result = cursor.value;
			cursor = cursor.next;
			return result;
		}
	}

	/**
	 * Удаление первого узла списка.
	 *
	 * @return Значение удаляемого узла.
	 */
	public T removeFirst() {
		T result = null;
		if (size > 2) {
			result = first.value;
			first = first.next;
			first.prev = null;
			size--;
		} else if (size == 2) {
			result = first.value;
			first = first.next;
			last = first;
			size--;
		} else if (size == 1) {
			result = first.value;
			first = null;
			last = null;
			size--;
		}
		return result;
	}

	/**
	 * Удаление последнего узла списка.
	 *
	 * @return Значение удаляемого узла.
	 */
	public T removeLast() {
		T result = null;
		if (size > 2) {
			result = last.value;
			last = last.prev;
			last.next = null;
			size--;
		} else if (size == 2) {
			result = last.value;
			last = last.prev;
			first = last;
			size--;
		} else if (size == 1) {
			result = last.value;
			first = null;
			last = null;
			size--;
		}
		return result;
	}
}
