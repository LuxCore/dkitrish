package ru.job4j.professions;

import java.time.Period;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем умения инженера.
 *
 * @author Denis.Kitrish (Denis.Kitrish@Yandex.ua)
 * @since 05.11.2017
 * @version 1.0
 */
public class EngineerTest {
	/**
	 * Имя доктора.
	 */
	private Name ivan = new Name("Иван");

	/**
	 * Внеземное образование доктора Стрейнджа.
	 */
	private Education highEdu = new Education("Информационные технологии", "Красный");

	/**
	 * Период практики / стаж.
	 */
	private Period twoYears = Period.ofYears(2);

	/**
	 * Категория.
	 */
	private Category category = new Category(2);

	/**
	 * Профессия инженера.
	 */
	private Engineer engineerIvan = new Engineer(ivan, highEdu, twoYears, category);

	/**
	 * Проводим тест Ивану на повышение категории.
	 */
	@Test
	public void testIncreaseCategory() {
		Category category = new Category(1);
		String result = engineerIvan.increaseCategory(category);
		String expected = "Инженер Иван выполняет задание для повышения "
				+ "категории с 2 на 1";

		assertThat(result, is(expected));
	}
}
