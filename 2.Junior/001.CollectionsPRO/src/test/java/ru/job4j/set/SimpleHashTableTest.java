package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование хэш-таблицы.
 */
public class SimpleHashTableTest {
	/**
	 * Тест конструктора по умолчанию.
	 */
	@Test
	public void testDefaultConstructor() {
		SimpleHashTable<Integer> hashTable = new SimpleHashTable<>();
		assertThat(hashTable.size(), is(17));
	}

	/**
	 * Тест конструктора с передачей в качестве параметра начальной длины
	 * хранилища.
	 */
	@Test
	public void testConstructorWithLength() {
		SimpleHashTable<Integer> hashTable = new SimpleHashTable<>(69);
		assertThat(hashTable.size(), is(71));
	}

	/**
	 * Тест исключения при вызове конструктора с передачей в него отрицательнго
	 * параметра.
	 */
	@Test(expected = NegativeArraySizeException.class)
	public void testConstructorNegativeLength() {
		new SimpleHashTable<>(-69);
	}

	/**
	 * Тест добавления целых чисел.
	 */
	@Test
	public void testAddInteger() {
		SimpleHashTable<Integer> hashTable = new SimpleHashTable<>();
		for (int i = 0; i < 10; i++) {
			hashTable.insert(i);
		}
		assertThat(hashTable.size(), is(17));
		assertThat(hashTable.getElementsCount(), is(10));
	}

	/**
	 * Тест добавления строк.
	 */
	@Test
	public void testAddString() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(5);
		hashTable.insert("one");
		hashTable.insert("two");
		hashTable.insert("three");
		hashTable.insert("four");
		hashTable.insert("five");
		hashTable.insert("six");
		hashTable.insert("seven");
		hashTable.insert("eight");
		hashTable.insert("nine");
		hashTable.insert("ten");
		hashTable.insert("eleven");
		hashTable.insert("twelve");
		hashTable.insert("thirteen");
		hashTable.insert("fourteen");
		hashTable.insert("fifteen");
		hashTable.insert("sixteen");
		hashTable.insert("seventeen");
		hashTable.insert("eighteen");
		hashTable.insert("nineteen");
		hashTable.insert("twenty");
		hashTable.insert("twenty one");
		hashTable.insert("twenty two");
		assertThat(hashTable.size(), is(19));
	}

	/**
	 * Тест поиска объектов в хранилище.
	 */
	@Test
	public void testFindObject() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(69);
		for (Integer i = 0; i < 32; i++) {
			hashTable.insert(i.toString());
		}
		assertThat(hashTable.find("10"), is("10"));
	}

	/**
	 * Тест удаления объектов.
	 */
	@Test
	public void testDeleteObject() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(5);
		hashTable.insert("one");
		hashTable.insert("two");
		hashTable.insert("three");
		hashTable.insert("four");
		hashTable.insert("five");
		hashTable.insert("six");
		hashTable.insert("seven");
		hashTable.insert("eight");
		hashTable.insert("nine");
		hashTable.insert("ten");
		hashTable.insert("eleven");
		hashTable.insert("twelve");
		hashTable.insert("thirteen");
		hashTable.insert("fourteen");
		hashTable.insert("fifteen");
		hashTable.insert("sixteen");
		hashTable.insert("seventeen");
		hashTable.insert("eighteen");
		hashTable.insert("nineteen");
		hashTable.insert("twenty");
		hashTable.insert("twenty one");
		hashTable.insert("twenty two");
		assertThat(hashTable.getElementsCount(), is(13));
		assertThat(hashTable.delete("thirteen"), is("thirteen"));
		assertThat(hashTable.getElementsCount(), is(12));
		hashTable.delete("one");
		hashTable.delete("four");
		hashTable.delete("ten");
		assertThat(hashTable.getElementsCount(), is(9));
	}

	/**
	 * Тест итератора.
	 */
	@Test
	public void testIterator() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(6);
		hashTable.insert("one");
		hashTable.insert("two");
		hashTable.insert("three");
		hashTable.insert("four");
		hashTable.insert("five");
		hashTable.insert("six");
		hashTable.insert("seven");
		hashTable.insert("eight");
		hashTable.insert("nine");
		hashTable.insert("ten");
		hashTable.insert("eleven");
		hashTable.insert("twelve");
		hashTable.insert("thirteen");
		hashTable.insert("fourteen");
		hashTable.insert("fifteen");
		hashTable.insert("sixteen");
		hashTable.insert("seventeen");
		hashTable.insert("eighteen");
		hashTable.insert("nineteen");
		hashTable.insert("twenty");
		hashTable.insert("twenty one");
		hashTable.insert("twenty two");
		Iterator<String> itr = hashTable.iterator();
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.next(), is("two"));
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.hasNext(), is(true));
		assertThat(itr.next(), is("twenty one"));
		assertThat(itr.next(), is("four"));
		assertThat(itr.next(), is("twelve"));
		assertThat(itr.next(), is("five"));
		assertThat(itr.next(), is("eleven"));
		assertThat(itr.next(), is("thirteen"));
		assertThat(itr.next(), is("twenty"));
		assertThat(itr.next(), is("nine"));
		assertThat(itr.next(), is("seven"));
		assertThat(itr.next(), is("fifteen"));
		assertThat(itr.next(), is("eighteen"));
		assertThat(itr.next(), is("three"));
		assertThat(itr.hasNext(), is(false));
	}

	/**
	 * Тест исключения при "одновременном" изменении итератора и его использовании.
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorConcurrentModification() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(6);
		hashTable.insert("one");
		Iterator<String> itr = hashTable.iterator();
		hashTable.insert("two");
		itr.next();
	}

	/**
	 * Тест исключения, когда больше элементов не находится.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testIteratorNoSuchElement() {
		SimpleHashTable<String> hashTable = new SimpleHashTable<>(6);
		hashTable.insert("one");
		hashTable.insert("two");
		Iterator<String> itr = hashTable.iterator();
		assertThat(itr.next(), is("two"));
		assertThat(itr.next(), is("one"));
		itr.next();
	}
}
