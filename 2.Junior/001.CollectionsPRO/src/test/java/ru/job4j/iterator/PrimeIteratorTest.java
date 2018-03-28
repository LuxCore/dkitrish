package ru.job4j.iterator;

import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIteratorTest {
	private Iterator<Integer> iterator;

	@Before
	public void setUp() {
		iterator = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9,
				10, 11, 12, 13, 14, 3571, 5, 6, 7, 8, 9, 10, 127});
	}

	@Test
	public void testSequenceOfNextMethod() {
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.next(), is(3571));
	}

	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException () {
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(3571));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(127));
		assertThat(this.iterator.hasNext(), is(false));
		this.iterator.next();
	}

	@Test
	public void testSequenceOfHasNextMethod () {
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(2));
		assertThat(this.iterator.next(), is(3));
		assertThat(this.iterator.next(), is(5));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.hasNext(), is(true));
		assertThat(this.iterator.next(), is(7));
		assertThat(this.iterator.next(), is(11));
		assertThat(this.iterator.next(), is(13));
		assertThat(this.iterator.next(), is(3571));
	}

	@Test
	public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber (){
		this.iterator = new PrimeIterator(new int[]{4,6});
		assertThat("should return false, cause there is no any prime number",
				this.iterator.hasNext(), is(false));
	}
}
