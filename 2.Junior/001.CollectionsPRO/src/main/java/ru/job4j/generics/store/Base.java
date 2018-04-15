package ru.job4j.generics.store;

import java.util.Objects;

/**
 * Базовый класс для сущностей, содержащих идентификатор.
 */
public abstract class Base {
	/**
	 * Идентификатор сущности.
	 */
	private final String id;

	/**
	 * Конструктор, принимающий идентификаор создаваемой сущности.
	 * @param id Идентификатор сущности.
	 */
	protected Base(final String id) {
		this.id = id;
	}

	/**
	 * Получение идентификатора.
	 * @return Идентификатор сущности.
	 */
	public String getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Base base = (Base) o;
		return Objects.equals(id, base.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
