package ru.job4j.lambda;

import java.util.Objects;

/**
 * Адрес.
 */
public class Address {
	/**
	 * Город.
	 */
	private String city;
	/**
	 * Улица.
	 */
	private String street;
	/**
	 * Номер дома.
	 */
	private int home;
	/**
	 * Номер квартиры.
	 */
	private int apartment;

	/**
	 * Конструктор адреса.
	 * @param city Город
	 * @param street Улица
	 * @param home Номер дома
	 * @param apartment Номер квартиры
	 */
	public Address(String city, String street, int home, int apartment) {
		this.city = city;
		this.street = street;
		this.home = home;
		this.apartment = apartment;
	}

	/**
	 * Извлечение города из адреса.
	 * @return наименование города
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Обновление имени города.
	 * @param city город
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Получение названия улицы.
	 * @return название улицы.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Установка нового наименования улицы.
	 * @param street наименование улицы.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Получение номера дома.
	 * @return номер дома.
	 */
	public int getHome() {
		return home;
	}

	/**
	 * Установка нового номера дома.
	 * @param home номер дома.
	 */
	public void setHome(int home) {
		this.home = home;
	}

	/**
	 * Получение номера квартиры.
	 * @return номер квартиры.
	 */
	public int getApartment() {
		return apartment;
	}

	/**
	 * Установка нового номера квартиры.
	 * @param apartment номер квартиты.
	 */
	public void setApartment(int apartment) {
		this.apartment = apartment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return home == address.home
				&& apartment == address.apartment
				&& city.equals(address.city)
				&& street.equals(address.street);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, home, apartment);
	}
}
