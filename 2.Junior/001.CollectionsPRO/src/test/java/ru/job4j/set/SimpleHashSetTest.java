package ru.job4j.set;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Тестирование хэш-множества.
 */
public class SimpleHashSetTest {
	/**
	 * Проверка на содержание объекта в хэш-множестве.
	 */
	@Test
	public void testContains() {
		SimpleHashSet<String> hashSet = new SimpleHashSet<>();
		hashSet.add("One");
		hashSet.add("Two");
		hashSet.add("Three");
		hashSet.add("Four");
		hashSet.add("Two");
		hashSet.add("Three");
		hashSet.add("Four");
		System.out.println(hashSet);
		assertThat(hashSet.contains("Three"), Is.is(true));
		assertThat(hashSet.contains("One"), Is.is(true));
		assertThat(hashSet.contains("Four"), Is.is(true));
		assertThat(hashSet.contains("Two"), Is.is(true));
		assertThat(hashSet.contains("Five"), Is.is(false));
	}

	/**
	 * Тест удаления объектов из хэш-множества.
	 */
	@Test
	public void testRemove() {
		SimpleHashSet<String> hashSet = new SimpleHashSet<>();
		hashSet.add("One");
		hashSet.add("Two");
		hashSet.add("Three");
		hashSet.add("Four");
		hashSet.add("Two");
		hashSet.add("Three");
		hashSet.add("Four");
		System.out.println(hashSet);
		assertThat(hashSet.getElementsCount(), Is.is(4));
		assertThat(hashSet.contains("Three"), Is.is(true));
		assertThat(hashSet.remove("Three"), Is.is(true));
		assertThat(hashSet.contains("Three"), Is.is(false));

		assertThat(hashSet.getElementsCount(), Is.is(3));
		assertThat(hashSet.contains("Four"), Is.is(true));
		assertThat(hashSet.remove("Four"), Is.is(true));
		assertThat(hashSet.contains("Four"), Is.is(false));

		assertThat(hashSet.getElementsCount(), Is.is(2));
		assertThat(hashSet.contains("Two"), Is.is(true));
		assertThat(hashSet.remove("Two"), Is.is(true));
		assertThat(hashSet.contains("Two"), Is.is(false));

		assertThat(hashSet.getElementsCount(), Is.is(1));
		assertThat(hashSet.contains("One"), Is.is(true));
		assertThat(hashSet.remove("One"), Is.is(true));
		assertThat(hashSet.contains("One"), Is.is(false));

		assertThat(hashSet.getElementsCount(), Is.is(0));
	}
}
