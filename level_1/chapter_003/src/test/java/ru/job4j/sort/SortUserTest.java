package ru.job4j.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test sorting.
 */
public class SortUserTest {
	/**
	 * Instance of SortUser.
	 */
	private SortUser sortUser = new SortUser();

	/**
	 * Test of sorting using implementation of Comparable.
	 */
	@Test
	public void testSortUsingComparable() {
		User user1 = new User("John 0", 61);
		User user2 = new User("John 1", 30);
		User user3 = new User("John 2", 26);
		User user4 = new User("John 2", 44);
		User user5 = new User("John 2", 50);
		User user6 = new User("John 2", 54);
		User user7 = new User("John 3", 10);
		User user8 = new User("John 3", 21);
		User user9 = new User("John 3", 38);
		User user10 = new User("John 3", 54);

		List<User> userList = new LinkedList<>();
		userList.add(user6);
		userList.add(user10);
		userList.add(user4);
		userList.add(user5);
		userList.add(user8);
		userList.add(user2);
		userList.add(user3);
		userList.add(user9);
		userList.add(user7);
		userList.add(user1);

		Set<User> userSet = this.sortUser.process(userList);

		Set<User> expected = new TreeSet();
		expected.add(user1);
		expected.add(user2);
		expected.add(user3);
		expected.add(user4);
		expected.add(user5);
		expected.add(user6);
		expected.add(user7);
		expected.add(user8);
		expected.add(user9);
		expected.add(user10);

		assertThat(userSet, is(expected));
	}

	/**
	 * Tests sorting of List of users by name length.
	 */
	@Test
	public void testSortByNameLength() {
		User user1 = new User("Christian 1", 54);
		User user2 = new User("Christian 22", 44);
		User user3 = new User("Christian 22", 38);
		User user4 = new User("Christian 222", 10);
		User user5 = new User("Christian 1111", 60);
		User user6 = new User("Christian 1111", 54);
		User user7 = new User("Christian 123456", 54);
		User user8 = new User("Christian 123456", 15);
		User user9 = new User("Christian 123456", 26);

		List<User> userList = new ArrayList<>();
		userList.add(user7);
		userList.add(user5);
		userList.add(user1);
		userList.add(user6);
		userList.add(user8);
		userList.add(user2);
		userList.add(user4);
		userList.add(user9);
		userList.add(user3);

		this.sortUser.sortByNameLength(userList);

		List<User> expected = new ArrayList<>();
		expected.add(user1);
		expected.add(user2);
		expected.add(user3);
		expected.add(user4);
		expected.add(user5);
		expected.add(user6);
		expected.add(user7);
		expected.add(user8);
		expected.add(user9);

		assertThat(userList, is(expected));
	}

	/**
	 * Tests sorting of List of users by name and age.
	 */
	@Test
	public void testSortByNameAndAgeUsingOneComparator() {
		User user1 = new User("Christian 1", 54);
		User user2 = new User("Christian 1111", 54);
		User user3 = new User("Christian 1111", 60);
		User user4 = new User("Christian 123456", 15);
		User user5 = new User("Christian 123456", 26);
		User user6 = new User("Christian 123456", 54);
		User user7 = new User("Christian 22", 38);
		User user8 = new User("Christian 22", 44);
		User user9 = new User("Christian 222", 10);

		List<User> userList = new ArrayList<>();
		userList.add(user6);
		userList.add(user3);
		userList.add(user1);
		userList.add(user2);
		userList.add(user4);
		userList.add(user8);
		userList.add(user9);
		userList.add(user5);
		userList.add(user7);

		this.sortUser.sortByNameAndAgeUsingOneComparator(userList);

		List<User> expected = new ArrayList<>();
		expected.add(user1);
		expected.add(user2);
		expected.add(user3);
		expected.add(user4);
		expected.add(user5);
		expected.add(user6);
		expected.add(user7);
		expected.add(user8);
		expected.add(user9);

		assertThat(userList, is(expected));
	}

	/**
	 * Tests sorting of List of users by name and age.
	 */
	@Test
	public void testSortByNameAndAgeUsingTwoComparators() {
		User user1 = new User("Christian 1", 54);
		User user2 = new User("Christian 1111", 54);
		User user3 = new User("Christian 1111", 60);
		User user4 = new User("Christian 123456", 15);
		User user5 = new User("Christian 123456", 26);
		User user6 = new User("Christian 123456", 54);
		User user7 = new User("Christian 22", 38);
		User user8 = new User("Christian 22", 44);
		User user9 = new User("Christian 222", 10);

		List<User> userList = new ArrayList<>();
		userList.add(user6);
		userList.add(user3);
		userList.add(user1);
		userList.add(user2);
		userList.add(user4);
		userList.add(user8);
		userList.add(user9);
		userList.add(user5);
		userList.add(user7);

		this.sortUser.sortByNameAndAgeUsingTwoComparators(userList);

		List<User> expected = new ArrayList<>();
		expected.add(user1);
		expected.add(user2);
		expected.add(user3);
		expected.add(user4);
		expected.add(user5);
		expected.add(user6);
		expected.add(user7);
		expected.add(user8);
		expected.add(user9);

		assertThat(userList, is(expected));
	}
}
