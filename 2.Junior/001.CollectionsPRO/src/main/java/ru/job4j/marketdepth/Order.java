package ru.job4j.marketdepth;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Заявка.
 */
public class Order implements Comparable<Order> {
	/**
	 * Уникальный ключ заявки.
	 */
	private int id;

	/**
	 * Идентификатор ценной бумаги (12 символов).
	 */
	private String isin;

	/**
	 * Тип цены.
	 */
	private PriceType priceType;

	/**
	 * Цена товара/услуги.
	 */
	private BigDecimal price;

	/**
	 * Объём товара/услуг.
	 */
	private int volume;

	/**
	 * Трейдер, оставляющий заявку.
	 */
	private String trader;

	/**
	 * Конструктор заявки.
	 *
	 * @param id        Уникальный ключ заявки.
	 * @param isin      Идентификатор ценной бумаги (12 символов).
	 * @param trader    Держатель заявки.
	 * @param priceType Тип цены.
	 * @param price     Цена товара/услуги.
	 * @param volume    Объём товара/услуг.
	 */
	public Order(int id, String isin, String trader, PriceType priceType,
				 BigDecimal price, int volume) {
		this.id = id;
		this.isin = isin;
		this.priceType = priceType;
		this.price = price;
		this.volume = volume;
		this.trader = trader;
	}

	/**
	 * Получение идентификатора заявки.
	 *
	 * @return Идентификатор заявки.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Получение идентификатора ценной бумаги.
	 *
	 * @return Идентификатор ценной бумаги.
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * Получение типа цены.
	 *
	 * @return Тип цены.
	 */
	public PriceType getPriceType() {
		return priceType;
	}

	/**
	 * Получение цены.
	 *
	 * @return Цена.
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Получение объёма товара/услуг.
	 *
	 * @return Объём товара/услуг.
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * Получение держателя заявки.
	 *
	 * @return Держатель заявки.
	 */
	public String getTrader() {
		return trader;
	}

	/**
	 * Уменьшение объёма товара/услуг.
	 *
	 * @param volume Объём товара/услуг.
	 */
	public void decreaseVolume(int volume) {
		this.volume -= volume;
	}

	@Override
	public int compareTo(Order o) {
		return Integer.compare(id, o.id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Order order = (Order) o;
		return id == order.id
				&& Objects.equals(isin, order.isin)
				&& priceType == order.priceType
				&& Objects.equals(price, order.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isin, priceType, price);
	}
}
