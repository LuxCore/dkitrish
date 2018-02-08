package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Перебор чётных чисел в массиве.
 * ============================================================================
 * Iteration of even integers in array.
 *
 * @author Denis.Kitrish
 * @since 08.02.2018
 */
public class EvenIterator implements Iterable<Integer>, Iterator<Integer> {
	/**
	 * Массив целых чисел.
	 * ========================================================================
	 * Array of integers.
	 */
	private int[] array;

	/**
	 * Индекс элемента массива, с которого методы {@code hasNext()} и
	 * {@code next()} начинают поиски чётного числа.
	 * ========================================================================
	 * Index of an array element which from seeking of next even integer
	 * begins.
	 */
	private int index;

	/**
	 * Пустой конструктор.
	 * ========================================================================
	 * Empty constructor.
	 */
	public EvenIterator() {
	}

	/**
	 * Конструктор, принимающий массив целых чисел.
	 * ========================================================================
	 * Constructor accepts array of integers.
	 *
	 * @param array
	 *            Array of integers.
	 */
	public EvenIterator(int[] array) {
		this.array = array;
	}

	/**
	 * Устанвливает массив целых чисел.
	 * ========================================================================
	 * Sets an array of integers.
	 *
	 * @param array
	 *            Array of integers.
	 */
	public void setArray(int[] array) {
		this.array = array;
	}

	/**
	 * Поиск следующего чётного числа без каких-либо изменений.
	 * ========================================================================
	 * Searching of next even integer without changing of any field.
	 *
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (this.array.length == 0) {
			return false;
		}

		for (int i = this.index; i < this.array.length; i++) {
			if (this.array[i] % 2 == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Возвращает следующее чётное число. Если такого нет, то выбрасывается
	 * исключение.
	 * ========================================================================
	 * Returns the next integer. If it does not found then will be thrown the
	 * {@code NoSuchElementException}.
	 *
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		int value = 0;
		for (; this.index < this.array.length; this.index++) {
			if (this.array[this.index] % 2 == 0) {
				value = this.array[this.index++];
				break;
			}
		}

		return value;
	}

	/**
	 * Возвращает итератор текущего состояния. Это значит, что после вызова
	 * данного метода перебор будет происходить не с начала массива, а с
	 * того места, где последний раз был вызван метод {@code next}.
	 * ========================================================================
	 * Returns iterator of current state. It means that array is sorted out
	 * from last index which be remembered after calling {@code next} method.
	 *
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Integer> iterator() {
		return this;
	}
}
