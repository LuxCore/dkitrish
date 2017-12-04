package ru.job4j.coffeemachine;

/**
 * Coffee machine that gives change.
 */
public class CoffeeMachine {
	/**
	 * Selected coffee.
	 */
	private Coffee coffee;

	/**
	 * Available banknotes for change.
	 */
	private int[] banknotes;

	/**
	 * Client`s payment.
	 */
	private int payment;

	/**
	 * Constructs coffee machine.
	 */
	public CoffeeMachine() {
		this.initChange();
	}

	/**
	 * Sets available banknotes for change.
	 */
	private void initChange() {
		this.banknotes = new int[] {10, 5, 2, 1};
	}

	/**
	 * Coffee machine accepts payment from client.
	 *
	 * @param payment Payment from client.
	 */
	public void acceptPayment(int payment) {
		this.payment = payment;
	}

	/**
	 * Client selects a coffee.
	 *
	 * @param coffee Selected coffee.
	 */
	public void selectCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	/**
	 * Coffee machine gives change.
	 *
	 * @return Change with available banknotes.
	 */
	public int[] giveChange() {
		int diff = this.payment - coffee.getPrice();
		int[] change = null;

		if (0 < diff) {
			change = new int[numOfArrayElements(diff, banknotes, 0)];
			fillChange(change, diff, banknotes, 0, 0);
		}

		return change;
	}

	/**
	 * Counts number of minimum number of banknotes.
	 *
	 * @param price Price of a coffee.
	 * @param banknotes Available banknotes in automat.
	 * @param banknoteIdx Auxilary variable for array with available banknotes.
	 *
	 * @return Minimum number of banknotes.
	 */
	private int numOfArrayElements(int price, final int[] banknotes, int banknoteIdx) {
		int num = 0;

		num += price / banknotes[banknoteIdx];
		price = price % banknotes[banknoteIdx];
		if (banknotes.length - 1 >= ++banknoteIdx && price > 0) {
			num += numOfArrayElements(price, banknotes, banknoteIdx);
		}

		return num;
	}

	/**
	 * Counting out change with available banknotes.
	 *
	 * @param change Array containing banknotes of change.
	 * @param changeVal Whole value of change that is decreased at each iteration.
	 * @param banknotes Array with available banknotes.
	 * @param banknoteIdx Current index of array banknotes.
	 * @param from Auxilary variable that helps to save next initial position for filling change array.
	 */
	private void fillChange(int[] change, int changeVal, final int[] banknotes, int banknoteIdx, int from) {
		int to = from + changeVal / banknotes[banknoteIdx];

		for (int i = from; i < to; i++, from = to) {
			change[i] = banknotes[banknoteIdx];
		}
		changeVal %= banknotes[banknoteIdx];

		if (banknotes.length - 1 >= ++banknoteIdx && changeVal > 0) {
			fillChange(change, changeVal, banknotes, banknoteIdx, from);
		}
	}
}
