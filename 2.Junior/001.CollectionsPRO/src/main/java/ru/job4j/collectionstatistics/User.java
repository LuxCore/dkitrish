package ru.job4j.collectionstatistics;

/**
 * Пользователь.
 */
public class User {
	/**
	 * Идентификатор пользователя.
	 */
	private int id;

	/**
	 * Имя пользователя.
	 */
	private String name;

	/**
	 * Конструктор пользователя.
	 * @param id   Идентификатор пользователя.
	 * @param name Имя пользователя.
	 */
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Получение ID пользователя.
	 *
	 * @return ID пользователя.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Получение имени пользователя.
	 *
	 * @return имя пользователя.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Установка имени пользователя.
	 *
	 * @param name Имя пользователя.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
