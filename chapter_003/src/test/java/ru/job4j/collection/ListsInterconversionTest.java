package ru.job4j.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing interconversion of List and array into each other.
 */
public class ListsInterconversionTest {
	/**
	 * Instance of InterconvesionLists.
	 */
	private ListsInterconversion convert;

	/**
	 * List to test menthods.
	 */
	private List<Integer> list;

	/**
	 * Initializing method for tests.
	 */
	@Before
	public void init() {
		this.convert = new ListsInterconversion();
	}

	/**
	 * Tests conversion of List into array.
	 */
	@Test
	public void testConvertListIntoArray() {
		int[][] result = null;
		int[][] expected = null;

		this.list = new ArrayList<>();
		ListsInterconversion.fillListWithRandomInts(this.list, 1);
		result = this.convert.toArray(this.list);
		expected = new int[][]{{22}};
		assertThat(result, is(expected));

		this.list = new LinkedList<>();
		ListsInterconversion.fillListWithRandomInts(this.list, 2);
		result = this.convert.toArray(this.list);
		expected = new int[][]{{22, 54}, {0, 0}};
		assertThat(result, is(expected));

		this.list = new ArrayList<>();
		ListsInterconversion.fillListWithRandomInts(this.list, 7);
		result = this.convert.toArray(this.list);
		expected = new int[][]{{22, 54, 21}, {38, 37, 10}, {49, 0, 0}};
		assertThat(result, is(expected));

		this.list = new LinkedList<>();
		ListsInterconversion.fillListWithRandomInts(this.list, 17);
		result = this.convert.toArray(this.list);
		expected = new int[][]{{22, 54, 21, 38, 37}, {10, 49, 26, 42, 61},
				{36, 54, 7, 30, 14}, {21, 4, 0, 0, 0}, {0, 0, 0, 0, 0}};
		assertThat(result, is(expected));
	}

	/**
	 * Tests conversion of array into List.
	 */
	@Test
	public void testConvertArrayIntoList() {
		int[][] array = null;
		String result = null;
		String expected = null;
		List<Integer> resultList = null;

		array = new int[][]{{22}};
		resultList = this.convert.toList(array);
		result = resultList.toString();
		expected = "[22]";
		assertThat(result, is(expected));

		array = new int[][]{{22, 54}, {21, 38, 37, 10}};
		resultList = this.convert.toList(array);
		result = resultList.toString();
		expected = "[22, 54, 21, 38, 37, 10]";
		assertThat(result, is(expected));

		array = new int[][]{{22, 54, 21, 38, 37, 10}, {49, 0, 0}, {36, 54, 7, 30, 14}};
		resultList = this.convert.toList(array);
		result = resultList.toString();
		expected = "[22, 54, 21, 38, 37, 10, 49, 0, 0, 36, 54, 7, 30, 14]";
		assertThat(result, is(expected));
	}
}
