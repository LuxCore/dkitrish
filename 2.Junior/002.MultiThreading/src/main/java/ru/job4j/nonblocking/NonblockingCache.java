package ru.job4j.nonblocking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Неблокирующий кэш.
 */
public class NonblockingCache {
	/**
	 * Хранилище объектов.
	 */
	private Map<Integer, Base> cache = new ConcurrentHashMap<>();

	/**
	 * Добавление объекта.
	 *
	 * @param model Добавляемый объект.
	 */
	public void add(Base model) {
		if (model != null) {
			cache.putIfAbsent(model.getId(), model);
		}
	}

	/**
	 * Удаление объекта.
	 *
	 * @param model Удаляемый объект.
	 */
	public void delete(Base model) {
		cache.remove(model.getId());
	}

	/**
	 * Обновление объекта.
	 *
	 * @param model Обновляемый объект.
	 */
	public void update(Base model) {
		BiFunction<Integer, Base, Base> func = (Integer id, Base base) -> {
			if (model.getVersion() < base.getVersion()) {
				throw new OptimisticException("The value has been changed by other thread.");
			}
			model.setVersion(model.getVersion() + 1);
			return model;
		};
		cache.computeIfPresent(model.getId(), func);
	}
}
