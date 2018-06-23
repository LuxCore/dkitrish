package ru.job4j.tree;

import java.util.Iterator;
import java.util.Optional;

/**
 * Интерфейс дерева.
 *
 * @param <V> Параметризованный тип, представляющий тип значений,
 *            которые могут содержать узлы дерева.
 */
public interface Tree<V extends Comparable<V>> extends Iterable<Node<V>> {
	/**
	 * Получение корневого узла.
	 *
	 * @return Корнвой узел.
	 */
	Optional<Node<V>> getRoot();

	/**
	 * Добавление {@code child} узла в {@code parent} узел.
	 *
	 * @param parent Родительский узел.
	 * @param child  Дочерний узел.
	 * @return true, если дочерний узел {@code child} был успешно добавлен
	 * в родельский {@code parent}.
	 */
	boolean add(V parent, V child);

	/**
	 * Поиск узла по содержащемуся в нём значению.
	 *
	 * @param value Значение, по которому необходимо найти узел.
	 * @return Узел, содержащий значение {@code value}. Узел находится в
	 * обёртке {@link Optional}.
	 */
	Optional<Node<V>> findBy(V value);

	@Override
	Iterator<Node<V>> iterator();
}
