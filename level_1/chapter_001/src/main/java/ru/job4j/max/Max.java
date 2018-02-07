package ru.job4j.max;

/**
 *	It finds maximum of two numbers.
 *
 *	@author Denis.Kitrish
 *	@since 24.09.2017
 *	@version 1.0
 */
public class Max {

	/**
	 *	Method seeks maximum value of two given integers.
	 *
	 *	@param first First parameter.
	 *	@param second Second parameter.
	 *
	 *	@return int maximum integer.
	 */
	public int max(int first, int second) {
		return first > second ? first : second;
	}

	/**
	 *	Method seeks maximum value of three given integers.
	 *
	 *	@param first First parameter.
	 *	@param second Second parameter.
	 *	@param third Third parameter.
	 *
	 *	@return int maximum integer.
	 */
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}