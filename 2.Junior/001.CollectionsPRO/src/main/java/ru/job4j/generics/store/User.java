package ru.job4j.generics.store;

/**
 * Пользователь некой системы.
 */
public class User extends Base {
	/**
	 * Конструктор, принимающий идентификаор создаваемой сущности.
	 *
	 * @param id Идентификатор сущности.
	 */
	public User(String id) {
		super(id);
	}
}
