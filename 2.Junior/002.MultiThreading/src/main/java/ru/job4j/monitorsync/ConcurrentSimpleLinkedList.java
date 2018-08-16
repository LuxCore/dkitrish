package ru.job4j.monitorsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Связный список, синхронизированный для многопоточных приложений.
 *
 * @param <E> Тип элементов, содержащихся в связном списке.
 */
@ThreadSafe
public class ConcurrentSimpleLinkedList<E> implements Iterable {
	/**
	 * Несинхронизированный связный список.
	 */
	@GuardedBy("this")
	private SimpleLinkedList<E> list;

	/**
	 * Конструктор умолчанию.
	 */
	public ConcurrentSimpleLinkedList() {
		this.list = new SimpleLinkedList<>();
	}

	/**
	 * Добавление нового значения в конец списока.
	 *
	 * @param value Новый объект, добавляемый в список.
	 */
	public synchronized void add(E value) {
		this.list.add(value);
	}

	/**
	 * Получение элементов из списка по индексу.
	 *
	 * @param index Индекс искомого элемента.
	 * @return Необходимый элемент списка.
	 */
	public E get(int index) {
		return this.list.get(index);
	}

	/**
	 * Получение длины/количества элементов связного списка.
	 *
	 * @return Количество элементов связного списка.
	 */
	public int size() {
		return this.list.size();
	}

	/**
	 * Удаление первого узла списка.
	 *
	 * @return Значение удаляемого узла.
	 */
	public synchronized E removeFirst() {
		return this.list.removeFirst();
	}

	/**
	 * Удаление последнего узла списка.
	 *
	 * @return Значение удаляемого узла.
	 */
	public synchronized E removeLast() {
		return this.list.removeLast();
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}
}
