package ru.job4j.start;

/**
 * Abstract Action implements general for all actions the info() method.
 */
public abstract class BaseAction implements UserAction {
	/**
	 * Constructor based on number and description.
	 *
	 * @param number Number of menu item.
	 * @param desc Description of menu item.
	 */
	public BaseAction(int number, String desc) {
	}

	/**
	 * Notifies a user about action is performing.
	 *
	 * @return Information about menu item that available to a user for selection.
	 */
	@Override
	public String info() {
		return String.format("\t%s: %s", this.number(), this.description());
	}
}
