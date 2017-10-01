package ru.job4j.loop;

/**
 *	Serves to count nums in different ways.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class Counter {

	/**
	 *	Count sum of even numbers.
	 *
	 *	@param start Initial number of region.
	 *	@param finish Final number of region.
	 *
	 *	@return int sum of even nums.
	 */
	public int sumEvenNums(int start, int finish) {
		int tmp = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				tmp += i;
			}
		}
		return tmp;
	}
}