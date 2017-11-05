package ru.job4j.professions;

import java.time.Period;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем доктора на его возможности.
 *
 * @author Denis.Kitrish (Denis.Kitrish@Yandex.ua)
 * @since 05.11.2017
 * @version 1.0
 */
public class DoctorTest {
	// Имя доктора.
	Name strange = new Name("Strange");

	// Внеземное образование доктора Стрейнджа.
	Education extraterrestrialEdu = new Education("Астрал", "Красный");

	// Период практики / стаж.
	Period oneMonth = Period.ofMonths(1);

	// Профессия доктора.
	Doctor doctorStrange = new Doctor(strange, extraterrestrialEdu, oneMonth);

	Patient xanderCage = new Patient(new Name("Ксандер", "Кейдж"));

	/**
	 * Тестируем осмотр пацента.
	 */
	@Test
	public void testInspectionOfDoctor() {
		String result = doctorStrange.inspect(xanderCage);
		String expected = "Доктор Strange осматривает пациента Ксандер";

		assertThat(result, is(expected));
	}

	/**
	 * Посмотрим, как доктор Стрейндж ставит диагноз.
	 */
	@Test
	public void testMakeDiagnosis() {
		Diagnosis result = doctorStrange.makeDiagnosis(xanderCage);
		String expected = "Доктор Strange ставит диагноз "
				+ "ЗАМЕЧАТЕЛЬНОЕ НАСТРОЕНИЕ ОТ ПРОХОЖДЕНИЯ КУРСОВ JOB4J"
				+ " пациенту Ксандер";

		assertThat(result.toString(), is(expected));
	}
}
