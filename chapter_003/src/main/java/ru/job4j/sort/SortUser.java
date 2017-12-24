package ru.job4j.sort;

import java.util.Collections;
import java.util.Comparator;
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

	/**
	 * Sorts list of users by name length.
	 *
	 * @param users List of users.
	 */
	public void sortByNameLength(List<User> users) {
		Collections.sort(users, new UserComparators.UserNameLengthComparator());
	}

	/**
	 * Sorts list of users by name and age.
	 *
	 * @param users List of users.
	 */
	public void sortByNameAndAge(List<User> users) {
		Comparator<User> comp = new UserComparators.UserNameComparator()
				.thenComparing(new UserComparators.UserAgeComparator());
		Collections.sort(users, comp);
	}
}
