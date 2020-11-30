package ru.job4j.lambda;

/**
 * Анкета клиента туристической фирмы.
 */
public class Profile {
	/**
	 * Адрес клиента.
	 */
	private Address address;

	public Profile(Address address) {
		this.address = address;
	}

	/**
	 * Получение адреса клиента.
	 * @return адрес клиента
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Установка нового адреса клиента.
	 * @param address адрес клиента
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
