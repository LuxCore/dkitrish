package ru.job4j.collectionstatistics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Сравнение двух коллекций и выявление разницы между ними.
 */
public class Store {
	/**
	 * Сравнение коллекции: сколько было добавлено новый элементов,
	 * сколько было изменено элементов, сколько было удалено элементов.
	 *
	 * @param current  Текущая коллекция до изменений.
	 * @param previous Предыдущая коллекция до изменений.
	 * @return разницу до и после изменений.
	 */
	public Info diff(List<User> current, List<User> previous) {
		Info info = null;
		if (current.size() > 0) {
			if (previous.size() > 0) {
				info = diff1(current, previous);
			} else {
				info = new Info(current.size(), 0, 0);
			}
		} else if (previous.size() > 0) {
			info = new Info(0, 0, previous.size());
		}
		return info;
	}

	/**
	 * Вспомогательный метод для нахождения различий до и после
	 * изменений в коллекции.
	 *
	 * @param current  Текущая коллекция до изменений.
	 * @param previous Предыдущая коллекция до изменений.
	 * @return разницу до и после изменений.
	 */
	private Info diff1(List<User> current, List<User> previous) {
		int added = 0, updated = 0, deleted = 0;

		Iterator<User> prvItr = previous.iterator();
		Iterator<User> curItr = current.iterator();
		Set<Integer> updSet = new HashSet<>();

		for (int i = Math.max(current.size(), previous.size()); i >= 1; i--) {
			if (curItr.hasNext()) {
				User curUser = curItr.next();
				int containedAndUpdated = containedAndUpdated(previous, curUser);
				if (containedAndUpdated == 1) {
					updated++;
					updSet.add(curUser.getId());
				} else if (containedAndUpdated == -1) {
					added++;
				}
			}
			if (prvItr.hasNext()) {
				User prvUser = prvItr.next();
				if (!updSet.contains(prvUser.getId())) {
					if (containedAndUpdated(current, prvUser) == -1) {
						deleted++;
					}
				}
			}
		}
		return new Info(added, updated, deleted);
	}

	/**
	 * Содержится ли объект в коллекции.
	 * <p>Возвращает одно из трёх значений:
	 * <ul>
	 * <li>-1: объект не содержится в коллекции.</li>
	 * <li>0: объект содержится в коллекции.</li>
	 * <li>1: объект содержится в коллекции и обновлён.</li>
	 * </ul>
	 * </p>
	 *
	 * @param users Коллекция объектов.
	 * @param user  Искомый объект.
	 * @return -1, если объект не содержится в коллекции;
	 * 0, если объект содержится в коллекции; 1, если объект содержится в
	 * коллекции и обновлён.
	 */
	private int containedAndUpdated(List<User> users, User user) {
		int result = -1;
		for (User u : users) {
			if (u.getId() == user.getId()) {
				result = 0;
				if (!u.getName().equals(user.getName())) {
					result = 1;
				}
				break;
			}
		}
		return result;
	}
}
