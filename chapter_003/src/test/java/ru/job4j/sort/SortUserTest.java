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
	 * Test of sorting using implementation of Comparable.
	 */
	@Test
	public void testSortUsingComparable() {
		List<User> userList = new ArrayList<>();
		fillUserList(userList, 10);
		SortUser su = new SortUser();
		Set<User> userSet = su.process(userList);
		String result = userSet.toString();
		String expected = "[{John 0; age=61}, {John 1; age=30}, {John 2; age=26}, "
				+ "{John 2; age=44}, {John 2; age=50}, {John 2; age=54}, {John 3; age=10}, "
				+ "{John 3; age=21}, {John 3; age=38}, {John 3; age=54}]";
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
