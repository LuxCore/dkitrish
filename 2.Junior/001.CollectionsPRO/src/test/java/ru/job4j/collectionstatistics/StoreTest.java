package ru.job4j.collectionstatistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование разницы между коллекциями.
 */
public class StoreTest {
	/**
	 * Тест, когда элементы были добавлены в пустую колелкци.
	 */
	@Test
	public void testWhenElementsWereAddedToEmptyCollection() {
		Store store = new Store();
		List<User> previous = new ArrayList<>();
		List<User> current = new ArrayList<>();
		current.add(new User(20, "User_20"));
		current.add(new User(30, "User_30"));
		current.add(new User(35, "User_35"));
		current.add(new User(40, "User_40"));
		current.add(new User(50, "User_50"));
		Info expected = new Info(5, 0, 0);
		Info actual = store.diff(current, previous);
		assertEquals(expected, actual);
	}

	/**
	 * Тест, когда все элементы удалены из коллекции.
	 */
	@Test
	public void testWhenAllElementsWereDeletedFromCollection() {
		Store store = new Store();
		List<User> previous = new ArrayList<>();
		List<User> current = new ArrayList<>();
		previous.add(new User(10, "User_10"));
		previous.add(new User(20, "User_20"));
		previous.add(new User(30, "User_30"));
		previous.add(new User(40, "User_40"));
		Info expected = new Info(0, 0, 4);
		Info actual = store.diff(current, previous);
		assertEquals(expected, actual);
	}

	/**
	 * Тест, когда элементы в коллекции лишь изменялись внутренне.
	 */
	@Test
	public void testWhenElementsWereOnlyUpdated() {
		Store store = new Store();
		List<User> previous = new ArrayList<>();
		List<User> current = new ArrayList<>();
		previous.add(new User(10, "User_10"));
		previous.add(new User(20, "User_20"));
		previous.add(new User(30, "User_30"));
		previous.add(new User(40, "User_40"));
		current.add(new User(10, "User_110"));
		current.add(new User(20, "User_220"));
		current.add(new User(30, "User_30"));
		current.add(new User(40, "User_440"));
		Info expected = new Info(0, 3, 0);
		Info actual = store.diff(current, previous);
		assertEquals(expected, actual);
	}

	/**
	 * Смешанный тест, когда в коллекцию добавляются новые элементы,
	 * изменяются внутренние данные элементов, удаляются элементы.
	 */
	@Test
	public void testAllOperationsInOnePlace() {
		Store store = new Store();
		List<User> previous = new ArrayList<>();
		List<User> current = new ArrayList<>();
		previous.add(new User(10, "User_10"));
		previous.add(new User(20, "User_20"));
		previous.add(new User(30, "User_30"));
		previous.add(new User(40, "User_40"));
		previous.add(new User(50, "User_50"));
		previous.add(new User(60, "User_60"));
		current.add(new User(20, "User_20"));
		current.add(new User(30, "User_330"));
		current.add(new User(35, "User_35"));
		current.add(new User(40, "User_440"));
		current.add(new User(60, "User_60"));
		current.add(new User(70, "User_70"));
		current.add(new User(80, "User_80"));
		current.add(new User(90, "User_90"));
		current.add(new User(100, "User_100"));
		current.add(new User(110, "User_110"));
		current.add(new User(120, "User_120"));
		Info expected = new Info(7, 2, 2);
		Info actual = store.diff(current, previous);
		assertEquals(expected, actual);
	}
}