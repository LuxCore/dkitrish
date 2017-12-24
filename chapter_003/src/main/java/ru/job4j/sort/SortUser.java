package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementing of user sorting.
 */
public class SortUser {
	/**
	 * Convert List of users to sorted Set of users.
	 *
	 * @param users List of users.
	 *
	 * @return Sorted set of users.
	 */
	public Set<User> process(List<User> users) {
		return new TreeSet<>(users);
	}
}
