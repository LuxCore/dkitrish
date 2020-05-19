package ru.job4j.lambda;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест распределения учеников.
 */
public class SchoolTest {

	/**
	 * Распределение учеников в класс 10А.
	 */
	@Test
	public void testCollectInto10A() {
		List<Student> students = List.of(
				new Student("Петров1", 100),
				new Student("Петров2", 83),
				new Student("Петров3", 70),
				new Student("Петров4", 69),
				new Student("Петров5", 50),
				new Student("Петров6", 58),
				new Student("Петров7", 49),
				new Student("Петров8", 35),
				new Student("Петров9", 1)
		);
		School school = new School();
		List<Student> result = school.collect(students, student -> student.getScore() >= 70);
		List<Student> expected = List.of(
				new Student("Петров1", 100),
				new Student("Петров2", 83),
				new Student("Петров3", 70)
		);
		assertThat(result, is(expected));
	}

	/**
	 * Распределение учеников в класс 10Б.
	 */
	@Test
	public void testCollectInto10B() {
		List<Student> students = List.of(
				new Student("Петров1", 100),
				new Student("Петров2", 83),
				new Student("Петров3", 70),
				new Student("Петров4", 69),
				new Student("Петров5", 50),
				new Student("Петров6", 58),
				new Student("Петров7", 49),
				new Student("Петров8", 35),
				new Student("Петров9", 1)
		);
		School school = new School();
		List<Student> result = school.collect(students,
				student -> student.getScore() < 70 && student.getScore() >= 50);
		List<Student> expected = List.of(
				new Student("Петров4", 69),
				new Student("Петров5", 50),
				new Student("Петров6", 58)
		);
		assertThat(result, is(expected));
	}

	/**
	 * Распределение учеников в класс 10В.
	 */
	@Test
	public void testCollectInto10C() {
		List<Student> students = List.of(
				new Student("Петров1", 100),
				new Student("Петров2", 83),
				new Student("Петров3", 70),
				new Student("Петров4", 69),
				new Student("Петров5", 50),
				new Student("Петров6", 58),
				new Student("Петров7", 49),
				new Student("Петров8", 35),
				new Student("Петров9", 1)
		);
		School school = new School();
		List<Student> result = school.collect(students, student -> student.getScore() < 50);
		List<Student> expected = List.of(
				new Student("Петров7", 49),
				new Student("Петров8", 35),
				new Student("Петров9", 1)
		);
		assertThat(result, is(expected));
	}
}