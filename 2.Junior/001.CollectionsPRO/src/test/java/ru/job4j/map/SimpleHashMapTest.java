package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Тестирование хэш-карты.
 */
public class SimpleHashMapTest {
	/**
	 * Проверка конструктора с заданием длины внутреннего хранилища.
	 * Также проверяется перестройка хранилища при вставке количества Entry,
	 * превышающем коэффициент загрузки.
	 */
	@Test
	public void testConstructorWithLengthParam() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
		Assert.assertThat(map.size(), Is.is(5));
		map = new SimpleHashMap<>(69);
		Assert.assertThat(map.size(), Is.is(71));
		map = new SimpleHashMap<>(17);
		Assert.assertThat(map.size(), Is.is(17));
	}

	/**
	 * Проверка конструктора с заданием отрицательного размера массива.
	 * Проверка выброса исключения.
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testConstructorWithNegativeLength() {
		new SimpleHashMap<Integer, String>(-69);
	}

	/**
	 * Проверка перестройки внутреннего хранилища в случае, когда
	 * количество элементов превышает коэффициент загрузки.
	 */
	@Test
	public void testRebuildOfInnerStorage() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
		for (int i = 0; i < 7; i++) {
			map.insert(i, "" + i);
		}
		Assert.assertThat(map.size(), Is.is(11));

		for (int i = 7; i < 20; i++) {
			map.insert(i, "" + i);
		}
		Assert.assertThat(map.size(), Is.is(41));
	}

	/**
	 * Проверка вставки, когда расчёт индекса ячейки по хэш-коду ключа
	 * даёт разные значения.
	 */
	@Test
	public void testInsert() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>(5);
		Assert.assertThat(map.insert(1, "1"), Is.is(true));
		Assert.assertThat(map.size(), Is.is(5));

		Assert.assertThat(map.insert(2, "2"), Is.is(true));
		Assert.assertThat(map.size(), Is.is(5));

		Assert.assertThat(map.insert(3, "3"), Is.is(true));
		Assert.assertThat(map.size(), Is.is(5));

		Assert.assertThat(map.insert(4, "4"), Is.is(true));
		Assert.assertThat(map.size(), Is.is(11));
	}

	/**
	 * Проверка невозможности вставки Entry в случае, когда в ячейке уже
	 * находится другой Entry.
	 */
	@Test
	public void testUnableInsertIntoSameStorageCell() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>(5);
		Assert.assertThat(map.insert(1, "1"), Is.is(true));
		Assert.assertThat(map.insert(1, "1"), Is.is(false));
		Assert.assertThat(map.insert(2, "2"), Is.is(true));
		Assert.assertThat(map.insert(2, "2"), Is.is(false));
		Assert.assertThat(map.insert(3, "3"), Is.is(true));
		Assert.assertThat(map.insert(3, "3"), Is.is(false));
		Assert.assertThat(map.insert(4, "4"), Is.is(true));
		Assert.assertThat(map.insert(4, "4"), Is.is(false));
	}

	/**
	 * Тест получения объектов из хэш-карты.
	 */
	@Test
	public void testGet() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		map.insert(2, "1");
		map.insert(3, "2");
		map.insert(4, "3");
		Assert.assertThat(map.get(1), Is.is("0"));
		Assert.assertThat(map.get(2), Is.is("1"));
		Assert.assertThat(map.get(3), Is.is("2"));
		Assert.assertThat(map.get(4), Is.is("3"));
	}

	/**
	 * Тест удаления объектов из хэш-карты.
	 * Проверка удаления сперва всех элементов, пока не останется ни одного.
	 * Проверка удаления Entry по несуществующему в хранилище ключу.
	 */
	@Test
	public void delete() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		map.insert(2, "1");
		map.insert(3, "2");
		map.insert(4, "3");
		Assert.assertThat(map.getElementsCount(), Is.is(4));

		Assert.assertThat(map.delete(1), Is.is(true));
		Assert.assertThat(map.getElementsCount(), Is.is(3));

		Assert.assertThat(map.delete(2), Is.is(true));
		Assert.assertThat(map.getElementsCount(), Is.is(2));

		Assert.assertThat(map.delete(3), Is.is(true));
		Assert.assertThat(map.getElementsCount(), Is.is(1));

		Assert.assertThat(map.delete(4), Is.is(true));
		Assert.assertThat(map.getElementsCount(), Is.is(0));

		Assert.assertThat(map.delete(4), Is.is(false));
	}

	/**
	 * Тест итератора без выброса исключений.
	 */
	@Test
	public void testIteratorInNormalMode() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		map.insert(2, "1");
		map.insert(3, "2");
		map.insert(4, "3");
		Iterator<SimpleHashMap.Entry<Integer, String>> itr = map.iterator();
		Assert.assertThat(itr.hasNext(), Is.is(true));
		Assert.assertThat(itr.hasNext(), Is.is(true));
		SimpleHashMap.Entry<Integer, String> matcherEntry = new SimpleHashMap.Entry<>(1, "0");
		SimpleHashMap.Entry<Integer, String> actualEntry = itr.next();
		Assert.assertThat(actualEntry.getKey(), Is.is(matcherEntry.getKey()));
		Assert.assertThat(actualEntry.getValue(), Is.is(matcherEntry.getValue()));
		Assert.assertThat(actualEntry.toString(), Is.is("1 = 0"));

		Assert.assertThat(itr.hasNext(), Is.is(true));
		Assert.assertThat(itr.hasNext(), Is.is(true));
		matcherEntry = new SimpleHashMap.Entry<>(2, "1");
		actualEntry = itr.next();
		Assert.assertThat(actualEntry.getKey(), Is.is(matcherEntry.getKey()));
		Assert.assertThat(actualEntry.getValue(), Is.is(matcherEntry.getValue()));

		Assert.assertThat(itr.hasNext(), Is.is(true));
		Assert.assertThat(itr.hasNext(), Is.is(true));
		matcherEntry = new SimpleHashMap.Entry<>(3, "2");
		actualEntry = itr.next();
		Assert.assertThat(actualEntry.getKey(), Is.is(matcherEntry.getKey()));
		Assert.assertThat(actualEntry.getValue(), Is.is(matcherEntry.getValue()));

		Assert.assertThat(itr.hasNext(), Is.is(true));
		Assert.assertThat(itr.hasNext(), Is.is(true));
		matcherEntry = new SimpleHashMap.Entry<>(4, "3");
		actualEntry = itr.next();
		Assert.assertThat(actualEntry.getKey(), Is.is(matcherEntry.getKey()));
		Assert.assertThat(actualEntry.getValue(), Is.is(matcherEntry.getValue()));
	}

	/**
	 * Тест итератора в случае, когда происходят изменения
	 * во внутренней структуре во время итерирования.
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorWhenConcurrentModificationsHappen() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		Iterator<SimpleHashMap.Entry<Integer, String>> itr = map.iterator();
		map.insert(2, "1");
		itr.next();
	}

	/**
	 * Тест итератора в случае, когда метод next() выбрасывает
	 * исключение NoSuchElementException.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testIteratorWhenNoMoreElements() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		Iterator<SimpleHashMap.Entry<Integer, String>> itr = map.iterator();
		itr.next();
		itr.next();
	}

	/**
	 * Тест преобразования в строку карты.
	 */
	@Test
	public void testToStringOfSimpleHashMap() {
		SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
		map.insert(1, "0");
		map.insert(2, "1");
		Assert.assertThat(map.toString(), Is.is("{1 = 0, 2 = 1}"));
	}
}