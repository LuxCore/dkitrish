package ru.job4j.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Управление анкетами клиентов.
 */
public class Profiles {
	/**
	 * Извлечение адресов из всех анкет клиентов с подавлением
	 * дублирующихся адресов.
	 * @param profiles Анкета клиента турфирмы.
	 * @return список адресов клиентов.
	 */
	public List<Address> collect(List<Profile> profiles) {
		return profiles.stream()
				.sorted((o1, o2) -> o1.getAddress().getCity().compareTo(
						o2.getAddress().getCity()
				))
				.map(Profile::getAddress)
				.distinct()
				.collect(Collectors.toList());
	}
}
