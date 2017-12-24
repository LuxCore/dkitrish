package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests conversion of List&lt;User&gt; to Map&lt;User&gt;.
 */
public class UserConvertTest {
	/**
	 * Tests conversion List to Map.
	 */
	@Test
	public void testConversionListToMap() {
		UserConvert uc = new UserConvert();
		List<User> userList = new ArrayList<>();
		fillUserList(userList, 10);
		Map<Integer, User> userMap = uc.process(userList);
		String result = userMap.toString();
		String expected = "{1=John 4, 2=John 6, 3=John 8, 4=John 10, 5=John 12, 6=John 14, "
				+ "7=John 16, 8=John 18, 9=John 20, 10=John 22}";
		assertThat(result, is(expected));
	}

	/**
	 * Auxiliary method to fill the List of users.
	 *
	 * @param list List of users.
	 * @param amount Amount of list elements.
	 */
	private static void fillUserList(List<User> list, int amount) {
		User user = null;
		Integer id = list.size() + 1;

		for (int i = 0; i < amount; i++) {
			user = new User(id, "John " + ((id + 1) * 2), "Doe " + ((id + 1) * 2));
			list.add(user);
			id++;
		}
	}
}
