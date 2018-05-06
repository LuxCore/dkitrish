package ru.job4j.list;

/**
 * Класс для проверки замыканий/цикличностей в связном списке, основанном на
 * узлах {@link ru.job4j.list.Node}.
 */
public final class ListClosuresChecker {
	/**
	 * Поиск замыканий в списке.
	 * <p>На вход подаётся любой узел списка в качестве начального. Таким
	 * образом неоднократный вызов данного метода поможет найти все замыкания
	 * имеющихся узлов.</p>
	 *
	 * @param initialNode Начальный узел, от которого происходит поиск замыкания.
	 * @return true, если замыкание найдено, в противном случае - false.
	 * @throws IllegalArgumentException если передан пустой объект в качестве
	 *                                  аргумента.
	 */
	public static boolean hasClosure(Node<?> initialNode) {
		if (initialNode == null) {
			throw new IllegalArgumentException("Метод принимает только непустые объекты.");
		}
		return initialNode.getNext() != null
				&& isSimilarNodes(initialNode, initialNode.getNext());
	}

	/**
	 * Сравнение двух смежных узлов на замыкание.
	 *
	 * @param firstNode  Первый узел для сравнения.
	 * @param secondNode Второй узел для сравнения.
	 * @return true, если замыкание найдено, в противном случае - false.
	 */
	private static boolean isSimilarNodes(Node<?> firstNode, Node<?> secondNode) {
		return firstNode == secondNode
				|| firstNode != null && firstNode.getNext() != null
				&& secondNode != null && secondNode.getNext() != null
				&& secondNode.getNext().getNext() != null
				&& isSimilarNodes(firstNode.getNext(), secondNode.getNext().getNext());
	}
}
