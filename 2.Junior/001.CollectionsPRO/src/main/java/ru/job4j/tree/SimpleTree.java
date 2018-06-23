package ru.job4j.tree;

/**
 * Дерево, каждый узел которого может содержать более двух
 * дочерних узлов.
 *
 * @param <V> Параметризованный тип, представляющий тип значений, которые могут
 *           содержать узлы дерева.
 */
public class SimpleTree<V extends Comparable<V>> extends AbstractTree<V> {
	/**
	 * Конструктор по умолчанию. Ничего не делает.
	 */
	public SimpleTree() {
	}

	/**
	 * Создаёт дерево с корнем, значением которого является {@code value}.
	 *
	 * @param value Значение корня.
	 */
	public SimpleTree(V value) {
		setRoot(new MultiNode<>(value));
	}

	/**
	 * Получение двоичной реализации узла.
	 *
	 * @param value Значение, которое может содержаться в узле.
	 * @return Экземпляр бинарного узла.
	 */
	Node<V> getNodeInstance(V value) {
		return new MultiNode<>(value);
	}
}
