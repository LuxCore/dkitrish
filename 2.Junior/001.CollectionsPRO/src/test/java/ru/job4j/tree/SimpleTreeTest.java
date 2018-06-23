package ru.job4j.tree;

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
}