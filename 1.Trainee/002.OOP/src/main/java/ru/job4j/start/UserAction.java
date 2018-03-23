package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

/**
 * Maps a selection of menu item by user.
 */
public interface UserAction {
	/**
	 * Number of action user have to select.
	 *
	 * @return Number of menu item.
	 */
	int number();

	/**
	 * Description of an action which user selects.
	 *
	 * @return Description of menu item.
	 */
	String description();

	/**
	 * Performs an action which was chosen by user.
	 * !!! Actually parameters are included here to study
	 * outer class which is in the same file where MenuTracker is.
	 * In future we can delete these parameters everywhere in descendants.
	 *
	 * @param tracker Task tracker.
	 * @param input Type of input.
	 */
	void execute(Tracker tracker, Input input);

	/**
	 * Prints out information about performed action.
	 *
	 * @return Show description of action that will be done.
	 */
	String info();
}
