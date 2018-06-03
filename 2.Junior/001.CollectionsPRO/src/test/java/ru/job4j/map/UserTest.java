package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Для изучения поведения Map.
 */
public class UserTest {
	/**
	 * Тест конструкторов.
	 */
	@Test
	public void testUserConstructor() {
		User user1 = new User();
		Assert.assertNull(user1.getName());
		User user2 = new User("Denis", 2, new GregorianCalendar(1983, 11, 15));
		Assert.assertThat(user2.getName(), Is.is("Denis"));
		Assert.assertThat(user2.getBirthday(), Is.is(new GregorianCalendar(1983, 11, 15)));
	}

	/**
	 * Добавление пользователя в HashMap в качестве ключа.
	 */
	@Test
	public void testPuttingUsersIntoHashMap() {
		Map<User, Object> users = new HashMap<>();
		User user1 = new User("Denis", 2, new GregorianCalendar(1983, 11, 15));
		user1.setNonBusinessField(69);
		User user2 = new User("Denis", 2, new GregorianCalendar(1983, 11, 15));
		user2.setNonBusinessField(96);
		users.put(user1, new Object());
		System.out.println(users);
		users.put(user2, new Object());
		System.out.println(users);
		Assert.assertThat(users.size(), Is.is(2));
		for (User user : users.keySet()) {
			System.out.println(user.hashCode());
		}
		System.out.println(user1.equals(user2));
	}
}
