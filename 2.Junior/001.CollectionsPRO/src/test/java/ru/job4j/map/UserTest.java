package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
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
		Assert.assertThat(users.size(), Is.is(1));
		for (User user : users.keySet()) {
			System.out.println(user.hashCode());
		}
		System.out.println(user1.equals(user2));
	}

	/**
	 * Тест сравнения двух {@code User} методом {@code compareTo},
	 * когда оба {@code User} равны.
	 */
	@Test
	public void testCompareToWhenUsersEqual() {
		User user1 = new User("Denis", 2,
				new GregorianCalendar(1983, Calendar.DECEMBER, 15));
		User user2 = new User("Denis", 2,
				new GregorianCalendar(1983, Calendar.DECEMBER, 15));
		User user3 = new User("Denis", 2,
				new GregorianCalendar(1983, Calendar.DECEMBER, 15));
		Assert.assertThat(user1.compareTo(user2), Is.is(0));
		Assert.assertThat(user2.compareTo(user3), Is.is(0));
		Assert.assertThat(user1.compareTo(user3), Is.is(0));
	}

	/**
	 * Тест сравнения двух {@code User} методом {@code compareTo},
	 * когда имена двух {@code User} не равны.
	 */
	@Test
	public void testCompareToWhenUsersNamesNotEqual() {
		User user1 = new User("User1", 1,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		User user2 = new User("User2", 1,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		Assert.assertThat(user1.compareTo(user2), Is.is(-1));
		Assert.assertThat(user2.compareTo(user1), Is.is(1));
	}

	/**
	 * Тест сравнения двух {@code User} методом {@code compareTo},
	 * когда количество детей двух {@code User} не равны.
	 */
	@Test
	public void testCompareToWhenUsersChildrenNotEqual() {
		User user1 = new User("User1", 5,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		User user2 = new User("User1", 3,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		Assert.assertThat(user1.compareTo(user2), Is.is(1));
		Assert.assertThat(user2.compareTo(user1), Is.is(-1));
	}

	/**
	 * Тест сравнения двух {@code User} методом {@code compareTo},
	 * когда дни рождений двух {@code User} не равны.
	 */
	@Test
	public void testCompareToWhenUsersBirthdaysNotEqual() {
		User user1 = new User("User1", 0,
				new GregorianCalendar(1975, Calendar.JANUARY, 1));
		User user2 = new User("User1", 0,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		Assert.assertThat(user1.compareTo(user2), Is.is(1));
		Assert.assertThat(user2.compareTo(user1), Is.is(-1));

		user1 = new User("User1", 0,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		user2 = new User("User1", 0,
				new GregorianCalendar(1970, Calendar.FEBRUARY, 1));
		Assert.assertThat(user1.compareTo(user2), Is.is(-1));
		Assert.assertThat(user2.compareTo(user1), Is.is(1));

		user1 = new User("User1", 0,
				new GregorianCalendar(1970, Calendar.JANUARY, 2));
		user2 = new User("User1", 0,
				new GregorianCalendar(1970, Calendar.JANUARY, 1));
		Assert.assertThat(user1.compareTo(user2), Is.is(1));
		Assert.assertThat(user2.compareTo(user1), Is.is(-1));
	}
}
