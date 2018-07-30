package ru.job4j.pool;

/**
 * Подписчик на рассылку.
 */
public class User {
	/**
	 * Имя пользователя.
	 */
	private String userName;

	/**
	 * Адрес почты пользователя.
	 */
	private String email;

	/**
	 * Конструирует пользователя.
	 *
	 * @param userName Имя пользователя.
	 * @param email    Адрес электропочты пользователя.
	 */
	public User(String userName, String email) {
		this.userName = userName;
		this.email = email;
	}

	/**
	 * Получение имени подписчика.
	 *
	 * @return Имя подписчика.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Получение адреса электропочты подписчика.
	 *
	 * @return Адрес электропочты подписчика.
	 */
	public String getEmail() {
		return email;
	}
}
