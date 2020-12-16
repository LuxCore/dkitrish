package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса PhoneDictionary.
 */
public class PhoneDictionaryTest {
	/**
	 * Тест поиска личности по имени.
	 */
	@Test
	public void testFindByName() {
		var phones = new PhoneDictionary();
		phones.add(
				new Person("Petr", "Arsentev", "123", "Bryansk")
		);
		phones.add(
				new Person("Ivan", "Petrovich", "456", "Moscow")
		);
		phones.add(
				new Person("John", "Arsentev", "qwe", "Bryansk")
		);
		phones.add(
				new Person("Jane", "Arsentev", "789", "Bryansk")
		);
		var persons = phones.find("John");
		assertThat(persons.get(0).getAddress(), is("Bryansk"));
	}

	/**
	 * Тест поиска личности по фамилии.
	 */
	@Test
	public void testFindBySurname() {
		var phones = new PhoneDictionary();
		phones.add(
				new Person("Petr", "Arsentev", "123", "Bryansk")
		);
		phones.add(
				new Person("Ivan", "Petrovich", "456", "Moscow")
		);
		phones.add(
				new Person("John", "Doe", "qwe", "Saskatoon")
		);
		phones.add(
				new Person("Jane", "Doe", "789", "Bryansk")
		);
		var persons = phones.find("Doe");
		assertThat(persons.get(0).getPhone(), is("qwe"));
	}

	/**
	 * Тест поиска личности по телефону.
	 */
	@Test
	public void testFindByPhone() {
		var phones = new PhoneDictionary();
		phones.add(
				new Person("Petr", "Arsentev", "123", "Bryansk")
		);
		phones.add(
				new Person("Ivan", "Petrovich", "456", "Moscow")
		);
		phones.add(
				new Person("John", "Doe", "qwe", "Saskatoon")
		);
		phones.add(
				new Person("Jane", "Doe", "789", "Bryansk")
		);
		var persons = phones.find("456");
		assertThat(persons.get(0).getName(), is("Ivan"));
	}

	/**
	 * Тест поиска личности по городу.
	 */
	@Test
	public void testFindByAddress() {
		var phones = new PhoneDictionary();
		phones.add(
				new Person("Petr", "Arsentev", "123", "Bryansk")
		);
		phones.add(
				new Person("Ivan", "Petrovich", "456", "Moscow")
		);
		phones.add(
				new Person("John", "Doe", "qwe", "Saskatoon")
		);
		phones.add(
				new Person("Jane", "Doe", "789", "Bryansk")
		);
		var persons = phones.find("Saska");
		assertThat(persons.get(0).getName() + " "
				+ persons.get(0).getSurname(), is("John Doe"));
	}
}
