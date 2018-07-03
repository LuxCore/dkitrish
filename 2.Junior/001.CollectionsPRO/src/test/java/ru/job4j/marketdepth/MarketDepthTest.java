package ru.job4j.marketdepth;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование биржевого стакана.
 */
public class MarketDepthTest {
	/**
	 * Экземпляр главного класса приложения биржевой стакан.
	 */
	private MarketDepth marketDepth;

	/**
	 * Используемые локали для валюты.
	 */
	private Locale[] locales = {
			new Locale("ru", "RU"),
			new Locale("ru", "UA"),
			Locale.CANADA,
			Locale.GERMANY
	};

	/**
	 * Валюта.
	 */
	private Currency currency;

	/**
	 * Системный перенос строки.
	 */
	private String nl = System.getProperty("line.separator");

	/**
	 * Предварительное создание биржевого стакана, заполненного заявками.
	 */
	@Before
	public void setUp() {
		Random rnd = new Random();
		Locale locale = locales[rnd.nextInt(locales.length - 1)];
		currency = Currency.getInstance(locale);
		marketDepth = MarketDepth.getInstance(currency);
	}

	/**
	 * Тест добавления заявки.
	 */
	@Test
	public void testAddOrder() {
		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 1", PriceType.ASK,
						new BigDecimal("24.24"), 1, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 1", PriceType.ASK,
						new BigDecimal("24.24"), 2, Action.ADD)
		);
		String actual = marketDepth.getAsString("ORG1");
		String expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     24.24|      3|";
		assertThat(actual, is(equalTo(expected)));

		assertFalse(
				marketDepth.putOrder("ORG1", "Trader 2", PriceType.BID,
						new BigDecimal("24.24"), 2, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 1", PriceType.ASK,
						new BigDecimal("24.24"), 2, Action.ADD)
		);

		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 1", PriceType.ASK,
						new BigDecimal("25.25"), 3, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 2", PriceType.ASK,
						new BigDecimal("25.25"), 4, Action.ADD)
		);
		actual = marketDepth.getAsString("ORG1");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     25.25|      7|" + nl
				+ "|       |     24.24|      4|";
		assertThat(actual, is(equalTo(expected)));

		assertTrue(
				marketDepth.putOrder("ORG1", "Trader 1", PriceType.BID,
						new BigDecimal("26.26"), 5, Action.ADD)
		);
		actual = marketDepth.getAsString("ORG1");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     25.25|      4|" + nl
				+ "|       |     24.24|      4|" + nl
				+ "|      1|     26.26|       |";
		assertThat(actual, is(equalTo(expected)));

		assertTrue(
				marketDepth.putOrder("ORG2", "Trader 3", PriceType.BID,
						new BigDecimal("26.26"), 6, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("ORG2", "Trader 4", PriceType.ASK,
						new BigDecimal("25.25"), 7, Action.ADD)
		);
		actual = marketDepth.getAsString("ORG2");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     25.25|      1|";
		assertThat(actual, is(equalTo(expected)));
	}

	/**
	 * Тест Удаления заявки.
	 * <p>ISIN Cadillac    CA1275278853.</p>
	 * <p>ISIN Google inc. US38259P5089.</p>
	 */
	@Test
	public void testDeleteOrder() {
		assertTrue(
				marketDepth.putOrder("CA1275278853", "Trader 1", PriceType.ASK,
						new BigDecimal("26.26"), 5, Action.ADD)
		);
		assertFalse(
				marketDepth.putOrder("CA1275278853", "Trader 2", PriceType.BID,
						new BigDecimal("27.27"), 2, Action.ADD)
		);
		String actual = marketDepth.getAsString("CA1275278853");
		String expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     26.26|      3|";
		assertThat(actual, is(equalTo(expected)));

		assertTrue(
				marketDepth.putOrder("CA1275278853", "Trader 1", PriceType.ASK,
						new BigDecimal("26.26"), 2, Action.DELETE)
		);
		actual = marketDepth.getAsString("CA1275278853");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     26.26|      1|";
		assertThat(actual, is(equalTo(expected)));

		assertTrue(
				marketDepth.putOrder("US38259P5089", "Trader 1", PriceType.ASK,
						new BigDecimal("26.26"), 1, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("US38259P5089", "Trader 2", PriceType.BID,
						new BigDecimal("25.25"), 2, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("US38259P5089", "Trader 1", PriceType.BID,
						new BigDecimal("25.25"), 2, Action.ADD)
		);
		assertTrue(
				marketDepth.putOrder("US38259P5089", "Trader 1", PriceType.BID,
						new BigDecimal("30.30"), 2, Action.ADD)
		);
		actual = marketDepth.getAsString("US38259P5089");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     26.26|      1|" + nl
				+ "|      2|     30.30|       |" + nl
				+ "|      4|     25.25|       |";
		assertThat(actual, is(equalTo(expected)));

		assertTrue(
				marketDepth.putOrder("US38259P5089", "Trader 2", PriceType.BID,
						new BigDecimal("25.25"), 2, Action.DELETE)
		);
		assertFalse(
				marketDepth.putOrder("US38259P5089", "Trader 2", PriceType.BID,
						new BigDecimal("30.30"), 2, Action.DELETE)
		);
		actual = marketDepth.getAsString("US38259P5089");
		expected = "|Покупка| Цена," + currency + " |Продажа|" + nl
				+ "----------------------------" + nl
				+ "|       |     26.26|      1|" + nl
				+ "|      2|     30.30|       |" + nl
				+ "|      2|     25.25|       |";
		assertThat(actual, is(equalTo(expected)));
	}
}