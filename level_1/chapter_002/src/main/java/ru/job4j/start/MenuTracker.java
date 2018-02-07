package ru.job4j.start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * User menu to manage the tracker`s tasks.
 */
public class MenuTracker {
	/**
	 * Instance of task tracker.
	 */
	private Tracker tracker;

	/**
	* Kind of input of answers from user.
	*/
	private Input input;

	/**
	 * List of available actions to a user.
	 */
	private List<UserAction> actions;

	/**
	 * Range of menu numbers.
	 */
	private List<Integer> menuNumRange;

	/**
	 * Constructs MenuTracker with tracker and input type.
	 *
	 * @param tracker Single instance of tracker.
	 * @param input Input type of answers.
	 */
	public MenuTracker(Tracker tracker, Input input) {
		this.tracker = tracker;
		this.input = input;
		this.init();
	}

	/**
	 * Initializing the MenuTracker.
	 */
	private void init() {
		this.menuNumRange = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		this.registerActions();
	}

	/**
	 * Add item event.
	 */
	private class AddItemAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		AddItemAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of add item action.
		 */
		@Override
		public int number() {
			return 1;
		}

		/**
		 * The description of action of task addition.
		 *
		 * @return The description of menu item of item addition.
		 */
		@Override
		public String description() {
			return "Добавить задачу";
		}

		/**
		 * Performs add item action.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		@Override
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Создание задачи -----");

			String taskName = input.ask("Введите наименование задачи: ");
			String taskDesc = input.ask("Введите описание задачи: ");

			Item task = new Item(taskName, taskDesc);
			tracker.add(task);

			System.out.println("Задача с ID = " + task.getId() + " успешно создана.");
		}
	}

	/**
	 * This action allows a user to delete any task from tracker.
	 */
	private static class DeleteItemAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		DeleteItemAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of delete item action.
		 */
		public int number() {
			return 3;
		}

		/**
		 * The description of delete item action.
		 *
		 * @return The description of menu item of deletion of task.
		 */
		public String description() {
			return "Удалить задачу";
		}

		/**
		 * Performs delete task action.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Удаление задачи -----");

			String taskId = input.ask("Введите ID задачи, которую необходимо удалить: ");
			Item task = tracker.findById(taskId);
			tracker.delete(task);

			System.out.println("Задача с ID = " + task.getId() + " успешно удалена.");
		}
	}

	/**
	 * Event of search item by ID.
	 */
	private class FindItemByIdAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		FindItemByIdAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of action of searching item by ID.
		 */
		public int number() {
			return 4;
		}

		/**
		 * The description of action of searching task by ID.
		 *
		 * @return The description of menu item of searching task by ID.
		 */
		public String description() {
			return "Показать задачу по идентификатору";
		}

		/**
		 * Performs action searching of item by its ID.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Поиск задачи по ID -----");

			String taskId = input.ask("Введите ID задачи, которую необходимо найти/показать: ");
			Item item = tracker.findById(taskId);

			System.out.println("Задача с искомым ID найдена: " + item);
		}
	}

	/**
	 * Event of search items by name.
	 */
	private class FindItemsByNameAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		FindItemsByNameAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of action of searching items by name.
		 */
		public int number() {
			return 5;
		}

		/**
		 * The description of action of searching tasks by name.
		 *
		 * @return The description of menu item of searching tasks by name.
		 */
		public String description() {
			return "Показать задачи по имени";
		}

		/**
		 * Performs action searching of items by its name.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Поиск всех задач по наименованию -----");

			String taskName = input.ask("Введите наименование задачи, которую необходимо найти/показать: ");
			List<Item> items = tracker.findByName(taskName);

			System.out.println("Список задач с наименованием " + taskName + ":");
			showTaskList(items);
		}
	}

	/**
	 * Event of search all items in tracker.
	 */
	private class FindAllItemsAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		FindAllItemsAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of action of searching of all items.
		 */
		public int number() {
			return 6;
		}

		/**
		 * The description of action of searching of all tasks.
		 *
		 * @return The description of menu item of searching of all tasks.
		 */
		public String description() {
			return "Показать все задачи";
		}

		/**
		 * Performs action searching of all items.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Список всех задач -----");
			showTaskList(tracker.findAll());
		}
	}

	/**
	 * Event of quit the tracker app.
	 */
	private class QuitTrackerAction extends BaseAction {
		/**
		 * Constructor of item addition.
		 *
		 * @param number Number of menu item.
		 * @param desc Description of menu item.
		 */
		QuitTrackerAction(int number, String desc) {
			super(number, desc);
		}

		/**
		 * Number of menu item.
		 *
		 * @return Number of action of quit the app.
		 */
		public int number() {
			return 7;
		}

		/**
		 * The description of action of quit the app.
		 *
		 * @return The description of menu item of quit the app.
		 */
		public String description() {
			return "Завершить приложение";
		}

		/**
		 * Performs action of quit the app.
		 * This method is empty because it do nothig.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
		}
	}

	/**
	 * Shows all needed menu items.
	 */
	public void show() {
		System.out.println("\n\t--- Меню ---");

		for (UserAction act : this.actions) {
			if (act != null) {
				System.out.println(act.info());
			}
		}

		System.out.println();
	}

	/**
	 * Regisering of all needed actions.
	 */
	private void registerActions() {
		int numOfAction = 0;
		this.actions = new ArrayList<>(this.menuNumRange.size());

		// Arguments in constructors are used as stubs because they don`t carry any meaning.
		this.actions.add(this.new AddItemAction(++numOfAction, "Добавить задачу"));
		this.actions.add(new UpdateItemAction(++numOfAction, "Обносить задачу"));
		this.actions.add(new MenuTracker.DeleteItemAction(++numOfAction, "Удалить задачу"));
		this.actions.add(this.new FindItemByIdAction(++numOfAction, "Поиск задачи по идентификатору"));
		this.actions.add(this.new FindItemsByNameAction(++numOfAction, "Поиск задач по имени"));
		this.actions.add(this.new FindAllItemsAction(++numOfAction, "Поиск всех задач"));
		this.actions.add(this.new QuitTrackerAction(++numOfAction, "Завершить приложение"));
	}

	/**
	 * Offers to user to select required menu item.
	 *
	 * @return Number of selected menu item.
	 */
	public int selectActionNum() {
		int answer = this.input.ask("Выберите подходящий пункт меню (введите соответствующее число): ", this.menuNumRange);
		return answer;
	}

	/**
	 * Performs the action corresponding to the selected menu item by user.
	 *
	 * @param userChoice Number of menu item user selected.
	 */
	public void performAction(int userChoice) {
		// We must predecrement number of menu item because this number
		// more per one then index of array element containing the corresponding
		// action.
		this.actions.get(--userChoice).execute(this.tracker, this.input);
	}

	/**
	 * Print out all tasks.
	 *
	 * @param items Items of tracker.
	 */
	private void showTaskList(List<Item> items) {
		byte index = 1;
		for (Item task : items) {
			System.out.println(index++ + ": " + task);
		}
	}

	/**
	 * Gets range of available menu numbers to user.
	 *
	 * @return Available menu numbers.
	 */
	public List<Integer> getMenuNumRange() {
		return this.menuNumRange;
	}
}

/**
 * This action allows a user to replace existing information of certain task
 * with new one.
 */
class UpdateItemAction extends BaseAction {
	/**
	 * Constructor of item addition.
	 *
	 * @param number Number of menu item.
	 * @param desc Description of menu item.
	 */
	UpdateItemAction(int number, String desc) {
		super(number, desc);
	}

	/**
	 * Number of menu item.
	 *
	 * @return Number of update item action.
	 */
	public int number() {
		return 2;
	}

	/**
	 * The description of update item action.
	 *
	 * @return The description of menu item of updating of task.
	 */
	public String description() {
		return "Обновить задачу";
	}

	/**
	 * Performs update task action.
	 *
	 * @param tracker Task tracker.
	 * @param input Type of input.
	 */
	public void execute(Tracker tracker, Input input) {
		System.out.println("----- Обновление задачи -----");

		String taskName = input.ask("Введите наименование задачи: ");
		String taskDesc = input.ask("Введите описание задачи: ");
		String taskId = input.ask("Введите id задачи, которую необходимо изменить: ");

		Item task = new Item(taskName, taskDesc);
		task.setId(taskId);
		tracker.update(task);

		System.out.println("Задача с ID = " + task.getId() + " успешно обновлена.");
	}
}
