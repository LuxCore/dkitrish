package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Простое множество на базе связного списка.
 *
 * @param <E> тип элементов, которые могут содержаться в множестве.
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
	/**
	 * Связный список для хранения элементов множества.
	 */
	private SimpleLinkedList<E> list = new SimpleLinkedList<>();

	/**
	 * Флаг, обозначающий наличие null в множестве.
	 * <p>Если равен true, то новый null добавляться не будет.</p>
	 */
	private boolean hasNullElement = false;

	/**
	 * Добавляет новый элемент, если множество не содержит эквивалентный.
	 * <p>Множество может содержать один пустой элемент.</p>
	 *
	 * @param pElement Добавляемый элемент.
	 */
	public void add(E pElement) {
		if (pElement == null) {
			if (!hasNullElement) {
				list.add(null);
				hasNullElement = true;
			}
			return;
		}
		if (!has(pElement)) {
			list.add(pElement);
		}
	}

	/**
	 * Проверка множества на наличие эквивалентного элемента добавляемому.
	 *
	 * @param pElement Добавляемый элемент.
	 * @return true, если элемент уже имеется в множестве; false, если такового
	 * ещё не имеется в множестве.
	 */
	private boolean has(E pElement) {
		boolean result = false;
		for (E e : list) {
			if (pElement.equals(e)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
