package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
* Tests conversion List.
*/
public class ConvertListTest {
	/**
	 * Tests conversion of List of integer arrays to List of Integer.
	 */
	@Test
	public void testConversionOfArrayListOfArraysToList() {
		List<int[]> listOfArrays = new ArrayList<>();
		listOfArrays.add(new int[]{54, 21, 37, 10, 49, 42, 61});
		listOfArrays.add(new int[]{54, 7, 30, 21, 4, 44, 50});
		listOfArrays.add(new int[]{27, 66, 31, 54, 55});
		listOfArrays.add(new int[]{20, 3, 7, 53, 32});

		ConvertList cl = new ConvertList();
		List<Integer> result = cl.listOfArraysToList(listOfArrays);

		int[] intArray = new int[]{54, 21, 37, 10, 49, 42, 61, 54, 7, 30, 21, 4, 44,
				50, 27, 66, 31, 54, 55, 20, 3, 7, 53, 32};
		List<Integer> expected = Arrays.stream(intArray).boxed().collect(Collectors.toList());

		assertThat(result, is(expected));
	}

	/**
	 * Tests conversion of List of integer arrays to List of Integer.
	 */
	@Test
	public void testConversionOfLinkedListOfArraysToList() {
		List<int[]> listOfArrays = new LinkedList<>();
		listOfArrays.add(new int[]{7, 30, 21, 4});
		listOfArrays.add(new int[]{54, 7, 30, 21, 4, 44});
		listOfArrays.add(new int[]{10, 49, 42});

		ConvertList cl = new ConvertList();
		List<Integer> result = cl.listOfArraysToList(listOfArrays);

		int[] intArray = new int[]{7, 30, 21, 4, 54, 7, 30, 21, 4, 44, 10, 49, 42};
		List<Integer> expected = Arrays.stream(intArray).boxed().collect(Collectors.toList());

		assertThat(result, is(expected));
	}
}
