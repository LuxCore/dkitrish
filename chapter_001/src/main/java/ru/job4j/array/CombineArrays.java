package ru.job4j.array;

import java.util.Arrays;

/**
 * Combining of two sorted arrays.
 *
 * @author Denis.Kitrish
 * @since 22.10.2017
 * @version 1.0
 */
public class CombineArrays {
	/**
	 * Combines two sorted arrays using API.
	 *
	 * @param array1 String where needed to find a substring.
	 * @param array2 Search string.
	 *
	 * @return byte[] If string contains sub then true.
	 */
	public byte[] combine(byte[] array1, byte[] array2) {
		byte[] result = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, result, 0, array1.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);

		Arrays.sort(result);

		return result;
	}
}