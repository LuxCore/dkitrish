package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Простой список на базе массива.
 *
 * @param <T> Тип элементов.
 */
public class SimpleArrayList<T> implements Iterable<T> {
	/**
	 * Массив/контейнер элементов.
	 */
	private T[] elements;

	/**
	 * Позиция элемента, к которому будет следующее обращение.
	 */
	private int index;

	/**
	 * Конструктор на основе класса, который выступает в качестве будущего типа
	 * элементов.
	 * <p>По умолчанию длина массива равна 16.</p>
	 *
	 * @param clazz Будущий тип элементов.
	 */
	@SuppressWarnings("unchecked")
	public SimpleArrayList(Class<T> clazz) {
		this.elements = (T[]) Array.newInstance(clazz, 16);
	}

	/**
	 * Конструктор на основе класса, который выступает в качестве будущего типа
	 * элементов.
	 * <p>Длина списка задаётся входным параметром length.</p>
	 *
	 * @param clazz  Будущий тип элементов.
	 * @param length Начальное количество элементов, которые могут содержатся
	 *               во внутреннем массиве.
	 * @throws NegativeArraySizeException Выбрасывается в случае отрицательной длины.
	 */
	@SuppressWarnings("unchecked")
	public SimpleArrayList(Class<T> clazz, int length) throws NegativeArraySizeException {
		this.elements = (T[]) Array.newInstance(clazz, length);
	}

	/**
	 * Конструктор списка на основе уже имеющегося массива элементов.
	 *
	 * @param elements Массив объектов.
	 */
	public SimpleArrayList(T[] elements) {
		this.elements = elements;
		this.index = this.elements.length;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			/**
			 * Индекс следующего элемента массива, использующийся итератором.
			 */
			private int index;

			@Override
			public boolean hasNext() {
				return SimpleArrayList.this.elements[this.index] == null
						|| this.index < SimpleArrayList.this.elements.length - 1;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException("Элемент не найден.");
				}
				return SimpleArrayList.this.elements[this.index++];
			}
		};
	}

	/**
	 * Возвращает размер списка.
	 *
	 * @return Размер списка.
	 */
	public int size() {
		return this.elements.length;
	}

	/**
	 * Возвращает количество имеющихся элементов в списке.
	 *
	 * @return Количество имеющихся элементов в списке.
	 */
	public int elementsCount() {
		return this.index;
	}

	/**
	 * Добавление элементов в список.
	 * <p>Перед добавлением массив проверяется на возможность добавления
	 * элементов. Если такой возможности уже нет, то массив раширяется. После
	 * этого добавляется элемент.</p>
	 *
	 * @param value Объект, который необходимо добавить в список.
	 */
	public void add(T value) {
		ensureCapacity();
		this.elements[index++] = value;
	}

	/**
	 * Удаление элемента массива по индексу.
	 * <p>Удаление происходит за счёт смещения оставшейся части элементов справа
	 * на 1 позицию влево.</p>
	 * @param index Индекс ячейки массива, из которой нужно удалить элемент.
	 */
	public void delete(int index) {
		ensureIndex(index);
		if (this.elements[index] != null) {
			System.arraycopy(this.elements, index + 1, this.elements, index, this.index - index);
			this.index--;
		}
	}

	/**
	 * Запись нового элемента в указанную позицию.
	 *
	 * @param index Индекс ячейки массива, в которую необходимо записать объект.
	 * @param model Значение, которое необходимо записать в указанную позицию.
	 */
	public void set(int index, T model) {
		ensureIndex(index);
		this.elements[index] = model;
	}

	/**
	 * Получение объекта по его позиции в списке.
	 *
	 * @param index Позиция объекта в списке.
	 * @return Значение, находящееся в ячейке массива с искомым индексом.
	 */
	public T get(int index) {
		ensureIndex(index);
		return this.elements[index];
	}

	/**
	 * Убеждаемся в том, что в массив можно добавлять элементы.
	 */
	private void ensureCapacity() {
		if (index == this.elements.length) {
			int newLength = this.elements.length * 3 / 2 + 1;
			this.elements = Arrays.copyOf(this.elements, newLength);
		}
	}

	/**
	 * Проверка того, что индекс находится в допустимых пределах длины массива.
	 *
	 * @param index Индекс ячейки массива.
	 */
	private void ensureIndex(int index) {
		if (index < 0 || index >= this.elements.length) {
			throw new IndexOutOfBoundsException("Индекс находится за пределами массива.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SimpleArrayList<?> that = (SimpleArrayList<?>) o;
		int minLen = Math.min(this.elements.length, that.elements.length);
		for (int i = 0; i < minLen; i++) {
			if (!Objects.equals(this.elements[i], (that.elements[i]))) {
				return false;
			}
		}
		if (this.elements.length > that.elements.length) {
			return this.elements[minLen] == null;
		} else {
			return that.elements[minLen] == null;
		}
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(this.elements);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < this.index; i++) {
			sb.append(this.elements[i]);
			if (i < this.index - 1) {
				sb.append(", ");
			}
		}
		return sb.append("]").toString();
	}
}
