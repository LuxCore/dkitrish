package ru.job4j.lambda;

/**
 * Личность.
 */
public class Person {
	/**
	 * Имя.
	 */
	private String name;
	/**
	 * Фамилия.
	 */
	private String surname;
	/**
	 * Телефон.
	 */
	private String phone;
	/**
	 * Адрес.
	 */
	private String address;

	/**
	 * Конструктор личности.
	 * @param name Имя.
	 * @param surname Фамилия.
	 * @param phone Телефон.
	 * @param address Адрес.
	 */
	public Person(String name, String surname, String phone, String address) {
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * Получение имени.
	 * @return Имя.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Получение фамилии.
	 * @return Фамилия.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Получение телефона.
	 * @return Телефон.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Получение адреса.
	 * @return Адрес.
	 */
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Person{"
				+ "name='" + name + '\''
				+ ", surname='" + surname + '\''
				+ ", phone='" + phone + '\''
				+ ", address='" + address + '\'' + '}';
	}
}
