package ru.job4j.generics;

import org.junit.Test;
import ru.job4j.generics.store.Role;
import ru.job4j.generics.store.RoleStore;
import ru.job4j.generics.store.Store;
import ru.job4j.generics.store.User;
import ru.job4j.generics.store.UserStore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Тестирование хранилища объектов.
 */
public class StoreTest {
	/**
	 * Тест добавления пользователей в хранилище пользователей.
	 */
	@Test
	public void testUserStoreAddition() {
		Store<User> userStore = new UserStore();
		User user1 = new User("user1");
		User user2 = new User("user2");
		userStore.add(user1);
		userStore.add(user2);
		assertThat(userStore.findById("user1"), is(new User("user1")));
		assertThat(userStore.findById("user2").getId(), is("user2"));
	}

	/**
	 * Тест замены пользователей в хранилище пользователей.
	 */
	@Test
	public void testUserStoreReplace() {
		Store<User> userStore = new UserStore();
		User user1 = new User("user1");
		User user2 = new User("user2");
		userStore.add(user1);
		userStore.add(user2);
		userStore.replace("user1", new User("user3"));
		assertNull(userStore.findById("user1"));
		assertThat(userStore.findById("user3").getId(), is("user3"));

		boolean actual = userStore.replace("user4", new User("user5"));
		assertThat(actual, is(false));
	}

	/**
	 * Тест удаления пользователей из хранилища пользователей.
	 */
	@Test
	public void testUserStoreDeletion() {
		Store<User> userStore = new UserStore();
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");
		User user4 = new User("user4");
		userStore.add(user1);
		userStore.add(user2);
		userStore.add(user3);
		userStore.add(user4);
		userStore.delete("user1");
		userStore.delete("user3");
		assertNull(userStore.findById("user1"));
		assertThat(userStore.findById("user2").getId(), is("user2"));
		assertNull(userStore.findById("user3"));
		assertThat(userStore.findById("user4").getId(), is("user4"));
		assertThat(userStore.delete("user69"), is(false));
	}

	/**
	 * Тест добавления ролей в хранилище ролей.
	 */
	@Test
	public void testRoleStoreAddition() {
		Store<Role> roleStore = new RoleStore();
		Role role1 = new Role("role1");
		Role role2 = new Role("role2");
		roleStore.add(role1);
		roleStore.add(role2);
		assertThat(roleStore.findById("role1"), is(new Role("role1")));
		assertThat(roleStore.findById("role2").getId(), is("role2"));
	}

	/**
	 * Тест замены ролей в хранилище ролей.
	 */
	@Test
	public void testRoleStoreReplace() {
		Store<Role> roleStore = new RoleStore();
		Role role1 = new Role("role1");
		Role role2 = new Role("role2");
		roleStore.add(role1);
		roleStore.add(role2);
		roleStore.replace("role1", new Role("role3"));
		assertNull(roleStore.findById("role1"));
		assertThat(roleStore.findById("role3").getId(), is("role3"));

		boolean actual = roleStore.replace("role4", new Role("role5"));
		assertThat(actual, is(false));
	}

	/**
	 * Тест удаления ролей из хранилище ролей.
	 */
	@Test
	public void testRoleStoreDeletion() {
		Store<Role> roleStore = new RoleStore();
		Role role1 = new Role("role1");
		Role role2 = new Role("role2");
		Role role3 = new Role("role3");
		Role role4 = new Role("role4");
		roleStore.add(role1);
		roleStore.add(role2);
		roleStore.add(role3);
		roleStore.add(role4);
		roleStore.delete("role1");
		roleStore.delete("role3");
		assertNull(roleStore.findById("role1"));
		assertThat(roleStore.findById("role2").getId(), is("role2"));
		assertNull(roleStore.findById("role3"));
		assertThat(roleStore.findById("role4").getId(), is("role4"));
		assertThat(roleStore.delete("role69"), is(false));
	}
}
