package ru.job4j.marketdepth;

/**
 * Тип операции над заявкой: выставить на торги (добавление) или
 * снятие с торгов (удаление).
 */
public enum Action {
	/**
	 * Выставление заявки на торги.
	 */
	ADD("Выставить заявку"),

	/**
	 * Снятие заявки с торогов.
	 */
	DELETE("Снять заявку");

	/**
	 * Описание действия.
	 */
	private String description;

	/**
	 * Конструктор с заданием описания действия.
	 *
	 * @param description Описание действия.
	 */
	Action(String description) {
		this.description = description;
	}

	/**
	 * Получить описание действия.
	 *
	 * @return Описание действия.
	 */
	public String getDescription() {
		return description;
	}
}
