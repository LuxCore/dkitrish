package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

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
	 * Поле, которое не учитывается в методах equals() и hashCode().
	 */
	private int nonBusinessField;

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

	public void setNonBusinessField(int nonBusinessField) {
		this.nonBusinessField = nonBusinessField;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return children == user.children &&
				Objects.equals(name, user.name) &&
				Objects.equals(birthday, user.birthday);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		result = prime * result + children;
		result = prime * result + birthday.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", children=" + children +
				", birthday=" + birthday.getTime() +
				", nonBusinessField=" + nonBusinessField +
				'}';
	}
}
