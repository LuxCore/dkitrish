package ru.job4j.sort;

import java.util.Comparator;

/**
 * Comparators for sorting of users by <tt>Collection.sort()</tt>.
 */
public class UserComparators {
	/**
	 * Compares users by name.
	 */
	static class UserNameLengthComparator implements Comparator<User> {
		@Override
		public int compare(User user0, User user1) {
			return user0.getName().length() - user1.getName().length();
		}
	};

	/**
	 * Compares users by name.
	 */
	static class UserNameComparator implements Comparator<User> {
		@Override
		public int compare(User user0, User user1) {
			return user0.getName().compareTo(user1.getName());
		}
	};

	/**
	 * Compares users by age.
	 */
	static class UserAgeComparator implements Comparator<User> {
		@Override
		public int compare(User user0, User user1) {
			return user0.getAge() - user1.getAge();
		}
	};

	/**
	 * Compares users at first by name, at second by age.
	 */
	static class UserNameAgeComparator implements Comparator<User> {
		@Override
		public int compare(User user0, User user1) {
			int result = user0.getName().compareTo(user1.getName());
			if (result == 0) {
				result = user0.getAge() - user1.getAge();
			}
			return result;
		}
	};
}
