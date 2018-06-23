package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование конкретных реализаций {@link Tree}
 * через тестирование абстрактного дерева {@link AbstractTree}.
 * <p>В данном тестовом классе производится тестирование
 * общих методов для всех реализаций. Специфические реализации
 * методов тестируются в тестовом классе для конкретной
 * реализции дерева.</p>
 */
public abstract class AbstractTreeTest {
	/**
	 * Получение конкретной реализации дерева.
	 *
	 * @param <V> Параметризованный тип, представляющий
	 *            тип значений, которые могут содержать узлы дерева.
	 * @return Конкретную реализацию дерева.
	 */
	protected abstract <V extends Comparable<V>> Tree<V> getTreeInstance();

	/**
	 * Получение экземпляра узла.
	 *
	 * @param value Значение, которое может содержать узел.
	 * @param <V> Параметризованный тип, представляющий
	 *            тип значений, которые могут содержать узлы дерева.
	 * @return Конкретную реализацию узла.
	 */
	protected abstract <V extends Comparable<V>> Node<V> getNodeInstance(V value);

	/**
	 * Тест поиска узла по значению.
	 */
	@Test
	public void testFinBy() {
		Tree<String> tree1 = getTreeInstance();
		assertNull(tree1.findBy("root").orElse(null));

		tree1.add(null, "root");

		Tree<String> tree2 = getTreeInstance();
		tree2.add(null, "root");

		Node<String> actual = tree1.findBy("root").orElse(null);
		Node<String> expected = tree2.findBy("root").orElse(null);
		assertThat(actual, is(equalTo(expected)));

		tree1.add("root", "one");
		tree1.add("root", "two");
		tree1.add("root", "three");

		tree2.add("root", "one");
		tree2.add("root", "two");
		tree2.add("root", "three");

		actual = tree1.findBy("root").orElse(null);
		expected = tree2.findBy("root").orElse(null);
		assertThat(actual, is(equalTo(expected)));

		tree1.add("one", "four");
		tree1.add("one", "five");
		tree1.add("one", "six");
		tree1.add("one", "seven");
		tree1.add("seven", "eight");
		tree1.add("seven", "nine");
		tree1.add("seven", "ten");

		tree2.add("one", "four");
		tree2.add("one", "five");
		tree2.add("one", "six");
		tree2.add("one", "seven");
		tree2.add("seven", "eight");
		tree2.add("seven", "nine");
		tree2.add("seven", "ten");

		actual = tree1.findBy("seven").orElse(null);
		expected = tree2.findBy("seven").orElse(null);
		assertThat(actual, is(equalTo(expected)));
	}

	/**
	 * Тест обхода дерева итератором в нормальном режиме.
	 */
	@Test
	public void testIterator() {
		Tree<Integer> tree = getTreeInstance();
		Iterator<Node<Integer>> itr = tree.iterator();
		assertThat(itr.hasNext(), is(equalTo(false)));

		tree.add(null, 69);
		tree.add(69, 555);
		tree.add(69, 50);
		tree.add(69, 90);
		tree.add(69, 111);
		tree.add(555, 55511);
		tree.add(555, 55522);
		itr = tree.iterator();
		assertThat(itr.hasNext(), is(equalTo(true)));
		assertThat(itr.next(), is(equalTo(getNodeInstance(69))));
		assertTrue(itr.hasNext());
		assertTrue(itr.hasNext());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertFalse(itr.hasNext());
	}

	/**
	 * Тест fail-fast режима итератора дерева.
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void testIteratorWhenConcurrentModificationOccurs() {
		Tree<String> tree = getTreeInstance();
		tree.add(null, "69");
		tree.add("69", "555");
		Iterator<Node<String>> itr = tree.iterator();
		assertTrue(itr.hasNext());
		assertThat(itr.next(), is(equalTo(getNodeInstance("69"))));
		tree.add("69", "666");
		itr.next();
	}

	/**
	 * Тест итератора дерева, когда элементов больше нет.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testIteratorWhenNoSuchElementOccurs() {
		Tree<Integer> tree = getTreeInstance();
		tree.add(null, 69);
		tree.add(69, 555);
		tree.add(69, 50);
		tree.add(555, 55511);
		tree.add(555, 55522);
		tree.add(50, 5011);
		tree.add(50, 5022);
		Iterator<Node<Integer>> itr = tree.iterator();
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		assertNotNull(itr.next());
		itr.next();
	}
}