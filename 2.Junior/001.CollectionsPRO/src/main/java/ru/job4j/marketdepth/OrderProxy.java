package ru.job4j.marketdepth;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Посредник, с обобщенными данными для отображения биржевого стакана.
 */
public class OrderProxy implements Comparable<OrderProxy> {
	/**
	 * Идентификатор ценной бумаги.
	 */
	private String isin;

	/**
	 * Тип цены.
	 */
	private PriceType priceType;

	/**
	 * Цена.
	 */
	private BigDecimal price;

	/**
	 * Объём/количество товара.
	 */
	private int volume;

	/**
	 * Конструктор посредника, содержащего обобщённые данные для однотипных
	 * зяавок.
	 *
	 * @param isin      Идентификатор ценной бумаги.
	 * @param priceType Тип цены.
	 * @param price     Цена.
	 */
	public OrderProxy(String isin, PriceType priceType, BigDecimal price) {
		this.isin = isin;
		this.priceType = priceType;
		this.price = price;
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
	 * @return объём товара/услуг.
	 */
	public int getVolume() {
		return volume;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OrderProxy that = (OrderProxy) o;
		return Objects.equals(isin, that.isin)
				&& priceType == that.priceType
				&& Objects.equals(price, that.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isin, priceType, price);
	}

	@Override
	public int compareTo(OrderProxy o) {
		int result = isin.compareTo(o.isin);
		if (result == 0) {
			result = priceType.compareTo(o.priceType);
		}
		if (result == 0) {
			result = o.price.compareTo(price);
		}
		return result;
	}

	/**
	 * Увеличить объём товара/услуг.
	 *
	 * @param volume Объём товара/услуг.
	 */
	public void addVolume(int volume) {
		this.volume += volume;
	}

	/**
	 * Уменьшить объём товара/услуг.
	 *
	 * @param volume Объём товара/услуг.
	 */
	public void decreaseVolume(int volume) {
		this.volume -= volume;
	}
}
