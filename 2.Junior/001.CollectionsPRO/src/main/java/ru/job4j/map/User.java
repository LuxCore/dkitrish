package ru.job4j.map;

import java.util.Calendar;

/**
 * Сущность пользователя.
 */
public class User {
	/**
	 * Имя пользователя.
	 */
	private String name;

	/**
	 * Количество детей у пользователя.
	 */
	private int children;

	/**
	 * День рождения пользователя.
	 */
	private Calendar birthday;

	/**
	 * Пустой конструктор.
	 */
	public User() {
	}

	/**
	 * Конструктор с инициализацией всех полей.
	 *
	 * @param pName     Имя пользователя.
	 * @param pChildren Количество детей у пользователя.
	 * @param pBirthday День рождения пользователя.
	 */
	public User(String pName, int pChildren, Calendar pBirthday) {
		name = pName;
		children = pChildren;
		birthday = pBirthday;
	}

	/**
	 * Получает имя пользователя.
	 *
	 * @return Имя пользователя.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Установливает имя пользователя.
	 *
	 * @param name Имя пользователя.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Получает количество детей пользователя.
	 *
	 * @return Количество детей пользователя.
	 */
	public int getChildren() {
		return children;
	}

	/**
	 * Устанавливает количество детей пользователя.
	 *
	 * @param children Количество детей пользователя.
	 */
	public void setChildren(int children) {
		this.children = children;
	}

	/**
	 * Получает день рождения пользователя.
	 *
	 * @return День рождения пользователя.
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * Устанавливает день рождения пользователя.
	 *
	 * @param birthday День рождения пользователя.
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
}
