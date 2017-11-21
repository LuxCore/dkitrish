package ru.job4j.professions;

import java.time.Period;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем учительские способности учителя.
 *
 * @author Denis.Kitrish (Denis.Kitrish@Yandex.ua)
 * @since 05.11.2017
 * @version 1.0
 */
public class TeacherTest {
	/**
	 * Имя учителя.
	 */
	private Name sensei = new Name("Сэнсей");

	/**
	 * Китайское образование.
	 */
	private Education chinaEdu = new Education("Вин-чунь", "Чёрный");

	/**
	 * Период практики / стаж.
	 */
	private Period manyYears = Period.ofYears(5);

	/**
	 * Degree of person.
	 */
	private Degree degree = new Degree("Заслуженный мастер вин-чунь");

	/**
	 * Subject person should learn.
	 */
	private String subject = "Wing-Chun";

	/**
	 * Профессия учителя.
	 */
	private Teacher teacherSensei = new Teacher(sensei, chinaEdu, manyYears, degree, subject);

	/**
	 * Student Bruce Lee.
	 */
	private Student bruceLee = new Student(new Name("Bruce", "Lee"));

	/**
	 * Сенсей пытается объяснить тему урока.
	 */
	@Test
	public void testExplainTopic() {
		Topic topic = new Topic("Блоки + контрудары");
		String result = teacherSensei.explainTopic(topic);
		String expected = "Учитель Сэнсей объясняет тему \"Блоки + контрудары\"";

		assertThat(result, is(expected));
	}
}
