package ru.job4j.loop;

/**
 *	Counts factrial.
 *
 *	@author Denis.Kitrish
 *	@since 01.10.2017
 *	@version 1.0
 */
public class Factorial {

	/**
	 *	Counts factorial with for loop.
	 *
	 *	@param num Counts factorial of this number.
	 *
	 *	@return int Factorial.
	 */
	public int calc(int num) {
		if (num == 0) {
			return 1;
		}

		int tmp = 1;

		for (int i = 1; i <= num; i++) {
			tmp *= i;
		}

		return tmp;
	}
}