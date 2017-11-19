package ru.job4j.start;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;

/**
 * Launch point of Tracker app.
 */
public class StartUI {
	/**
	 * Menu item: add a task to tracker.
	 */
	private static final String ADD = "1";

	/**
	 * Menu item: update an existing task in tracker.
	 */
	private static final String UPDATE = "2";

	/**
	 * Menu item: delete a task from tracker.
	 */
	private static final String DELETE = "3";

	/**
	 * Menu item: find a task by its id.
	 */
	private static final String FIND_BY_ID = "4";

	/**
	 * Menu item: find all tasks by its name.
	 */
	private static final String FIND_BY_NAME = "5";

	/**
	 * Menu item: find all non-nullable tasks in tracker.
	 */
	private static final String FIND_ALL = "6";

	/**
	 * Menu item: exit application.
	 */
	private static final String EXIT = "7";

	/**
	 * Instance of task tracker.
	 */
	private final Tracker tracker;

	/**
	 * Kind of input: it can be from console or from automated tests.
	 */
	private final Input input;

	/**
	 * Set the tracker of tasks and type of getting answers from user.
	 *
	 * @param tracker Task tracker.
	 * @param input Input type of answers from user.
	 */
	public StartUI(Tracker tracker, Input input) {
		this.tracker = tracker;
		this.input = input;
	}

	/**
	 * Prime app thread.
	 *
	 * @param args Console arguments for launch of Tracker app.
	 */
	public static void main(String[] args) {
		ConsoleInput input = new ConsoleInput();
		// StubInput input = new StubInput(new String[]{"create stub task"});
		new StartUI(new Tracker(), input).init();
	}

	/**
	 * Main cycle for interaction with user and process his input answers.
	 */
	public void init() {
		boolean exit = false;

		while (!exit) {
			this.showMenu();
			String answer = this.input.ask("Выберите подходящий пункт меню (введите соответствующее число): ");
			if (ADD.equals(answer)) {
				this.createTask();
			} else if (UPDATE.equals(answer)) {
				this.updateTask();
			} else if (DELETE.equals(answer)) {
				this.deleteTask();
			} else if (FIND_BY_ID.equals(answer)) {
				this.findTaskById();
			} else if (FIND_BY_NAME.equals(answer)) {
				this.findTaskByName();
			} else if (FIND_ALL.equals(answer)) {
				this.findAllTasks();
			} else if (EXIT.equals(answer)) {
				exit = true;
			}
		}
	}

	/**
	 * Application menu.
	 */
	private void showMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tМеню:\n")
			.append("\t1: Добавить задачу\n")
			.append("\t2: Обновить задачу\n")
			.append("\t3: Удалить задачу\n")
			.append("\t4: Показать задачу по идентификатору\n")
			.append("\t5: Показать задачи по имени\n")
			.append("\t6: Показать все задачи\n")
			.append("\t7: Завершить приложение\n");

		System.out.println(sb.toString());
	}

	/**
	 * Offer a user to type data for new task.
	 */
	private void createTask() {
		System.out.println("----- Создание задачи -----");

		String taskName = this.input.ask("Введите наименование задачи: ");
		String taskDesc = this.input.ask("Введите описание задачи: ");

		Item task = new Item(taskName, taskDesc);
		this.tracker.add(task);

		System.out.println("Задача с ID = " + task.getId() + " успешно создана.");
	}

	/**
	 * Offer a user to type a new task to update an existing one.
	 */
	private void updateTask() {
		System.out.println("----- Обновление задачи -----");

		String taskName = this.input.ask("Введите наименование задачи: ");
		String taskDesc = this.input.ask("Введите описание задачи: ");
		String taskId = this.input.ask("Введите id задачи, которую необходимо изменить: ");

		Item task = new Item(taskName, taskDesc);
		task.setId(taskId);
		this.tracker.update(task);

		System.out.println("Задача с ID = " + task.getId() + " успешно обновлена.");
	}

	/**
	 * Deletes a task item from tracker.
	 */
	private void deleteTask() {
		System.out.println("----- Удаление задачи -----");

		String taskId = this.input.ask("Введите ID задачи, которую необходимо удалить: ");
		Item task = this.tracker.findById(taskId);
		this.tracker.delete(task);

		System.out.println("Задача с ID = " + task.getId() + " успешно удалена.");
	}

	/**
	 * Find task item by ID.
	 */
	private void findTaskById() {
		System.out.println("----- Поиск задачи по ID -----");

		String taskId = this.input.ask("Введите ID задачи, которую необходимо найти/показать: ");
		Item item = this.tracker.findById(taskId);

		// System.out.println("Задача с ID = " + taskId + " успешно найдена.");
		System.out.println("Задача с искомым ID найдена: " + item);
	}

	/**
	 * Find tasks item by name.
	 */
	private void findTaskByName() {
		System.out.println("----- Поиск всех задач по наименованию -----");

		String taskName = this.input.ask("Введите наименование задачи, которую необходимо найти/показать: ");
		Item[] items = this.tracker.findByName(taskName);

		System.out.println("Список задач с наименованием " + taskName + ":");
		this.showTaskList(items);
	}

	/**
	 * Shows all created tasks.
	 */
	private void findAllTasks() {
		System.out.println("----- Список всех задач -----");
		this.showTaskList(this.tracker.findAll());
	}

	private void showTaskList(Item[] items) {
		byte index = 1;
		for (Item task : items) {
			System.out.println(index++ + ": " + task);
		}
	}
}
