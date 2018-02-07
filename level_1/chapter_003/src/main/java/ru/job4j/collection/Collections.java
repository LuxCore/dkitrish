package ru.job4j.collection;

import java.time.Instant;
import java.time.Duration;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.UUID;

/**
 * Class that helps to understand which collection useful for.
 */
public class Collections {
	/**
	 * Adds an amount of elements into collection.
	 *
	 * @param collection Into this collection will be added an amount of elements.
	 * @param amount Amount of elements will be added into <tt>collection</tt>.
	 *
	 * @return Time spent on adding of elements into collection.
	 */
	public long addValues(Collection<String> collection, int amount) {
		Instant start = Instant.now();

		for (int i = 0; i < amount; i++) {
			collection.add(UUID.randomUUID().toString());
		}

		Instant end = Instant.now();
		long performingTime = Duration.between(start, end).toNanos();
		return performingTime;
	}

	/**
	 * Deletes an amount of elements from collection.
	 *
	 * @param collection From this collection will be deleted an amount of elements.
	 * @param amount Amount of elements will be deleted from <tt>collection</tt>.
	 *
	 * @return Time spent on deleting of elements from collection.
	 */
	public long deleteValues(Collection<String> collection, int amount) {
		Instant start = Instant.now();

		for (int i = 0; i < amount; i++) {
			collection.remove(collection.iterator().next());
		}

		Instant end = Instant.now();
		long performingTime = Duration.between(start, end).toNanos();
		return performingTime;
	}

	/**
	 * Main entry point into program.
	 *
	 * @param args Arguments for execution.
	 */
	public static void main(String[] args) {
		Collections coll = new Collections();
		int addAmount = 1_200_000;
		int delAmount = 2_000;

		ArrayList<String> al = new ArrayList<>();
		String collStr = "ArrayList<String>";

		System.out.println(collStr);

		long performingTime = coll.addValues(al, addAmount);
		System.out.printf("\tВремя заполнения %s (%d элементов) = %d ms\n", collStr, addAmount, performingTime);

		performingTime = coll.deleteValues(al, delAmount);
		System.out.printf("\tВремя удаления из %s (%d элементов) = %d ms\n", collStr, delAmount, performingTime);

		LinkedList<String> ll = new LinkedList<>();
		collStr = "LinkedList<String>";
		System.out.println(collStr);

		performingTime = coll.addValues(ll, addAmount);
		System.out.printf("\tВремя заполнения %s (%d элементов) = %d ms\n", collStr, addAmount, performingTime);

		performingTime = coll.deleteValues(ll, delAmount);
		System.out.printf("\tВремя удаления из %s (%d элементов) = %d ms\n", collStr, delAmount, performingTime);

		TreeSet<String> ts = new TreeSet<>();
		collStr = "TreeSet<String>";
		System.out.println(collStr);

		performingTime = coll.addValues(ts, addAmount);
		System.out.printf("\tВремя заполнения %s (%d элементов) = %d ms\n", collStr, addAmount, performingTime);

		performingTime = coll.deleteValues(ts, delAmount);
		System.out.printf("\tВремя удаления из %s (%d элементов) = %d ms\n", collStr, delAmount, performingTime);
	}
}
