package ru.job4j.tree;

/**
 * Узел, который может содрежать более двух дочерних узлов.
 *
 * @param <V> Тип параметра, представляющий тип содержащегося значения в узле.
 */
public class MultiNode<V extends Comparable<V>> extends AbstractNode<V> {
	/**
	 * Конструктор с заданием значения.
	 *
	 * @param value Значение, которое содержится в узле.
	 */
	MultiNode(V value) {
		super(value, false);
	}

	/**
	 * Добавление дочернего узла.
	 *
	 * @param child Дочерний узел, по отношению к текущему.
	 * @return true, если дочерний узел добавлен успешно, иначе - false.
	 */
	public boolean add(Node<V> child) {
		if (child == null || this.equals(child) || children().contains(child)) {
			return false;
		}
		return children().add(child);
	}
}
