package ru.job4j.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
		List<User> userList = new ArrayList<>();
		fillUserList(userList, 10);
		Set<User> userSet = this.sortUser.process(userList);
		String result = userSet.toString();
		String expected = "[{John 0; age=61}, {John 1; age=30}, {John 2; age=26}, "
				+ "{John 2; age=44}, {John 2; age=50}, {John 2; age=54}, {John 3; age=10}, "
				+ "{John 3; age=21}, {John 3; age=38}, {John 3; age=54}]";
		assertThat(result, is(expected));
	}

	/**
	 * Tests sorting of List of users by name length.
	 */
	@Test
	public void testSortByNameLength() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("Christian 123456", 54));
		userList.add(new User("Christian 1111", 60));
		userList.add(new User("Christian 1", 54));
		userList.add(new User("Christian 1111", 54));
		userList.add(new User("Christian 123456", 15));
		userList.add(new User("Christian 22", 44));
		userList.add(new User("Christian 222", 10));
		userList.add(new User("Christian 123456", 26));
		userList.add(new User("Christian 22", 38));

		this.sortUser.sortByNameLength(userList);
		String result = userList.toString();
		String expected = "[{Christian 1; age=54}, {Christian 22; age=44}, "
				+ "{Christian 22; age=38}, {Christian 222; age=10}, {Christian 1111; age=60}, "
				+ "{Christian 1111; age=54}, {Christian 123456; age=54}, "
				+ "{Christian 123456; age=15}, {Christian 123456; age=26}]";
		assertThat(result, is(expected));
	}

	/**
	 * Tests sorting of List of users by name and age.
	 */
	@Test
	public void testSortByNameAndAge() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("Christian 123456", 54));
		userList.add(new User("Christian 1111", 60));
		userList.add(new User("Christian 1", 54));
		userList.add(new User("Christian 1111", 54));
		userList.add(new User("Christian 123456", 15));
		userList.add(new User("Christian 22", 44));
		userList.add(new User("Christian 222", 10));
		userList.add(new User("Christian 123456", 26));
		userList.add(new User("Christian 22", 38));

		this.sortUser.sortByNameAndAge(userList);
		String result = userList.toString();
		String expected = "[{Christian 1; age=54}, {Christian 1111; age=54}, "
				+ "{Christian 1111; age=60}, {Christian 123456; age=15}, "
				+ "{Christian 123456; age=26}, {Christian 123456; age=54}, {Christian 22; age=38}, "
				+ "{Christian 22; age=44}, {Christian 222; age=10}]";
		assertThat(result, is(expected));
	}

	/**
	 * Auxiliary method to fill users' list.
	 *
	 * @param list List of users.
	 * @param amount Amount of list elements.
	 */
	private static void fillUserList(List<User> list, int amount) {
		Random rnd = new Random(69);
		User user = null;

		for (int i = 0; i < amount; i++) {
			user = new User("John " + rnd.nextInt(4), rnd.nextInt(69));
			list.add(user);
		}
	}
}
