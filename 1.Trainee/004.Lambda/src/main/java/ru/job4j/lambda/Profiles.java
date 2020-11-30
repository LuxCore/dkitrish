package ru.job4j.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Управление анкетами клиентов.
 */
public class Profiles {
	/**
	 * Извлечение адресов из всех анкет клиентов.
	 * @param profiles Анкета клиента турфирмы.
	 * @return список адресов клиентов.
	 */
	public List<Address> collect(List<Profile> profiles) {
		return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
	}
}
