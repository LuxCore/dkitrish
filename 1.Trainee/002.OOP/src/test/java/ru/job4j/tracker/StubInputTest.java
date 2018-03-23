package ru.job4j.tracker;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

import ru.job4j.start.StartUI;

/**
 * Check our functionality of tracker managment.
 */
public class StubInputTest {
	/**
	 * Tracker of tasks.
	 */
	private Tracker tracker;

	/**
	 * Any kind input that implements Input.
	 */
	private Input input;

	/**
	 * Initialization of test data.
	 */
	@Before
	public void init() {
		this.tracker = new Tracker();
		Random rnd = new Random(69);

		for (int i = 0; i < 20; i++) {
			this.tracker.add(new Item("Task " + rnd.nextInt(69), "Desc " + (i + 1)));
		}
	}

	/**
	 * Tests creation of task.
	 */
	@Test
	public void testCreateTask() {
		this.input = new ValidMenuNumberInput(new StubInput(new String[]{"69", "fgh", "1", "New task", "Desc for new task", "7"}));
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().get(20).getName(), is("New task"));
	}

	/**
	 * Tests update of task.
	 */
	@Test
	public void testUpdateTask() {
		String id = this.tracker.findByName("Task 10").get(0).getId();
		this.input = new ValidMenuNumberInput(new StubInput(new String[]{"2", "New task", "Desc for new task", id, "69", "7"}));
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().get(5).getName(), is("New task"));
	}

	/**
	 * Tests deletion of task.
	 */
	@Test
	public void testDeleteTask() {
		String id1 = this.tracker.findByName("Task 14").get(0).getId();
		String id2 = this.tracker.findByName("Task 42").get(0).getId();
		this.input = new ValidMenuNumberInput(new StubInput(new String[]{"3", id1, "qwe", "3", id2, "7"}));

		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().size(), is(18));
	}

	/**
	 * Tests finding of task by its ID.
	 */
	@Test
	public void testFindTaskById() {
		String id = this.tracker.findByName("Task 38").get(0).getId();
		this.input = new StubInput(new String[]{"4", id, "7"});

		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().get(3).getName(), is("Task 38"));
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
		assertThat(this.tracker.findByName("Task 38").size(), is(2));
	}

	/**
	 * Tests finding all tasks.
	 */
	@Test
	public void testFindAllTasks() {
		this.input = new StubInput(new String[]{"6", "7"});
		new StartUI(this.tracker, this.input).init();
		assertThat(this.tracker.findAll().size(), is(20));
	}
}
