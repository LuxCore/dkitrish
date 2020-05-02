package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Телефонный справочник.
 */
public class PhoneDictionary {
	/**
	 * Список найденных личностей в справочнике.
	 */
	private ArrayList<Person> persons = new ArrayList<>();

	/**
	 * Добавление личности в справочник.
	 *
	 * @param person Личность.
	 */
	public void add(Person person) {
		persons.add(person);
	}

	/**
	 * Поиск личности по искомому значению.
	 *
	 * @param searchValue Искомое значение, по которому необходимо найти личность в справочнике.
	 * @return Личность.
	 */
	public ArrayList<Person> find(String searchValue) {
		ArrayList<Person> result = new ArrayList<>();

		Predicate<Person> findKeyInName = person -> person.getName().contains(searchValue);
		Predicate<Person> findKeyInSurname = person -> person.getSurname().contains(searchValue);
		Predicate<Person> findKeyInAddress = person -> person.getAddress().contains(searchValue);
		Predicate<Person> findKeyInPhone = person -> person.getPhone().contains(searchValue);
		Predicate<Person> findKeyInAllProps = findKeyInName.or(
				findKeyInSurname.or(findKeyInAddress.or(findKeyInPhone)));

		persons.forEach(person -> {
			if (findKeyInAllProps.test(person)) {
				result.add(person);
			}
		});

		return result;
	}
}
