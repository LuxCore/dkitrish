package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of collection performance.
 */
public class CollectionsTest {
	/**
	 * Testing performance of collection when elements are added.
	 */
	@Ignore
	@Test
	public void testAddAndDelWCollection() {
		Collections collections = new Collections();
		Collection<String> coll = null;
		int addAmount = 1_000_000;
		int delAmount = 2_000;

		coll = new ArrayList<>();
		collections.addValues(coll, addAmount);
		assertThat(coll.size(), is(1_000_000));

		collections.deleteValues(coll, delAmount);
		assertThat(coll.size(), is(998_000));

		coll = new LinkedList<>();
		collections.addValues(coll, addAmount);
		assertThat(coll.size(), is(1_000_000));

		collections.deleteValues(coll, delAmount);
		assertThat(coll.size(), is(998_000));

		coll = new TreeSet<>();
		collections.addValues(coll, addAmount);
		assertThat(coll.size(), is(1_000_000));

		collections.deleteValues(coll, delAmount);
		assertThat(coll.size(), is(998_000));
	}
}
