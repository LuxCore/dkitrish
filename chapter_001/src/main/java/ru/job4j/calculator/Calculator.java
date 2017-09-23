package ru.job4j.calculator;

/**
 *	Performs operations with double numbers.
 *
 *	@author Denis.Kitrish
 *	@since 23.09.2017
 *	@version 1.0
 */
public class Calculator {

	/**
	 *	Result of performing operations with numbers.
	 */
	private double result;

	/**
	 *	Sum operation with two doubles.
	 *
	 *	@param first First number.
	 *	@param second Second number.
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 *	Difference of two doubles.
	 *
	 *	@param first First number.
	 *	@param second Second number.
	 */
	public void subtract(double first, double second) {
		this.result = first - second;
	}

	/**
	 *	Quotient of two doubles.
	 *
	 *	@param first First number.
	 *	@param second Second number.
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	 *	Product of two doubles.
	 *
	 *	@param first First number.
	 *	@param second Second number.
	 */
	public void multiply(double first, double second) {
		this.result = first * second;
	}

	/**
	 *	Getter of result.
	 *
	 *	@return result of operations.
	 */
	public double getResult() {
		return result;
	}
}