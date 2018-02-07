package ru.job4j.array;

/**
 * Combining of two sorted arrays.
 *
 * @author Denis.Kitrish (Denis.Kitrish@Yandex.ua)
 * @since 07.11.2017
 * @version 2.0
 */
public class CombineArrays {
	/**
	 * Combines two sorted arrays using API.
	 *
	 * @param array1 First sorted array.
	 * @param array2 Second sorted array.
	 *
	 * @return byte[] Result sorted array.
	 */
	public byte[] combine(byte[] array1, byte[] array2) {
		// Result sorted (without sorting) array that will combine two
		// sorted arrays.
		byte[] ra = new byte[array1.length + array2.length];
		// Counters for arrays.
		byte i = 0, j = 0, k = 0;

		do {
			if (i < array1.length && j < array2.length) {
				ra[k++] = array1[i] <= array2[j] ? array1[i++] : array2[j++];
			} else if (i < array1.length && j == array2.length) {
				ra[k++] = array1[i++];
			} else if (i == array1.length && j < array2.length) {
				ra[k++] = array2[j++];
			}
		} while (i + j < array1.length + array2.length);

		return ra;
	}
}
