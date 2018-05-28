package ru.job4j.set;

import java.util.Iterator;

/**
 * Простое хэш-множество.
 *
 * @param <E> Тип объектов, которые могут находится в множестве.
 */
public class SimpleHashSet<E> implements Iterable<E> {
	/**
	 * Хэш-таблица в качестве хранилища данных.
	 */
	private SimpleHashTable<E> hashTable = new SimpleHashTable<>();

	/**
	 * Добавляет элемент.
	 *
	 * @param pElement Добавляемый объект.
	 * @return true если добавление объекта прошло удачно, в противном случае
	 * false.
	 */
	public boolean add(E pElement) {
		if (hashTable.find(pElement) == null) {
			hashTable.insert(pElement);
			return true;
		}
		return false;
	}

	/**
	 * Проверяет содержится ли нужный объект в множестве.
	 *
	 * @param pElement Проверяемый объект.
	 * @return true если объект содержится в множестве, в противном случае
	 * false.
	 */
	public boolean contains(E pElement) {
		return hashTable.find(pElement) != null;
	}

	/**
	 * Удаляет объект.
	 *
	 * @param pElement Удаляемый объект.
	 * @return true если объект удалён успешно из множества, в противном случае
	 * false.
	 */
	public boolean remove(E pElement) {
		return hashTable.delete(pElement) != null;
	}

	/**
	 * Получение размера хранилища.
	 *
	 * @return Размер хранилища.
	 */
	public int getElementsCount() {
		return hashTable.getElementsCount();
	}

	@Override
	public Iterator<E> iterator() {
		return hashTable.iterator();
	}

	@Override
	public String toString() {
		return hashTable.toString();
	}
}
