package ru.job4j.collectionstatistics;

import java.util.Objects;

/**
 * Информация о сравнении коллекции до изменений и после.
 */
public class Info {
	/**
	 * Количество добавленныйх объектов в коллекцию.
	 */
	private int added;

	/**
	 * Количество обновлённых объектов в коллекции.
	 */
	private int updated;

	/**
	 * Количество удалённых объектов из коллекции.
	 */
	private int deleted;

	/**
	 * Конструктор информации об изменении внутреннего содержимого
	 * коллекции.
	 *
	 * @param added   Количество добавленныйх объектов в коллекцию.
	 * @param updated Количество обновлённых объектов в коллекции.
	 * @param deleted Количество удалённых объектов из коллекции.
	 */
	public Info(int added, int updated, int deleted) {
		this.added = added;
		this.updated = updated;
		this.deleted = deleted;
	}

	/**
	 * Получение количества добавленных элементов.
	 *
	 * @return количество добавленных элементов.
	 */
	public int getAdded() {
		return added;
	}

	/**
	 * Получение количества обновлённых элементов.
	 *
	 * @return количество обновлённых элементов.
	 */
	public int getUpdated() {
		return updated;
	}

	/**
	 * Получение количества удалённых элементов.
	 *
	 * @return количество удалённых элементов.
	 */
	public int getDeleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Info info = (Info) o;
		return added == info.added
				&& updated == info.updated
				&& deleted == info.deleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(added, updated, deleted);
	}

	/*@Override
	public String toString() {
		return "Добавлено: " + added
				+ "\nОбновлено: " + updated
				+ "\nУдалено: " + deleted;
	}*/
}
