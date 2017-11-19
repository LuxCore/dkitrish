package ru.job4j.tracker;

import org.junit.Before;
// import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

import ru.job4j.start.StartUI;

import java.util.Random;

/**
 *
 */
public class StubInputTest {
	private Tracker tracker;
	private Input input;

	/**
	 * Initialization of test data.
	 */
	@Before
	public void init() {
		this.tracker = new Tracker();
		this.input = new StubInput();
		Random rnd = new Random(69);

		for (int i = 0; i < 20; i++) {
			this.tracker.add(new Item("Task " + rnd.nextInt(69), "Desc " + (i + 1)));
		}

		// int index = 0;
		// for (Item task : this.tracker.findAll()) {
		// 	System.out.println(index++ + ": " + task);
		// }
	}

	/**
	 * Tests creation of task.
	 */
	@Test
	public void testCreateTask() {
		this.input = new StubInput(new String[]{"1", "New task", "Desc for new task", "7"});
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll()[20].getName(), is("New task"));
	}

	/**
	 * Tests update of task.
	 */
	@Test
	public void testUpdateTask() {
		String id = this.tracker.findByName("Task 10")[0].getId();
		this.input = new StubInput(new String[]{"2", "New task", "Desc for new task", id, "7"});
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll()[5].getName(), is("New task"));
	}

	/**
	 * Tests deletion of task.
	 */
	@Test
	public void testDeleteTask() {
		String id1 = this.tracker.findByName("Task 14")[0].getId();
		String id2 = this.tracker.findByName("Task 42")[0].getId();
		this.input = new StubInput(new String[]{"3", id1, "3", id2, "7"});

		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().length, is(18));
	}

	/**
	 * Tests finding of task by its ID.
	 */
	@Test
	public void testFindTaskById() {
		String id = this.tracker.findByName("Task 38")[0].getId();
		this.input = new StubInput(new String[]{"4", id, "7"});

		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll()[3].getName(), is("Task 38"));
	}

	/**
	 * Tests finding of tasks by name.
	 */
	@Test
	public void testFindTasksByName() {
		// First we add a one more task to tracker with existing name and then
		// we select option to find tasks by name.
		this.input = new StubInput(new String[]{"1", "Task 38", "Desc for new task", "5", "Task 38", "7"});

		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findByName("Task 38").length, is(2));
	}

	/**
	 * Tests finding all tasks.
	 */
	@Test
	public void testFindAllTasks() {
		this.input = new StubInput(new String[]{"6", "7"});
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().length, is(20));
	}
}