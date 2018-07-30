package ru.job4j.monitorsync;

import java.util.Objects;

/**
 * Пользователь.
 */
public class User {
	/**
	 * Идентификатор пользователя.
	 */
	private int id;

	/**
	 * Сумма на счету пользователя.
	 */
	private int amount;

	/**
	 * Конструктор по умолчанию.
	 */
	public User() {
	}

	/**
	 * Конструктор, принимающий id и начальную сумму.
	 *
	 * @param id     Идентификатор пользователя.
	 * @param amount Начальная сумма на счету пользователя.
	 */
	public User(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	/**
	 * Получение идентификатора пользователя.
	 *
	 * @return идентификатор пользователя.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Получение суммы со счёта пользователя.
	 *
	 * @return сумму со счёта пользоватедя.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Установка новой суммы на счёте.
	 *
	 * @param amount Новая сумма на счёте.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
		return id == user.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "User{"
				+ "id=" + id
				+ ", amount=" + amount
				+ '}';
	}
}
