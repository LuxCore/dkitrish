package ru.job4j.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Простая хэш-таблица.
 *
 * @param <E> Тип объектов, которые могут хранится в хэш-таблице.
 */
public class SimpleHashTable<E> implements Iterable<E> {
	/**
	 * Коэффициент загрузки хранилища.
	 * <p>Если заполненность хранилища превышает коэффициент, то хранилище
	 * расширяется.</p>
	 */
	private static final float LOAD_FACTOR = 0.75f;

	/**
	 * Коэффициент увеличения размера хранилища.
	 */
	private static final float INCREASE_FACTOR = LOAD_FACTOR + 1f;

	/**
	 * Массив для хранения объектов.
	 */
	private Object[] storage;

	/**
	 * Количество элементов.
	 * <p>Необходимо для расширения хранилища, когда будет превышен коеффициент
	 * загрузки.</p>
	 */
	private int elementsCount;

	/**
	 * Количество изменений, произведённых в хранилище.
	 */
	private int modCount;

	/**
	 * Создаёт хранилище с начальным количеством элементов равным 17.
	 */
	public SimpleHashTable() {
		storage = new Object[17];
	}

	/**
	 * Создаёт хранилище с заданным пользователем размером.
	 * <p>Если заданное значение длины не является простым числом, то производится
	 * поиск ближайшего простого числа больше заданного.</p>
	 *
	 * @param pLength Размер хранилища, задаваемый пользователем.
	 */
	public SimpleHashTable(int pLength) {
		if (pLength < 0) {
			throw new NegativeArraySizeException("SimpleHashTable не может иметь отрицательную длину.");
		}
		if (!isPrime(pLength)) {
			pLength = getPrime(pLength);
		}

		storage = new Object[pLength];
	}

	/**
	 * Проверка числа на простоту.
	 *
	 * @param pNumber Число для проверки на простоту.
	 * @return true, если число простое, false, если число не простое.
	 */
	private boolean isPrime(int pNumber) {
		boolean result = true;
		if (pNumber == 1 || pNumber > 10 && pNumber % 10 == 5) {
			result = false;
		} else {
			for (int i = 2; i < pNumber; i++) {
				if (pNumber % i == 0) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Поиск индекса в массиве, под которым находится простое число.
	 *
	 * @param pNumber Число.
	 * @return Индекс в массиве, под которым находится простое число.
	 */
	private int getPrime(int pNumber) {
		int result;
		while (true) {
			if (isPrime(pNumber)) {
				result = pNumber;
				break;
			}
			pNumber++;
		}
		return result;
	}

	/**
	 * Поиск объекта по его ключу.
	 *
	 * @param pObject Искомый объект.
	 * @return Искомый объект.
	 */
	@SuppressWarnings("unchecked")
	public E find(E pObject) {
		int index = calculateIndex(pObject.hashCode(), storage.length);
		if (storage[index] != null && pObject.equals(storage[index])) {
			return (E) storage[index];
		}
		return null;
	}

	/**
	 * Вставка объекта.
	 * <p>Если заполненность хранилища превышает коэффициент загрузки,
	 * то размер хранилища увеличивается и существующие элементы перераспределяются
	 * в новом хранилище. После этого добавляется новый объект.</p>
	 *
	 * @param pObject Добавляемый объект.
	 * @return true если объект был удачно добавлен, в противном случае - false.
	 */
	public boolean insert(E pObject) {
		if (elementsCount >= Math.floor(storage.length * LOAD_FACTOR)) {
			rebuildStorage();
		}
		int index = calculateIndex(pObject.hashCode(), storage.length);
		if (storage[index] == null) {
			storage[index] = pObject;
			elementsCount++;
			modCount++;
			return true;
		}
		return false;
	}

	/**
	 * Удаление объекта.
	 *
	 * @param pObject Ключ, по которому находится и удаляется объект.
	 * @return Удаляемый объект.
	 */
	@SuppressWarnings("unchecked")
	public E delete(E pObject) {
		E result = null;
		int index = calculateIndex(pObject.hashCode(), storage.length);
		if (storage[index] != null && pObject.equals(storage[index])) {
			result = (E) storage[index];
			storage[index] = null;
			elementsCount--;
			modCount++;
		}
		return result;
	}

	/**
	 * Размер хранилища.
	 *
	 * @return Размер хранилища.
	 */
	public int size() {
		return storage.length;
	}

	/**
	 * Рассчёт индекс массива на основе хэшкода объекта.
	 *
	 * @param pHashCode Хэшкод объекта.
	 * @param pLength Размер хранилища.
	 * @return Индекс массива.
	 */
	private int calculateIndex(int pHashCode, int pLength) {
		return Math.abs(pHashCode) % pLength;
	}

	/**
	 * Получение количества непустых элементов хранилища.
	 *
	 * @return Количества непустых элементов хранилища.
	 */
	public int getElementsCount() {
		return elementsCount;
	}

	/**
	 * Увеличение хранилища с перераспределением объектов из старого хранилища.
	 */
	private void rebuildStorage() {
		int newSize = Math.round((float) storage.length * INCREASE_FACTOR);
		newSize = getPrime(newSize);
		Object[] newTable = new Object[newSize];
		int newIndex;
		for (Object o : storage) {
			if (o != null) {
				newIndex = calculateIndex(o.hashCode(), newSize);
				newTable[newIndex] = o;
			}
		}
		storage = newTable;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (Object obj : storage) {
			sb.append(obj).append(',').append(' ');
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.append(']').toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr<>();
	}

	/**
	 * Итератор хэш-таблицы.
	 *
	 * @param <T> тип параметра.
	 */
	private class Itr<T> implements Iterator<T> {
		/**
		 * Позиция в хранилище, с которой нужно начинать поиск следующего элемента.
		 */
		private int cursor;

		/**
		 * Ожидаемое количество изменений в хранилище.
		 */
		private int expectedModCount;

		/**
		 * Конструктор итератора.
		 */
		Itr() {
			expectedModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			for (int i = cursor; i < storage.length; i++) {
				if (storage[i] != null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public T next() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T result = null;
			for (int i = cursor; i < storage.length; i++) {
				if (storage[i] != null) {
					result = (T) storage[i];
					cursor = i + 1;
					break;
				}
			}
			return result;
		}
	}
}
