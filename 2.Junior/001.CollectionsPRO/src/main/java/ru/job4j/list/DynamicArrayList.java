package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический список на базе массива.
 *
 * @param <E> Тип элементов динамического массива.
 */
public class DynamicArrayList<E> implements Iterable<E> {
	/**
	 * Начальное значение размера массива container.
	 */
	private static final int INITIAL_CAPACITY = 16;

	/**
	 * Контейнер объектов.
	 */
	private Object[] container;

	/**
	 * Указатель на позицию в контейнере, куда нужно будет добавить объект.
	 */
	private int cursor;

	/**
	 * Количество изменений в контейнере объектов.
	 */
	private int modificationCount;

	/**
	 * Конструктор по умолчанию.
	 * Задаёт начальный размер контейнера объектов равный 16.
	 */
	public DynamicArrayList() {
		this.container = new Object[INITIAL_CAPACITY];
	}

	/**
	 * Конструктор, задающий начальную длину контейнера.
	 *
	 * @param length Длина контейнера.
	 * @throws NegativeArraySizeException Если аргумент длины массива
	 *                                    передаётся отрицательным числом.
	 */
	public DynamicArrayList(int length) throws NegativeArraySizeException {
		if (length < 0) {
			throw new NegativeArraySizeException();
		}
		this.container = new Object[length];
	}

	/**
	 * Добавление элемента в список.
	 *
	 * @param value Добавляемый объект.
	 */
	public void add(E value) {
		ensureCapacity();
		this.container[this.cursor++] = value;
		this.modificationCount++;
	}

	/**
	 * Получение объекта из списка.
	 *
	 * @param index Индекс массива, по которому можно найти искомый объект.
	 * @return Искомый по индексу объект.
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) this.container[index];
	}

	/**
	 * Получение количества объектов в контейнере.
	 *
	 * @return Количество элементов в контейнере.
	 */
	public int elementsCount() {
		return this.cursor;
	}

	/**
	 * Получение размера контейнера.
	 *
	 * @return Размер контейнера.
	 */
	public int size() {
		return this.container.length;
	}

	/**
	 * Убеждаемся в том, что в массив можно добавлять элементы.
	 */
	private void ensureCapacity() {
		if (this.cursor == this.container.length) {
			int newLength = this.container.length * 3 / 2 + 1;
			this.container = Arrays.copyOf(this.container, newLength);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Итератор по контейнеру.
	 */
	private class Itr implements Iterator<E> {
		/**
		 * Индекс итератора.
		 */
		private int index;

		/**
		 * Количество изменений на момент создания итератора.
		 * <p>Неизменяемая переменная.</p>
		 */
		private int modificationCount;

		/**
		 * Конструктор копирует значение количества изменений контейнера.
		 */
		Itr() {
			this.modificationCount = DynamicArrayList.this.modificationCount;
		}

		@Override
		public boolean hasNext() {
			if (this.modificationCount != DynamicArrayList.this.modificationCount) {
				throw new ConcurrentModificationException();
			}
			return this.index < DynamicArrayList.this.container.length
					&& DynamicArrayList.this.container[this.index] != null;
		}

		@Override
		@SuppressWarnings("unchecked")
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return (E) DynamicArrayList.this.container[this.index++];
		}
	}
}
