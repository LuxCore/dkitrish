package ru.job4j.array;

/**
 * Rotating of array 90 degrees clockwise.
 *
 * @author Denis.Kitrish
 * @since 07.10.2017
 * @version 1.0
 */
public class RotateArray {

	/**
	 * Rotating of a 2D array.
	 *
	 * @param array Given array.
	 *
	 * @return int[][] Rotated array.
	 */
	public int[][] rotate(int[][] array) {
		if (array == null || array.length > 0 && array[0].length == 0) {
			return null;
		}

		// Обходим массив послойно: сначала самый верхний слой переворачиваем, далее внутрь масства углубляемся.
		// Количество изменяемых слоёв массива узнаём с помощью целочисленного деления на 2.
		for (int i = 0; i < array.length / 2; i++) {
			for (int j = 0 + i; j < array.length - 1 - i; j++) {
				// Берём основным верхний левый угловой элемент представляемого квадрата.
				// И далее меняем данный элемент с тремя другими угловыми элементами по часовой стрелке.
				// Таким образом мы представляем как будто наш квадрат вращается по часовой стрелке и координаты
				// его углов меняются.
				swap(array, i, j, j, array.length - 1 - i);

				swap(array, i, j, array.length - 1 - i, array.length - 1 - j);

				swap(array, i, j, array.length - 1 - j, i);
			}
		}

		return array;
	}

	/**
	 * Initializing an array with integers.
	 *
	 * @param array Array to init.
	 */
	public void initArray(int[][] array) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = ++count;
			}
		}
	}

	/**
	 * Swaps elements of an 2D array without temporary variable.
	 *
	 * @param array Given 2D array.
	 * @param i First index of first element.
	 * @param j Second index of first element.
	 * @param k First index of second element.
	 * @param l Second index of second element.
	 */
	public static void swap(int[][] array, int i, int j, int k, int l) {
		array[i][j] += array[k][l];
		array[k][l] = array[i][j] - array[k][l];
		array[i][j] -= array[k][l];
	}
}