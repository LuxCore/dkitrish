package ru.job4j.generics.store;

import ru.job4j.generics.SimpleArrayList;

import java.lang.reflect.ParameterizedType;

/**
 * Абстрактное хранилище различных сущностей.
 *
 * @param <T> Тип хранимых сущностей.
 */
public class AbstractStore<T extends Base> implements Store<T> {
	/**
	 * Хранилище сущностей.
	 */
	private SimpleArrayList<T> elements;

	/**
	 * Инициализируем хранилище.
	 */
	public AbstractStore() {
		this.elements = new SimpleArrayList(getParameterizedType());
	}

	@Override
	public void add(T model) {
		this.elements.add(model);
	}

	@Override
	public boolean replace(String id, T model) {
		int index = findIndexById(id);
		if (index > -1) {
			this.elements.set(index, model);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		int index = findIndexById(id);
		if (index > -1) {
			this.elements.delete(index);
			return true;
		}
		return false;
	}

	@Override
	public T findById(String id) {
		int index = findIndexById(id);
		if (index > -1) {
			return this.elements.get(index);
		}
		return null;
	}

	/**
	 * Вспомогательный метод для получения индекса ячейки массива, в которой
	 * лежит объект, у которого есть поле id.
	 *
	 * @param id Идентификатор искомого объекта.
	 * @return Индекс ячейки массива, в которой находится искомы объект.
	 */
	private int findIndexById(String id) {
		int result = -1;
		for (int i = 0; i < this.elements.elementsCount(); i++) {
			if (this.elements.get(i).getId().equals(id)) {
				result = i;
				break;
			}
		}
		return result;
	}

	/**
	 * Возвращает класс параметризованного типа, чтобы знать какого типа
	 * будет хранилище.
	 *
	 * @return Возвращает класс параметризованного типа, чтобы знать какого типа
	 * будет хранилище.
	 */
	private Class<?> getParameterizedType() {
		return (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
