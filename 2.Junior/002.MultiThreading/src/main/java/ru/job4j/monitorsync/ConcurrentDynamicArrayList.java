package ru.job4j.monitorsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;

/**
 *  Список на базе массива, синхронизированный для многопоточного приложения.
 *
 *  @param <E> Тип элементов динамического массива.
 */
@ThreadSafe
public class ConcurrentDynamicArrayList<E> implements Iterable<E> {
	/**
	 * Список на базе массива.
	 */
	@GuardedBy("this")
	private DynamicArrayList<E> list;

	/**
	 * Конструктор., создающий список из 16 элементов.
	 */
	public ConcurrentDynamicArrayList() {
		this.list = new DynamicArrayList<>();
	}

	/**
	 * Конструктор, задающий начальную длину списка.
	 * @param length Начальная длина списка.
	 */
	public ConcurrentDynamicArrayList(int length) {
		this.list = new DynamicArrayList<>(length);
	}

	/**
	 * Добавление элемента в список.
	 *
	 * @param value Добавляемый объект.
	 */
	public synchronized void add(E value) {
		this.list.add(value);
	}

	/**
	 * Получение объекта из списка.
	 *
	 * @param index Индекс массива, по которому можно найти искомый объект.
	 * @return Искомый по индексу объект.
	 */
	public E get(int index) {
		return this.list.get(index);
	}

	/**
	 * Получение количества объектов в контейнере.
	 *
	 * @return Количество элементов в контейнере.
	 */
	public int elementsCount() {
		return this.list.elementsCount();
	}

	/**
	 * Получение размера контейнера.
	 *
	 * @return Размер контейнера.
	 */
	public int size() {
		return this.list.size();
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}
}
