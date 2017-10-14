package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;

/**
 * Tests Deleting duplicates from array.
 *
 * @author Denis.Kitrish
 * @since 13.10.2017
 * @version 1.0
 */
public class ArrayDuplicateTest {

	/**
	 * ArrayDuplicate object.
	 */
	private ArrayDuplicate ad = new ArrayDuplicate();

	/**
	 * Test of deleting duplicates from array with different string elements:
	 * {"one", "two", "two", "three", "three", "three", "four", "four", "four", "four", "five", "five", "five", "five", "five"}.
	 */
	@Test
	public void testRemoveDuplicatesFromArrayWithDiffrentStrings() {
		String[] array = {"one", "two", "two", "three", "three", "three",
				"four", "four", "four", "four", "five", "five", "five", "five", "five"};
		String[] expected = {"one", "two", "three", "four", "five"};

		String[] result = ad.remove(array);

		assertThat(result, arrayContaining(expected));
	}

	/**
	 * Test of deleting duplicates from array with different string elements: {"first", "first", "first", "first", "first"}.
	 */
	@Test
	public void testRemoveDuplicatesFromArrayWithSameStrings() {
		String[] array = {"first", "first", "first", "first", "first"};
		String[] expected = {"first"};

		String[] result = ad.remove(array);

		assertThat(result, arrayContainingInAnyOrder(expected));
	}
}