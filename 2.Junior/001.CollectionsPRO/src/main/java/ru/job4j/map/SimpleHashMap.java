package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация просто хэш-карты.
 *
 * @param <K> Параметризованный тип ключа.
 * @param <V> Параметризованный тип значения.
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
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
	 * Хранилище пар, содердащих соответствия ключ-значение.
	 */
	private Entry<K, V>[] storage;

	/**
	 * Конструктор, задающий начальную длину хранилища равную 17.
	 */
	@SuppressWarnings("unchecked")
	SimpleHashMap() {
		storage = (Entry<K, V>[]) new Entry[17];
	}

	/**
	 * Конструктор с инициализацией начального размера хранилища.
	 *
	 * @param length Начальный размер внутреннего хранилища карты.
	 */
	@SuppressWarnings("unchecked")
	SimpleHashMap(int length) {
		if (length < 0) {
			throw new NegativeArraySizeException("SimpleHashMap не может иметь отрицательную длину.");
		}
		storage = (Entry<K, V>[]) new Entry[getPrime(length)];
	}

	/**
	 * Запись, содержащая в себе одну пару ключ-значение.
	 *
	 * @param <K> Параметризованный тип ключа.
	 * @param <V> Параметризованный тип значения.
	 */
	public static class Entry<K, V> {
		/**
		 * Ключ.
		 */
		private K key;

		/**
		 * Значение.
		 */
		private V value;

		/**
		 * Конструктор пары ключ-значение.
		 *
		 * @param key   Ключ.
		 * @param value Значение.
		 */
		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Получение ключа.
		 *
		 * @return Возвращает ключ.
		 */
		public K getKey() {
			return key;
		}

		/**
		 * Получение значения.
		 *
		 * @return Значение.
		 */
		public V getValue() {
			return value;
		}

		/**
		 * Установка значения.
		 *
		 * @param value Значение.
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}
	}

	/**
	 * Вставка пары ключ-значение.
	 * Если ключ уже существует, то новое значение перезапишет старое и вернёт
	 * false.
	 *
	 * @param key   Ключ, которому соответствует value.
	 * @param value Значение, которое соответствует key.
	 * @return true, если пара ключ-значение вставлены в карту, false - не вставлены.
	 */
	public boolean insert(K key, V value) {
		if (elementsCount >= Math.floor(LOAD_FACTOR * storage.length)) {
			rebuild();
		}
		boolean result = false;
		if (key != null) {
			int index = indexFor(key.hashCode(), storage.length);
			if (storage[index] == null) {
				storage[index] = new Entry<>(key, value);
				result = true;
				elementsCount++;
				modCount++;
			} else {
				if (key.equals(storage[index].key)) {
					storage[index].setValue(value);
				}
			}
		}
		return result;
	}

	/**
	 * Получение значения по ключу.
	 *
	 * @param key Ключ, по которому должен быть возвращено значение.
	 * @return Значение, соответствующее ключу.
	 */
	@SuppressWarnings("unchecked")
	public V get(K key) {
		return storage[indexFor(key, storage.length)].getValue();
	}

	/**
	 * Удаление пары ключ-значение по ключу.
	 *
	 * @param key Ключ.
	 * @return true, если удалён успешно
	 */
	public boolean delete(K key) {
		int index = indexFor(key, storage.length);
		Entry<K, V> e = storage[index];
		if (e != null && key.equals(e.key)) {
			storage[index] = null;
			elementsCount--;
			modCount++;
			return true;
		}
		return false;
	}

	@Override
	public Iterator<SimpleHashMap.Entry<K, V>> iterator() {
		return new Itr();
	}

	/**
	 * Итератор по хранилищу внутреннему.
	 */
	private class Itr implements Iterator<SimpleHashMap.Entry<K, V>> {
		/**
		 * Флаг изменчивости структуры хранилища.
		 */
		private int expectedModCount = modCount;

		/**
		 * Курсор, указывающий на текущее положение в хранилище.
		 */
		private int cursor;

		@Override
		public boolean hasNext() {
			return getNextPositionOfNonNullElement() != -1;
		}

		@Override
		@SuppressWarnings("unchecked")
		public Entry next() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			cursor = getNextPositionOfNonNullElement();
			return storage[cursor++];
		}

		/**
		 * Получение следующей позиции, в которой содержится Entry.
		 *
		 * @return Позицию, в которой содержится Entry.
		 */
		private int getNextPositionOfNonNullElement() {
			int result = -1;
			for (int i = cursor; i < storage.length; i++) {
				if (storage[i] != null) {
					result = i;
					break;
				}
			}
			return result;
		}
	}

	/**
	 * Получение размера размера внутреннего хранилища карты.
	 *
	 * @return Размер внутреннего хранилища карты.
	 */
	public int size() {
		return storage.length;
	}

	/**
	 * Получение количества элементов в хранилище.
	 *
	 * @return Количество хранящихся элементов.
	 */
	public int getElementsCount() {
		return elementsCount;
	}

	/**
	 * Хэш-функция для расчёта хэш-кода ключа.
	 *
	 * @param key    Ключ хэш-карты, для расчёта хэш-кода.
	 * @param length Размер хранилища.
	 * @return Хэш-код.
	 */
	private static int indexFor(Object key, int length) {
		return Math.abs(key.hashCode()) % length;
	}

	/**
	 * Перестройка хранилища в случае, когда количество элементов хранилища
	 * превышает коэффициент загрузки.
	 */
	private void rebuild() {
		int newSize = (int) Math.ceil(INCREASE_FACTOR * storage.length);
		newSize = getPrime(newSize);
		@SuppressWarnings("unchecked")
		Entry<K, V>[] newStorage = (Entry<K, V>[]) new Entry[newSize];
		for (Entry<K, V> e : storage) {
			if (e != null) {
				newStorage[indexFor(e.key, newSize)] = e;
			}
		}
		storage = newStorage;
	}

	/**
	 * Поиск простого числа бОльшего по отношению к передаваемому параметру.
	 *
	 * @param pNumber Число.
	 * @return Ближайшее простое число, которое равно или больше передаваемому.
	 */
	private static int getPrime(int pNumber) {
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
	 * Проверка числа на простоту.
	 *
	 * @param pNumber Число для проверки на простоту.
	 * @return true, если число простое, false, если число не простое.
	 */
	private static boolean isPrime(int pNumber) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for (Object o : storage) {
			if (o != null) {
				if (sb.length() > 1) {
					sb.append(',').append(' ');
				}
				sb.append(o);
			}
		}
		return sb.append('}').toString();
	}
}
