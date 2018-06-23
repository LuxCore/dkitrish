package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактный узел.
 *
 * @param <V> Тип параметра, представляющий тип содержащегося значения
 *            в узле.
 */
public abstract class AbstractNode<V extends Comparable<V>> implements Node<V> {
	/**
	 * Значение узла.
	 */
	private final V value;

	/**
	 * Список дочерних узлов.
	 */
	private final List<Node<V>> children;

	/**
	 * Конструктор, в котором можно задать количество дочерних
	 * узлов: либо их может быть два (бинарное дерево), либо
	 * больше двух.
	 *
	 * @param value    Значение, которое содержит узел.
	 * @param isBinary true, если узел будет использоваться в
	 *                 бинарном дереве, иначе false.
	 */
	AbstractNode(V value, boolean isBinary) {
		this.value = value;
		children = isBinary ? createBiList() : new ArrayList<>();
	}

	/**
	 * Метод создаёт {@link ArrayList} с двумя ячейками и заполняет
	 * его двума пустыми значениями {@code null}. В дальнейшем
	 * использование таких методов, как {@link ArrayList#get(int)},
	 * не будет выбрасывать исключение {@link IndexOutOfBoundsException}.
	 *
	 * @return Список с двумя ячейками, содержащими {@code null}
	 * значения.
	 */
	private List<Node<V>> createBiList() {
		List<Node<V>> list = new ArrayList<>(2);
		list.add(null);
		list.add(null);
		return list;
	}

	/**
	 * Добавление дочернего узла.
	 *
	 * @param child Дочерний узел, по отношению к текущему.
	 * @return true, если дочерний узел добавлен успешно, иначе - false.
	 */
	public abstract boolean add(Node<V> child);

	/**
	 * Получение значения узла.
	 *
	 * @return Значение, которое содержит узел.
	 */
	@Override
	public V getValue() {
		return value;
	}

	/**
	 * Получение всех дочерних узлов, которые не являются родительскими
	 * ни для каких других узлов.
	 *
	 * @return Все дочерние узлы.
	 */
	@Override
	public List<Node<V>> children() {
		return children;
	}

	@Override
	public int compareTo(Node<V> anotherNode) {
		return this.value.compareTo(anotherNode.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getValue());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node<V> o = (Node<V>) obj;
		return Objects.equals(this.getValue(), o.getValue());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(value.toString());
		sb.append('=').append('{');
		Iterator<Node<V>> itr = children.iterator();
		while (itr.hasNext()) {
			Node<V> node = itr.next();
			if (node != null) {
				sb.append(node.getValue().toString());
				if (itr.hasNext()) {
					sb.append(',').append(' ');
				}
			}
		}
		return sb.append('}').toString();
	}
}
