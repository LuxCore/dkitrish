package ru.job4j.lambda;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Школа.
 */
public class School {
	/**
	 * Разделение учеников по классам.
	 * @param students Список учеников.
	 * @param predicate Условие распределения учеников.
	 * @return Список распределённых учеников.
	 */
	List<Student> collect(List<Student> students, Predicate<Student> predicate) {
		return students.stream().filter(predicate).collect(Collectors.toList());
	}
}
