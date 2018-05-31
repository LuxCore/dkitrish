package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

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
		User user2 = new User("Denis", 2, new GregorianCalendar(1983, 12, 15));
		Assert.assertThat(user2.getName(), Is.is("Denis"));
		Assert.assertThat(user2.getBirthday(), Is.is(new GregorianCalendar(1983, 12, 15)));
	}
}
