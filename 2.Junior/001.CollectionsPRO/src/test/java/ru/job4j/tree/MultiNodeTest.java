package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование мультиузла.
 */
public class MultiNodeTest extends AbstractNodeTest {
	/**
	 * Получение реализации {@link MultiNode}.
	 *
	 * @param value {@inheritDoc}
	 * @param <V> {@inheritDoc}
	 * @return Реализацию мультиузла.
	 */
	protected <V extends Comparable<V>> AbstractNode<V> getNodeInstance(V value) {
		return new MultiNode<>(value);
	}

	/**
	 * Тест добавления узла.
	 */
	@Test
	public void testAdd() {
		AbstractNode<String> root = getNodeInstance("69");
		assertTrue(root.add(getNodeInstance("65")));
		assertFalse(root.add(getNodeInstance("65")));
		AbstractNode<String> node128 = getNodeInstance("128");
		assertTrue(root.add(node128));
		AbstractNode<String> node01 = getNodeInstance("150");
		assertTrue(root.add(node01));
		assertFalse(node01.add(getNodeInstance("150")));
		assertFalse(node01.add(null));
		assertTrue(node128.add(getNodeInstance("150")));
	}
}