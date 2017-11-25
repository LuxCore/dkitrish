package ru.job4j.start;

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
	 * Array of available actions to a user.
	 */
	private UserAction[] actions;

	/**
	 * Range of menu numbers.
	 */
	private int[] menuNumRange;

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
		this.registerActions();
		this.menuNumRange = new int[] {1, 2, 3, 4, 5, 6, 7};
	}

	/**
	 * Add item event.
	 */
	private class AddItemAction implements UserAction {
		/**
		 * Number of menu item.
		 *
		 * @return Number of add item action.
		 */
		public int number() {
			return 1;
		}

		/**
		 * The description of action of task addition.
		 *
		 * @return The description of menu item of item addition.
		 */
		public String description() {
			return "Добавить задачу";
		}

		/**
		 * Performs add item action.
		 *
		 * @param tracker Task tracker.
		 * @param input Type of input.
		 */
		public void execute(Tracker tracker, Input input) {
			System.out.println("----- Создание задачи -----");

			String taskName = input.ask("Введите наименование задачи: ");
			String taskDesc = input.ask("Введите описание задачи: ");

			Item task = new Item(taskName, taskDesc);
			tracker.add(task);

			System.out.println("Задача с ID = " + task.getId() + " успешно создана.");
		}

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
		}
	}

	/**
	 * This action allows a user to delete any task from tracker.
	 */
	private static class DeleteItemAction implements UserAction {
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

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
		}
	}

	/**
	 * Event of search item by ID.
	 */
	private class FindItemByIdAction implements UserAction {
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

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
		}
	}

	/**
	 * Event of search items by name.
	 */
	private class FindItemsByNameAction implements UserAction {
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
			Item[] items = tracker.findByName(taskName);

			System.out.println("Список задач с наименованием " + taskName + ":");
			showTaskList(items);
		}

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
		}
	}

	/**
	 * Event of search all items in tracker.
	 */
	private class FindAllItemsAction implements UserAction {
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

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
		}
	}

	/**
	 * Event of quit the tracker app.
	 */
	private class QuitTrackerAction implements UserAction {
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

		/**
		 * Notifies a user about action is performing.
		 *
		 * @return Information about menu item that available to a user for selection.
		 */
		public String info() {
			return String.format("\t%s: %s", this.number(), this.description());
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
		int numOfActions = 7;
		int numOfAction = 0;
		this.actions = new UserAction[numOfActions];

		this.actions[numOfAction++] = this.new AddItemAction();
		this.actions[numOfAction++] = new UpdateItemAction();
		this.actions[numOfAction++] = new MenuTracker.DeleteItemAction();
		this.actions[numOfAction++] = this.new FindItemByIdAction();
		this.actions[numOfAction++] = this.new FindItemsByNameAction();
		this.actions[numOfAction++] = this.new FindAllItemsAction();
		this.actions[numOfAction++] = this.new QuitTrackerAction();
	}

	/**
	 * Offers to user to select required menu item.
	 * !!! It is necessary to add handling of irregular input from user or
	 * throw the exception.
	 *
	 * @return Number of selected menu item.
	 */
	public int selectActionNum() {
		// String answer = this.input.ask("Выберите подходящий пункт меню (введите соответствующее число): ");
		int answer = this.input.ask("Выберите подходящий пункт меню (введите соответствующее число): ", this.menuNumRange);
		// return Integer.valueOf(answer);
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
		this.actions[--userChoice].execute(this.tracker, this.input);
	}

	/**
	 * Print out all tasks.
	 *
	 * @param items Items of tracker.
	 */
	private void showTaskList(Item[] items) {
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
	public int[] getMenuNumRange() {
		return this.menuNumRange;
	}
}

/**
 * This action allows a user to replace existing information of certain task
 * with new one.
 */
class UpdateItemAction implements UserAction {
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

	/**
	 * Notifies a user about action is performing.
	 *
	 * @return Information about menu item that available to a user for selection.
	 */
	public String info() {
		return String.format("\t%s: %s", this.number(), this.description());
	}
}
