package ru.job4j.tree;

/**
 * Двоичное дерево поиска.
 * <p>Каждый узел такого дерева содержит не более двух дочерних
 * узлов. При этом "левый" узел содержит значение меньшее, чем
 * родительский узел. "Правый" узел имеет большее значение, чем
 * родительский узел.</p>
 *
 * @param <V> Параметризованный тип, представляющий тип значений, которые могут
 *           содержать узлы дерева.
 */
public final class BinarySearchTree<V extends Comparable<V>> extends AbstractTree<V> {
	/**
	 * Конструктор по умолчанию. Ничего не делает.
	 */
	public BinarySearchTree() {
	}

	/**
	 * Создаёт дерево с корнем, значением которого является {@code value}.
	 *
	 * @param value Значение корня.
	 */
	public BinarySearchTree(V value) {
		setRoot(new BiNode<>(value));
	}

	/**
	 * Получение двоичной реализации узла.
	 *
	 * @param value Значение, которое может содержаться в узле.
	 * @return Экземпляр бинарного узла.
	 */
	Node<V> getNodeInstance(V value) {
		return new BiNode<>(value);
	}

	/**
	 * Метод добавления дочернего узла.
	 *
	 * @param child Значение, которое должно содержаться в добавляемом узле.
	 * @return true, если добавление прошло успешно, иначе - false.
	 */
	public boolean add(V child) {
		boolean result = getRoot().map(root -> root.add(getNodeInstance(child)))
				.orElse(false);
		if (result) {
			increaseModCount();
		}
		return result;
	}
}
