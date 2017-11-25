package ru.job4j.start;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.Input;

/**
 * Launch point of Tracker app.
 */
public class StartUI {
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
		new StartUI(new Tracker(), input).init();
	}

	/**
	 * Main cycle for interaction with user and process his input answers.
	 */
	public void init() {
		System.out.println("\n*** СИСТЕМА УЧЁТА ЗАДАЧ ***");
		MenuTracker menu = new MenuTracker(this.tracker, this.input);
		final int EXIT = 7;
		int userChoice = EXIT;

		do {
			menu.show();
			userChoice = menu.selectActionNum();
			menu.performAction(userChoice);
		} while (EXIT != userChoice);
	}
}
