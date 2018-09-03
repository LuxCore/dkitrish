package ru.job4j.monitorsync;

import org.junit.Test;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты-заглушки для UserStorage.
 */
public class UserStorageTest {
	/**
	 * Тест обновления информации о пользователе.
	 */
	@Test
	public void testUpdate() {
		UserStorage storage = new UserStorage();
		User user1 = new User(1, 111);
		storage.add(user1);
		assertThat(storage.update(new User(1, 555)), is(true));
		assertThat(storage.update(new User(4, 444)), is(false));
		storage.update(new User(3, 777));
		assertThat(storage.getUserById(1).getAmount(), not(111));
	}

	/**
	 * Тест удаления пользователя из системы.
	 */
	@Test
	public void testDelete() {
		UserStorage storage = new UserStorage();
		storage.add(new User(1, 111));
		storage.add(new User(2, 222));
		assertThat(storage.delete(new User(2, 0)), is(true));
		assertThat(storage.delete(new User(4, 0)), is(false));
	}

	/**
	 * Тест денежного перевода от одного пользователя другому.
	 */
	@Test
	public void testTransfer() {
		UserStorage storage = new UserStorage();
		storage.add(new User(1, 111));
		storage.add(new User(2, 222));
		assertThat(storage.transfer(1, 2, 11), is(true));
		assertThat(storage.transfer(2, 1, 244), is(false));
		assertThat(storage.transfer(3, 1, 555), is(false));
	}
}