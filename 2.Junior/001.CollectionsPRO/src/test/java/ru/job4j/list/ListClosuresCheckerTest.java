package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Проверка поиска замыканий в связном списке.
 */
public class ListClosuresCheckerTest {
	/**
	 * Тест поиска замыканий среди некой последовательности узлов.
	 * <p>Замыканий может быть много: в середине условного списка, замыкание
	 * узла на самого себя.</p>
	 * <p>Также может быть последовательность узлов без замыканий. Тогда узел
	 * не ссылается ни на какой другой узел.</p>
	 */
	@Test
	public void testSeekOfFewClosuresInSequenceOfNodes() {
		Node<Integer> zero = new Node<>(0);
		Node<Integer> first = new Node<>(1);
		Node<Integer> two = new Node<>(2);
		Node<Integer> three = new Node<>(3);
		Node<Integer> four = new Node<>(4);
		Node<Integer> five = new Node<>(5);
		Node<Integer> six = new Node<>(6);
		Node<Integer> seven = new Node<>(7);
		Node<Integer> eight = new Node<>(8);
		Node<Integer> nine = new Node<>(9);
		Node<Integer> ten = new Node<>(10);
		Node<Integer> eleven = new Node<>(11);

		zero.setNext(six);
		first.setNext(six);
		two.setNext(zero);
		three.setNext(first);
		four.setNext(four);
		five.setNext(three);
		six.setNext(three);
		seven.setNext(four);
		eight.setNext(zero);
		nine.setNext(ten);
		ten.setNext(eleven);
		eleven.setNext(null);

		assertThat(ListClosuresChecker.hasClosure(zero), is(true));
		assertThat(ListClosuresChecker.hasClosure(first), is(true));
		assertThat(ListClosuresChecker.hasClosure(two), is(true));
		assertThat(ListClosuresChecker.hasClosure(three), is(true));
		assertThat(ListClosuresChecker.hasClosure(four), is(true));
		assertThat(ListClosuresChecker.hasClosure(five), is(true));
		assertThat(ListClosuresChecker.hasClosure(six), is(true));
		assertThat(ListClosuresChecker.hasClosure(seven), is(true));
		assertThat(ListClosuresChecker.hasClosure(eight), is(true));
		assertThat(ListClosuresChecker.hasClosure(nine), is(false));
		assertThat(ListClosuresChecker.hasClosure(ten), is(false));
		assertThat(ListClosuresChecker.hasClosure(eleven), is(false));
	}

	/**
	 * Проверка случая, когда замыкание отсутствует.
	 */
	@Test
	public void testAbsenceOfClosure() {
		Node<String> zero = new Node<>("0");
		Node<String> first = new Node<>("1");
		Node<String> two = new Node<>("2");
		Node<String> three = new Node<>("3");

		zero.setNext(first);
		first.setNext(two);
		two.setNext(three);
		three.setNext(null);

		assertThat(ListClosuresChecker.hasClosure(zero), is(false));
		assertThat(ListClosuresChecker.hasClosure(first), is(false));
		assertThat(ListClosuresChecker.hasClosure(two), is(false));
		assertThat(ListClosuresChecker.hasClosure(three), is(false));
	}

	/**
	 * Проеврка выброса исключения, в случае передачи пустого аргумента.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPassingOfNullNode() {
		ListClosuresChecker.hasClosure(null);
	}
}
