package ru.job4j.generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	public void testConversionOfListOfArraysToList() {
		ConvertList cl = new ConvertList();
		List<int[]> listOfArrays = new ArrayList<>();
		String result = null;
		String expected = null;

		cl.fillListWithArrays(listOfArrays, 69, 10, 4);
		List<Integer> iList = cl.listOfArraysToList(listOfArrays);
		result = iList.toString();
		expected = "[54, 21, 37, 10, 49, 42, 61, 54, 7, 30, 21, 4, 44, 50, 27, 66, 31, 54, 55, 20, 3, 7, 53, 32]";
		assertThat(result, is(expected));

		listOfArrays = new LinkedList<>();
		listOfArrays.add(new int[]{7, 30, 21, 4});
		listOfArrays.add(new int[]{54, 7, 30, 21, 4, 44});
		listOfArrays.add(new int[]{10, 49, 42});
		iList = cl.listOfArraysToList(listOfArrays);
		result = iList.toString();
		expected = "[7, 30, 21, 4, 54, 7, 30, 21, 4, 44, 10, 49, 42]";
		assertThat(result, is(expected));
	}
}
