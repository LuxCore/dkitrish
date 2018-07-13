package ru.job4j.jmm;

/**
 * Рассмотрение проблемы многопоточной гонки.
 */
public class SharedObject {
	/**
	 * Переменная с совместынм доступом.
	 */
	private int sharedInt;

	/**
	 * Логический флаг.
	 */
	private boolean flag;

	/**
	 * Инкрементирование общей переменной.
	 */
	public void incSharedInt() {
		sharedInt++;
	}

	/**
	 * Получения значения общей переменной.
	 *
	 * @return Общую переменную.
	 */
	public int getSharedInt() {
		return sharedInt;
	}

	/**
	 * Изменение флага на противоположный.
	 */
	public void changeFlag() {
		flag = !flag;
	}

	/**
	 * Чтение флага.
	 *
	 * @return текущее значение флага.
	 */
	public boolean isFlagSet() {
		return flag;
	}
}
