package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по массиву с нахождением простых чисел.
 */
public class PrimeIterator implements Iterator<Integer> {
	/**
	 * Массив целых чисел.
	 */
	private int[] numbers;

	/**
	 * Позиция элемента, следующего за последним найденным простым числом.
	 */
	private int position;

	/**
	 * Конструктор, принимающий массив целых чисел.
	 *
	 * @param numbers Массив целых чисел.
	 */
	PrimeIterator(final int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public boolean hasNext() {
		return getIndexOfNextPrime() > -1;
	}

	@Override
	public Integer next() {
		int indexOfPrime = getIndexOfNextPrime();
		int result;
		if (indexOfPrime == -1) {
			throw new NoSuchElementException("Простое число не найдено.");
		}
		result = this.numbers[indexOfPrime];
		this.position = ++indexOfPrime;
		return result;
	}

	/**
	 * Поиск индекса в массиве, под которым находится простое число.
	 *
	 * @return Индекс в массиве, под которым находится простое число.
	 */
	private int getIndexOfNextPrime() {
		int result = -1;
		for (int i = this.position; i < this.numbers.length; i++) {
			if (isPrimeNumber(this.numbers[i])) {
				result = i;
				break;
			}
		}
		return result;
	}

	/**
	 * Проверка числа на простоту.
	 *
	 * @param number Число для проверки на простоту.
	 * @return true, если число простое, false, если число не простое.
	 */
	private boolean isPrimeNumber(int number) {
		boolean result = true;
		if (number == 1 || number > 10 && number % 10 == 5) {
			result = false;
		} else {
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
