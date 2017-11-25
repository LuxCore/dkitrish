package ru.job4j.tracker;

import java.util.UUID;
import java.util.Arrays;

/**
 * Task tracker.
 */
public class Tracker {
	/**
	 * List of proposals.
	 */
	private Item[] items;

	/**
	 * Позиция в массиве items, отображающая ячейку, в которую можно добавлять
	 * новые задания.
	 */
	private int position;

	/**
	 * Hidden constructor.
	 */
	public Tracker() {
		this.items = new Item[100];
	}

	/**
	 * Adding of task to the last free position in items array.
	 *
	 * @param item Item to add into array of tasks.
	 *
	 * @return Item Added task.
	 */
	public Item add(Item item) {
		item.setId(this.generateItemId());
		this.items[position++] = item;
		return item;
	}

	/**
	 * Raplace available task with another task.
	 *
	 * @param item New task for replacement of existing one.
	 */
	public void update(Item item) {
		for (int i = 0; i < this.items.length; i++) {
			if (item != null && item.getId().equals(this.items[i].getId())) {
				this.items[i] = item;
				break;
			}
		}
	}

	/**
	 * Deletes one task from items array. After deletion it shifts remaining right part
	 * one position to the left.
	 *
	 * @param item Task for deletion.
	 */
	public void delete(Item item) {
		for (int i = 0; i < this.items.length; i++) {
			if (item != null && item.getId().equals(this.items[i].getId())) {
				System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
				position--;
				break;
			}
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

		for (Item item : this.items) {
			if (id != null && id.equals(item.getId())) {
				task = item;
				break;
			}
		}

		return task;
	}

	/**
	 * Finds all task by name.
	 *
	 * @param key Search name.
	 *
	 * @return Item[] All tasks whose name match to key.
	 */
	public Item[] findByName(String key) {
		// Для начала создадим вспомогательный массив такой же длины,
		// как и основной.
		Item[] foundItemsByName = new Item[position];

		// Счётчик для вспомогательного массива.
		int j = 0;

		for (int i = 0; i < this.items.length; i++) {
			// Т.к. все задания располагаются относительно начала массива,
			// то нет смысла искать по всему массиву, т.е. прервём цикл
			// на первом пустом элементе.
			if (this.items[i] == null) {
				break;
			}

			if (key != null && key.equals(this.items[i].getName())) {
				foundItemsByName[j++] = this.items[i];
			}
		}

		if (foundItemsByName != null) {
			foundItemsByName = Arrays.copyOf(foundItemsByName, j);
		} else {
			foundItemsByName = null;
		}

		return foundItemsByName;
	}

	/**
	 * Finds all non-nullable tasks.
	 *
	 * @return Item[] Array of all non-nullable tasks.
	 */
	public Item[] findAll() {
		Item[] allItems = null;

		for (int i = 0; i < this.items.length; i++) {
			// Все задания расположены относительно начала массива,
			// поэтому можем проходить все элементы сначала до первого пустого.
			// Как только доходим до пустого елемента в массиве, то сразу
			// копируем все задания в allItems.
			if (this.items[i] == null) {
				allItems = new Item[i];
				System.arraycopy(this.items, 0, allItems, 0, i);
				break;
			}
		}

		return allItems;
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
