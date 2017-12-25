package ru.job4j.generics;

import java.util.ArrayList;
import java.util.HashMap;
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
		User user1 = new User(1, "John 1", "Doe 1");
		User user2 = new User(2, "John 2", "Doe 2");
		User user3 = new User(3, "John 3", "Doe 3");
		User user4 = new User(4, "John 4", "Doe 4");

		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);

		UserConvert uc = new UserConvert();
		Map<Integer, User> userMap = uc.process(userList);

		Map<Integer, User> expectedMap = new HashMap<>();
		expectedMap.put(1, user1);
		expectedMap.put(2, user2);
		expectedMap.put(3, user3);
		expectedMap.put(4, user4);

		assertThat(userMap, is(expectedMap));
	}
}
