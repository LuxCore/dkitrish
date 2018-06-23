package ru.job4j.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

/**
 * Абстрактное дерево.
 *
 * @param <V> Параметризованный тип, представляющий тип значений, которые могут
 *            содержать узлы дерева.
 */
public abstract class AbstractTree<V extends Comparable<V>> implements Tree<V> {
	/**
	 * Кореневой узел дерева.
	 */
	private Node<V> root;

	/**
	 * Количество внесённых изменений в структуру дерева.
	 */
	private int modCount;

	/**
	 * Получение конкретной реализации узла.
	 *
	 * @param value Значение, которое может содержаться в узле.
	 * @return Экземпляр бинарного узла.
	 */
	abstract Node<V> getNodeInstance(V value);

	/**
	 * Добавление {@code child} узла в {@code parent} узел.
	 * <p>Список дочерних узлов не содержит дубликатов и не содержит узлов
	 * эквивалентного родительскому.</p>
	 * <p>Чтобы добавить корень в пустое дерево, нужно параметру
	 * {@code parent} присвоить {@code null}.</p>
	 * <pre>{@code
	 * Tree<String> tree = new SimpleTree<>();
	 * tree.add(null, "root");}
	 * </pre>
	 * <p>Если значения обоих параметров равны {@code null}, то будет
	 * выброшено исключение {@link IllegalArgumentException}.</p>
	 *
	 * @param parent Родительский узел.
	 * @param child  Дочерний узел.
	 * @return true, если дочерний узел {@code child} был успешно добавлен
	 * в родельский {@code parent}.
	 */
	@Override
	public boolean add(V parent, V child) {
		boolean result;
		if (getRoot().isPresent()) {
			Optional<Node<V>> parentNode = findBy(parent);
			result = parentNode.map((node) -> {
				node.add(getNodeInstance(child));
				increaseModCount();
				return true;
			}).orElse(false);
		} else {
			setRoot(getNodeInstance(child));
			increaseModCount();
			result = true;
		}
		return result;
	}

	/**
	 * Поиск узла по содержащемуся в нём значению.
	 * <p>Поиск узла выполняется вширь.</p>
	 *
	 * @param value Значение, по которому необходимо найти узел.
	 * @return Узел, содержащий значение {@code value}. Узел находится в
	 * обёртке {@link Optional}.
	 */
	@Override
	public Optional<Node<V>> findBy(V value) {
		Optional<Node<V>> result = Optional.empty();
		Queue<Node<V>> levelNodes = new LinkedList<>();
		levelNodes.offer(root);
		while (!levelNodes.isEmpty()) {
			Node<V> node = levelNodes.poll();
			if (node != null) {
				if (Objects.equals(value, node.getValue())) {
					result = Optional.of(node);
					break;
				}
				sendChildrenToQueue(node, levelNodes);
			}
		}
		return result;
	}

	/**
	 * Заполнение очереди {@code to} дочерними узлами родительского
	 * узла {@code from}.
	 *
	 * @param from Родительский узел.
	 * @param to   Очередь из дочерних узлов.
	 */
	void sendChildrenToQueue(Node<V> from, Queue<Node<V>> to) {
		for (Node<V> n : from.children()) {
			if (n != null) {
				to.offer(n);
			}
		}
	}

	/**
	 * Установка корня дерева.
	 *
	 * @param root Корень дерева.
	 */
	void setRoot(Node<V> root) {
		this.root = root;
	}

	/**
	 * Получение корневого узла дерева.
	 *
	 * @return Корневой узел дерева.
	 */
	public Optional<Node<V>> getRoot() {
		return Optional.ofNullable(root);
	}

	/**
	 * Увеличение счётчика изменений в структуре дерева.
	 */
	void increaseModCount() {
		modCount++;
	}

	@Override
	public Iterator<Node<V>> iterator() {
		return new TreeItr();
	}

	/**
	 * Итереатор по дереву.
	 * <p>Итератор типа fail-fast в методе {@code next}.</p>
	 */
	class TreeItr implements Iterator<Node<V>> {
		/**
		 * Ожидаемое количество изменений в структуре дерева.
		 */
		private int expectedModCount = modCount;

		/**
		 * Очередь дочерних узлов.
		 */
		private Queue<Node<V>> nodes = new LinkedList<>();

		/**
		 * Конструктор по умолчанию. Назначается корневой узел
		 * первым для итерирования.
		 */
		TreeItr() {
			nodes.offer(root);
		}

		@Override
		public boolean hasNext() {
			return nodes.peek() != null;
		}

		@Override
		public Node<V> next() {
			if (modCount > expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			sendChildrenToQueue(nodes.peek(), nodes);
			return nodes.poll();
		}
	}
}
