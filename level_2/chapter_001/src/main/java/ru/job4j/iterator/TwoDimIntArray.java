package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация перебираемого двумерного массива с типом {@code int}.
 * Массивы второго измерения могут быть различного размера. По сути наш массив
 * может быть как равномерным, так и зубчатым.
 * =============================================================================
 * Implementation of iterable for processing of two dimensional array that has
 * primitive type {@code int}.
 * Arrays of second dimension can be of different size. Whole array can be
 * uniform and jagged.
 *
 * @author Denis.Kitrish
 * @since 07.02.2018
 */
public class TwoDimIntArray implements Iterable<Integer> {
	/**
	 * Двумерный массив, из которого необходимо узнавать информацию о следующем
	 * элементе, а также возвращать следующий элемент.
	 * =========================================================================
	 * 2D int array. User needs to know info about its next element and to
	 * return next element.
	 */
	private int[][] intArray;

	/**
	 * Пустой конструктор. Оставляем возможность создавать без массива.
	 * =========================================================================
	 * Empty default constructor. It exists to create class without array.
	 */
	 public TwoDimIntArray() { }

	/**
	 * Конструктор, принимающий двумерный массив с типом элементов {@code int}.
	 * =========================================================================
	 * Constructor accepts two dimensional array of {@code int} type.
	 *
	 * @param intArray
	 *         Двумерный массив типа {@code int}.
	 *         =================================================================
	 *         Two dimensional array of {@code int} type.
	 */
	public TwoDimIntArray(int[][] intArray) {
		this.intArray = intArray;
	}

	/**
	 * Iterator of 2D array of integers.
	 */
	private class TwoDimIntArrayIterator implements Iterator<Integer> {
		/**
		 * Индекс следующего элемента первого измерения.
		 * =========================================================================
		 * Next index of first dimension's element.
		 */
		private int nextE1lvl;

		/**
		* Индекс следующего элемента второго измерения.
		* ==========================================================================
		* Next index of second dimension's element.
		*/
		private int nextE2lvl;

		/**
		 * Возвращает положительный результат, если в массиве имеется следующий
		 * элемент. В первую очередь проверяется длина массива первого уровня, чтобы
		 * она была не нулевой. Во вторую очередь проверяется индекс элемента
		 * второго уровня, чтобы не выходил за пределы массива второго уровня.
		 *
		 * @return
		 *         true if length of an array is more than zero, index of next
		 *         element of first level is less then length of first level and
		 *         index of next element of second level is less then length of
		 *         second level. Otherwise it returns false.
		 */
		@Override
		public boolean hasNext() {
			return TwoDimIntArray.this.intArray.length > 0
					&& nextE1lvl < TwoDimIntArray.this.intArray.length;
		}

		/**
		 * Если нет следующего элемента, то выбрасывается исключение.
		 *
		 * @return Next available integer.
		 */
		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Integer result = TwoDimIntArray.this.intArray[nextE1lvl][nextE2lvl];

			if (++nextE2lvl == TwoDimIntArray.this.intArray[nextE1lvl].length) {
				nextE1lvl++;
				nextE2lvl = 0;
			}

			return result;
		}
	}

	/**
	 * Устанавливает новый массив для перебора его элементов.
	 * =========================================================================
	 * Sets new array for iteration of his elements.
	 *
	 * @param intArray
	 *         Двумерный массив типа {@code int}.
	 *         =================================================================
	 *         Two dimensional array of {@code int} type.
	 */
	public void setArray(int[][] intArray) {
		this.intArray = intArray;
	}

	/**
	 * Возвращает итератор целочисленного двумерного массива.
	 * =========================================================================
	 * Returns iterator of integer 2D array.
	 *
	 * @return Iterator of integer 2D array.
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new TwoDimIntArrayIterator();
	}
}
