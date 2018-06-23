package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование мультидерева.
 */
public class SimpleTreeTest extends AbstractTreeTest {
	@Override
	protected <V extends Comparable<V>> Tree<V> getTreeInstance() {
		return new SimpleTree<>();
	}

	@Override
	protected <V extends Comparable<V>> Node<V> getNodeInstance(V value) {
		return new MultiNode<>(value);
	}


	/**
	 * Тестирование проверки простого дерева на бинарность,
	 * т.е. содержит ли каждый узел дерева не больше двух узлов.
	 */
	@Test
	public void testIsBinary() {
		SimpleTree<String> emptyTree = new SimpleTree<>();
		assertFalse(emptyTree.isBinary());

		SimpleTree<String> tree = new SimpleTree<>("root");
		tree.add("root", "one");
		tree.add("root", "two");
		tree.add("one", "three");
		tree.add("one", "four");
		tree.add("two", "five");
		tree.add("two", "six");
		tree.add("three", "seven");
		tree.add("three", "eight");
		tree.add("five", "nine");
		tree.add("five", "ten");
		assertTrue(tree.isBinary());
		tree.add("ten", "eleven");
		tree.add("ten", "twelve");
		tree.add("ten", "thirteen");
		assertFalse(tree.isBinary());
	}

	/**
	 * Тест получения степени простого дерева.
	 */
	@Test
	public void testGetDegree() {
		SimpleTree<Integer> tree = new SimpleTree<>(69);
		tree.add(69, 60);
		tree.add(69, 70);

		tree.add(60, 61);
		tree.add(60, 62);

		tree.add(70, 71);
		tree.add(70, 72);
		assertEquals(tree.getDegree(), 2);

		tree.add(71, 73);
		tree.add(71, 74);
		tree.add(71, 75);
		assertEquals(tree.getDegree(), 3);

		tree.add(72, 81);
		tree.add(72, 82);
		tree.add(72, 83);
		tree.add(72, 84);
		assertEquals(tree.getDegree(), 4);
	}
}