package ru.job4j.tree;

import org.junit.Test;
import ru.job4j.map.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Тестирование конкретных реализаций {@link Node}
 * через тестирование абстрактного узла {@link AbstractNode}.
 * <p>В данном тестовом классе производится тестирование
 * общих методов для всех реализаций. Специфические реализации
 * методов тестируются в тестовом классе для конкретной
 * реализции узла.</p>
 */
public abstract class AbstractNodeTest {
	/**
	 * Получение конкретной реализации узла.
	 *
	 * @param value Значение, которое будет содержать узел.
	 * @param <V>   Параметризованный тип, представляющий тип значения узла.
	 * @return Конкретную реализацию узла.
	 */
	protected abstract <V extends Comparable<V>> AbstractNode<V> getNodeInstance(V value);

	/**
	 * Тест получения значения.
	 */
	@Test
	public void testGetValue() {
		AbstractNode<String> root = getNodeInstance("root");
		assertEquals(root.getValue(), "root");
		root.add(getNodeInstance("a"));
		assertEquals(root.children().get(0).getValue(), "a");
	}

	/**
	 * Тест получения дочерних узлов.
	 */
	@Test
	public void testChildren() {
		AbstractNode<Integer> root = getNodeInstance(69);
		AbstractNode<Integer> node01 = getNodeInstance(65);
		root.add(node01);
		AbstractNode<Integer> node02 = getNodeInstance(75);
		root.add(node02);
		assertEquals(root.children().toString(), "[65={}, 75={}]");
	}

	/**
	 * Тест расчёта хэш-кода узла.
	 */
	@Test
	public void testHashCode() {
		AbstractNode<String> node01 = getNodeInstance("node01");
		AbstractNode<String> node02 = getNodeInstance("node02");
		assertNotEquals(node01.hashCode(), node02.hashCode());
		assertEquals(node01.hashCode(), getNodeInstance("node01").hashCode());

		int hash1 = getNodeInstance("some_node").hashCode();
		int hash2 = getNodeInstance("some_node").hashCode();
		assertThat(hash1, is(equalTo(hash2)));
	}

	/**
	 * Тест сравнения двух узлов.
	 */
	@Test
	public void testEquals() {
		AbstractNode<Integer> node = getNodeInstance(69);
		assertThat(node, is(equalTo(node)));
		assertThat(node, not(equalTo(null)));
		assertThat(node, not(equalTo(new User())));
		assertThat(getNodeInstance("root"), is(equalTo(getNodeInstance("root"))));
		assertThat(getNodeInstance("root"), not(equalTo(getNodeInstance("node01"))));
	}

	/**
	 * Тест представления узла в виде строки.
	 */
	@Test
	public void testToString() {
		AbstractNode<Integer> root = getNodeInstance(69);
		assertEquals(root.toString(), "69={}");

		AbstractNode<Integer> node01 = getNodeInstance(65);
		root.add(node01);
		AbstractNode<Integer> node02 = getNodeInstance(75);
		root.add(node02);
		assertEquals(root.toString(), "69={65, 75}");
	}
}