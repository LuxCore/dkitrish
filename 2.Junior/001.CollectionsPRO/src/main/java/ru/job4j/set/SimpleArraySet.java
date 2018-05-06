package ru.job4j.set;

import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;

/**
 * Простое множество.
 * <p>Реализация базируется на {@link ru.job4j.list.DynamicArrayList}</p>
 *
 * @param <E> Тип элементов, содержащихся в простом множестве.
 */
public class SimpleArraySet<E> implements Iterable<E> {
	/**
	 * Динамический список на базе массива, ипользующийся в качестве хранилища
	 * элементов множества.
	 */
	private DynamicArrayList<E> arrayList = new DynamicArrayList<>();

	/**
	 * Добавляет новый элемент в множество.
	 * <p>Если объект pElement не имеет переопределённых методов equals() и
	 * hashCode(), то множество будет содержать эквивалентные объекты.</p>
	 * <p>В качестве хранилища используется динамический массив. Это значит, что
	 * сколько бы пользователь не добавлял объектов в множество почти всегда
	 * будут присутствовать пустые ячейки в массиве, кроме тех редких случаев,
	 * когда будет заполнен последний элемент.</p>
	 *
	 * @param pElement Элемент для хранения.
	 */
	public void add(E pElement) {
		if (pElement != null && !has(pElement)) {
			arrayList.add(pElement);
		}
	}

	/**
	 * Получение количества объектов в контейнере.
	 *
	 * @return Количество элементов в контейнере.
	 */
	public int elementsCount() {
		return arrayList.elementsCount();
	}

	/**
	 * Проверка хранилища на наличие элемента, эквивалентного добавляемому.
	 *
	 * @param pElement Добавляемый элемент.
	 * @return true, если элемент имеется в множестве; в противном случае false.
	 */
	private boolean has(E pElement) {
		boolean result = false;
		for (E e : arrayList) {
			if (pElement.equals(e)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return arrayList.iterator();
	}
}
