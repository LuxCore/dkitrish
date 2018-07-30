package ru.job4j.monitorsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Хранилище пользователей.
 */
@ThreadSafe
public class UserStorage {
	/**
	 * Множество пользователей.
	 */
	@GuardedBy("this")
	private Set<User> storage = new HashSet<>();

	/**
	 * Добавление пользователя в хранилище.
	 *
	 * @param user Пользователь, которого нужно добавить в систему.
	 * @return true, если пользователь успешно добавлен в систему,
	 * иначе - false.
	 */
	public synchronized boolean add(User user) {
		return storage.add(user);
	}

	/**
	 * Обновление информации о пользователе в хранилище.
	 *
	 * @param user Пользователь, информацию о котором нужно изменить в системе.
	 * @return true, если информация о пользователе успешно изменена в системе,
	 * иначе - false.
	 */
	public synchronized boolean update(User user) {
		boolean result = false;
		User u = findUser(user);
		if (u != null) {
			u.setAmount(user.getAmount());
			result = true;
		}
		return result;
	}

	/**
	 * Поиск пользователя.
	 *
	 * @param user Пользователь.
	 * @return пользователя.
	 */
	private synchronized User findUser(User user) {
		User result = null;
		for (User u : storage) {
			if (u.equals(user)) {
				result = u;
				break;
			}
		}
		return result;
	}

	/**
	 * Удаление пользователя из хранилища.
	 *
	 * @param user Пользователь, которого нужно удалить из системы.
	 * @return true, если пользователь успешно удалён из системы,
	 * иначе - false.
	 */
	public synchronized boolean delete(User user) {
		return storage.remove(user);
	}

	/**
	 * Пересылка денежной суммы от одного пользователя другому.
	 *
	 * @param userIdFrom Пользователь, со счёта которого необходимо перечислить
	 *                   денежную сумму.
	 * @param userIdTo   Пользователь, на счёт которого необходимо начислить
	 *                   денежную сумму.
	 * @param amount     Переводимая денежная сумма.
	 * @return true, если операция пеервода прошла успешно, иначе - false.
	 */
	public boolean transfer(int userIdFrom, int userIdTo, int amount) {
		boolean result = false;
		User uFrom = findUser(new User(userIdFrom, 0));
		User uTo = findUser(new User(userIdTo, 0));
		if (uFrom != null && uTo != null && uFrom.getAmount() >= amount) {
			uTo.setAmount(uTo.getAmount() + amount);
			uFrom.setAmount(uFrom.getAmount() - amount);
			result = true;
		}
		return result;
	}

	@Override
	public synchronized String toString() {
		return "UserStorage" + storage;
	}
}
