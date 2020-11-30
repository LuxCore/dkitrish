package ru.job4j.lambda;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Проверка извлечения адресов клиентов.
 */
public class ProfilesTest {
	/**
	 * Проверка работы извлечения адресов клиентов.
	 */
	@Test
	public void testCollect() {
		List<Profile> profiles = List.of(
				new Profile(new Address("City 1", "Street 1", 1, 1)),
				new Profile(new Address("City 2", "Street 2", 2, 2)),
				new Profile(new Address("City 3", "Street 3", 3, 3)),
				new Profile(new Address("City 4", "Street 4", 4, 4))
		);
		List<Address> expected = List.of(
				new Address("City 1", "Street 1", 1, 1),
				new Address("City 2", "Street 2", 2, 2),
				new Address("City 3", "Street 3", 3, 3),
				new Address("City 4", "Street 4", 4, 4)
		);
		assertEquals(expected, new Profiles().collect(profiles));
	}

	@Test
	public void testCollectWhenSuppressDuplicates() {
		List<Profile> profiles = List.of(
				new Profile(new Address("City 3", "Street 3", 3, 3)),
				new Profile(new Address("City 4", "Street 4", 4, 4)),
				new Profile(new Address("City 2", "Street 2", 2, 2)),
				new Profile(new Address("City 3", "Street 3", 3, 3)),
				new Profile(new Address("City 4", "Street 4", 4, 4)),
				new Profile(new Address("City 1", "Street 1", 1, 1)),
				new Profile(new Address("City 4", "Street 4", 4, 4)),
				new Profile(new Address("City 3", "Street 3", 3, 3)),
				new Profile(new Address("City 2", "Street 2", 2, 2)),
				new Profile(new Address("City 4", "Street 4", 4, 4))
		);
		List<Address> expected = List.of(
				new Address("City 1", "Street 1", 1, 1),
				new Address("City 2", "Street 2", 2, 2),
				new Address("City 3", "Street 3", 3, 3),
				new Address("City 4", "Street 4", 4, 4)
		);
		assertEquals(expected, new Profiles().collect(profiles));
	}
}