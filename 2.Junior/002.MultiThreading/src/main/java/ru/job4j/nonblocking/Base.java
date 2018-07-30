package ru.job4j.nonblocking;

/**
 * Обычный объект, имеющий специальное версионное поле {@code version}.
 * Это поле служит для потоков указателем на то, что объект был изменён.
 */
public class Base {
	/**
	 * Идентификатор объекта.
	 */
	private int id;

	/**
	 * Версия изменений.
	 */
	private int version;

	/**
	 * Конструктор.
	 *
	 * @param id Идентификатор объекта.
	 */
	public Base(int id) {
		this.id = id;
	}

	/**
	 * Получение идентификатора объекта.
	 *
	 * @return идентификатор объекта.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Получение версии изменений.
	 *
	 * @return версия изменений.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Установка следующей версии объекта после того, как объект
	 * был изменён.
	 *
	 * @param version Новая версия объекта.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
