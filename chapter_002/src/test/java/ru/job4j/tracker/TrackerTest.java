package ru.job4j.tracker;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тестируем систему учёта заданий.
 */
public class TrackerTest {
	private Tracker tracker = new Tracker();

	@Test
	public void testAddItem() {
		Item task = new Item("task1", "first task");
		tracker.add(task);

		Item result = tracker.findAll()[0];

		assertThat(result, is(task));
	}

	@Test
	public void testUpdateItem() {
		Item task1 = new Item("task1", "first task", 456987L);
		tracker.add(task1);

		Item task2 = new Item("task2", "second task for replacement of first task");
		task2.setId(task1.getId());

		tracker.update(task2);

		String result = tracker.findById(task2.getId()).getName();

		assertThat(result, is("task2"));
	}

	@Test
	public void testDeleteItem() {
		Item task1 = new Item("task one", "First task", 789654L);
		tracker.add(task1);

		Item task2 = new Item("task two", "Second task for replacement of first task");
		tracker.add(task2);

		Item task3 = new Item("task three", "Third task", 123478L);
		tracker.add(task3);

		Item task4 = new Item("task four", "Fourth task :-)");
		tracker.add(task4);

		tracker.delete(task1);
		tracker.delete(task3);

		String result = tracker.findByName("task four")[0].getDesc();

		assertThat(result, is("Fourth task :-)"));
	}

	/*
	@Test
	public void testFindItemsByName() {

	}

	@Test
	public void testFindItemById() {

	}*/
}
