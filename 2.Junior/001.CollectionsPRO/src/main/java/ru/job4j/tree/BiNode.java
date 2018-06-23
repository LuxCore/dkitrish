package ru.job4j.tree;

/**
 * Двоичный узел: содержит не более двух дочерних узлов.
 *
 * @param <V> Тип параметра, представляющий тип содержащегося значения в узле.
 */
public class BiNode<V extends Comparable<V>> extends AbstractNode<V> {
	/**
	 * Левый узел.
	 * <p>Узел, значение которого меньше по отношению к родительскому.</p>
	 */
	private static final int LEFT = 0;

	/**
	 * Правый узел.
	 * <p>Узел, значение которого больше по отношению к родительскому.</p>
	 */
	private static final int RIGHT = 1;

	/**
	 * Конструктор бинарного узла.
	 *
	 * @param value Значение, которое будет содержать узел.
	 */
	BiNode(V value) {
		super(value, true);
	}

	/**
	 * Добавление дочернего узла.
	 * <p>1. Если значение узла меньше значения родительского узла и левый узел
	 * отсутствует, то узел будет добавлен слева.</p>
	 * <p>2. Если значение узла больше значения родительского узла и правый узел
	 * отсутствует, то узел будет добавлен справа.</p>
	 *
	 * @param child Дочерний узел.
	 * @return true, если добавление прошло успешно, иначе - false.
	 */
	@Override
	public boolean add(Node<V> child) {
		if (child == null) {
			return false;
		}
		BiNode<V> n = (BiNode<V>) child;
		if (this.compareTo(n) > 0) {
			if (children().get(LEFT) == null) {
				children().set(LEFT, n);
				return true;
			} else {
				return children().get(LEFT).add(child);
			}
		} else if (this.compareTo(n) < 0) {
			if (children().get(RIGHT) == null) {
				children().set(RIGHT, n);
				return true;
			} else {
				return children().get(RIGHT).add(child);
			}
		}
		return false;
	}
}
