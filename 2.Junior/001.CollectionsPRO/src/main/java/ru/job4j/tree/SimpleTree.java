package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Queue;

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


	/**
	 * Определение является ли древо бинарным.
	 *
	 * @return true, если каждый узел дерева содержит не больше
	 * двух узлов, иначе - false.
	 */
	public boolean isBinary() {
		int degree = getDegree();
		return degree >= 0 && degree <= 2;
	}

	/**
	 * Получение степени дерева.
	 * <p>Если нет ни одного узла, то будет возвращено значение -1.</p>
	 *
	 * @return Степень дерева; -1 возвращается в случае отсутствия
	 * узлов в дереве.
	 */
	public int getDegree() {
		int degree = -1;
		Queue<Node<V>> nodes = new LinkedList<>();
		if (getRoot().isPresent()) {
			nodes.offer(getRoot().get());
			while (!nodes.isEmpty()) {
				Node<V> node = nodes.poll();
				if (node != null) {
					if (node.children().size() > degree) {
						degree = node.children().size();
					}
				}
				sendChildrenToQueue(node, nodes);
			}
		}
		return degree;
	}
}
