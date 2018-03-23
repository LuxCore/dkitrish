package ru.job4j.coffeemachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

/**
 * Tests giving of change from bying the coffee.
 */
public class CoffeeMachineTest {
	/**
	 * Coffee machine.
	 */
	private CoffeeMachine automat;

	/**
	 * Inits data for every test.
	 */
	@Before
	public void init() {
		this.automat = new CoffeeMachine();
	}

	/**
	 * Tetst giving of change for coffee.
	 */
	@Test
	public void testGiveChange() {
		this.automat.acceptPayment(30);
		this.automat.selectCoffee(Coffee.AMERICANO);
		int[] result = this.automat.giveChange();
		int[] expected = new int[] {2, 2};
		assertThat(result, is(expected));

		this.automat.acceptPayment(56);
		this.automat.selectCoffee(Coffee.ESPRESSO);
		result = this.automat.giveChange();
		expected = new int[] {10, 10, 1};
		assertThat(result, is(expected));

		this.automat.acceptPayment(96);
		this.automat.selectCoffee(Coffee.IRISH_WHISKУY);
		result = this.automat.giveChange();
		expected = new int[] {10, 10, 10, 10, 5, 2, 2};
		assertThat(result, is(expected));
	}
}
