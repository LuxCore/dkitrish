package ru.job4j.tree;

import java.util.List;

/**
 * Интерфейс узла.
 * @param <V> Тип параметра, представляющий тип значения, которое
 *           может содержатся в узле.
 */
public interface Node<V extends Comparable<V>> extends Comparable<Node<V>> {
	/**
	 * Добавление дочернего узла.
	 *
	 * @param child Дочерний узел.
	 * @return true, если добавление прошло успешно, иначе - false.
	 */
	boolean add(Node<V> child);

	/**
	 * Получение всех дочерних узлов.
	 *
	 * @return Список дочерних узлов.
	 */
	List<Node<V>> children();

	/**
	 * Получение значения.
	 *
	 * @return Значение, которое содрежит узел.
	 */
	V getValue();

	@Override
	int compareTo(Node<V> anotherNode);
}
