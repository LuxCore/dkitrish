package ru.job4j.tracker;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

/**
 * Task tracker.
 */
public class Tracker {
	/**
	 * List of proposals.
	 */
	private List<Item> items;

	/**
	 * Hidden constructor.
	 */
	public Tracker() {
		this.items = new ArrayList<>(100);
	}

	/**
	 * Adding of task to the last free position in <tt>items</tt>.
	 *
	 * @param item Item to add into array of tasks.
	 *
	 * @return Added task.
	 */
	public Item add(Item item) {
		item.setId(this.generateItemId());
		this.items.add(item);
		return item;
	}

	/**
	 * Raplace available task with another task.
	 *
	 * @param item New task for replacement of existing one.
	 */
	public void update(Item item) {
		if (item != null) {
			for (int i = 0; i < this.items.size(); i++) {
				if (item.getId().equals(this.items.get(i).getId())) {
					this.items.set(i, item);
				}
			}
		}
	}

	/**
	 * Deletes one task from <tt>items</tt>.
	 *
	 * @param item Task for deletion.
	 */
	public void delete(Item item) {
		if (item != null) {
			this.items.remove(item);
		}
	}

	/**
	 * Finds a task by its id.
	 *
	 * @param id ID of task.
	 *
	 * @return Item Found task by its id.
	 */
	public Item findById(String id) {
		Item task = null;

		if (id != null) {
			for (Item item : this.items) {
				if (id.equals(item.getId())) {
					task = item;
					break;
				}
			}
		}

		return task;
	}

	/**
	 * Finds all task by name.
	 *
	 * @param key Search name.
	 *
	 * @return list of all tasks whose name match to key.
	 */
	 public List<Item> findByName(String key) {
 		List<Item> foundItemsByName = new ArrayList<>();

 		if (key != null && !key.isEmpty()) {
 			for (Item item : this.items) {
 				if (key.equals(item.getName())) {
 					foundItemsByName.add(item);
 				}
 			}
 		}

 		return foundItemsByName;
 	}

	/**
	 * Finds all tasks.
	 *
	 * @return List of all tasks.
	 */
	public List<Item> findAll() {
		return this.items;
	}

	/**
	 * Generator of id for tasks. It generates ids using free position index
	 * of array of tasks.
	 *
	 * @return String ID for new task.
	 */
	private String generateItemId() {
		return UUID.randomUUID().toString();
	}
}
