package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование двоичного дерева поиска.
 */
public class BinarySearchTreeTest extends AbstractTreeTest {
	@Override
	protected <V extends Comparable<V>> Tree<V> getTreeInstance() {
		return new BinarySearchTree<>();
	}

	@Override
	protected <V extends Comparable<V>> Node<V> getNodeInstance(V value) {
		return new BiNode<>(value);
	}

	/**
	 * Тест добавления нового узла в бинарное деревце.
	 */
	@Test
	public void testAdd() {
		BinarySearchTree<String> tree = new BinarySearchTree<>("root");
		assertTrue(tree.add("one"));
		assertTrue(tree.add("two"));
		assertTrue(tree.add("three"));

		assertFalse(tree.add("one"));
		assertFalse(tree.add("two"));
		assertFalse(tree.add("three"));
	}
}