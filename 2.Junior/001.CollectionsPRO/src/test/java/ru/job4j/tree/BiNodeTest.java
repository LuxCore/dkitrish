package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование двоичного узла.
 */
public class BiNodeTest extends AbstractNodeTest {
	/**
	 * Получение реализации {@link BiNode}.
	 *
	 * @param value {@inheritDoc}
	 * @param <V> {@inheritDoc}
	 * @return Реализацию двоичного узла.
	 */
	protected <V extends Comparable<V>> AbstractNode<V> getNodeInstance(V value) {
		return new BiNode<>(value);
	}

	/**
	 * Тест добавления узла.
	 */
	@Test
	public void testAdd() {
		// Добавляем первый узел слева от корня/родителя.
		AbstractNode<Integer> root = getNodeInstance(69);
		assertTrue(root.add(getNodeInstance(65)));
		assertFalse(root.add(getNodeInstance(65)));
		assertTrue(root.add(getNodeInstance(75)));

		// Добавляем первый узел справа от корня/родителя.
		root = getNodeInstance(69);
		assertTrue(root.add(getNodeInstance(128)));
		assertFalse(root.add(getNodeInstance(128)));
		assertTrue(root.add(getNodeInstance(30)));

		assertTrue(root.add(getNodeInstance(67)));
		assertTrue(root.add(getNodeInstance(70)));

		assertTrue(root.add(getNodeInstance(50)));
		assertTrue(root.add(getNodeInstance(150)));
	}

	/**
	 * Тест добавления {@code null} в качестве дочернего узла.
	 */
	@Test
	public void testAddNullChild() {
		AbstractNode<String> root = getNodeInstance("69");
		assertFalse(root.add(null));
		assertTrue(root.add(getNodeInstance("80")));
		assertTrue(root.add(getNodeInstance("50")));
		assertFalse(root.children().get(0).add(null));
	}
}