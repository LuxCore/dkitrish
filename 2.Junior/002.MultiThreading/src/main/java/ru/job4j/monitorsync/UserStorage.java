package ru.job4j.monitorsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище пользователей.
 */
@ThreadSafe
public class UserStorage {
	/**
	 * Множество пользователей.
	 */
	@GuardedBy("this")
	private Map<Integer, User> storage = new HashMap<>();

	/**
	 * Добавление пользователя в хранилище.
	 *
	 * @param user Пользователь, которого нужно добавить в систему.
	 * @return true, если пользователь успешно добавлен в систему,
	 * иначе - false.
	 */
	public synchronized boolean add(User user) {
		return storage.putIfAbsent(user.getId(), user) != null;
	}

	/**
	 * Обновление информации о пользователе в хранилище.
	 *
	 * @param user Пользователь, информацию о котором нужно изменить в системе.
	 * @return true, если информация о пользователе успешно изменена в системе,
	 * иначе - false.
	 */
	public synchronized boolean update(User user) {
		return this.storage.computeIfPresent(user.getId(),
				(k, v) -> {
					v.setAmount(user.getAmount());
					return v;
				}) != null;
	}

	/**
	 * Удаление пользователя из хранилища.
	 *
	 * @param user Пользователь, которого нужно удалить из системы.
	 * @return true, если пользователь успешно удалён из системы,
	 * иначе - false.
	 */
	public synchronized boolean delete(User user) {
		return this.storage.remove(user.getId()) != null;
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
	public synchronized boolean transfer(int userIdFrom, int userIdTo, int amount) {
		boolean result = false;
		User sender = this.storage.get(userIdFrom);
		User recipient = this.storage.get(userIdTo);
		if (sender != null && recipient != null
				&& sender.getAmount() >= amount) {
			recipient.setAmount(recipient.getAmount() + amount);
			sender.setAmount(sender.getAmount() - amount);
			result = true;
		}
		return result;
	}

	/**
	 * Получение хранилища пользователей.
	 *
	 * @param id Идентификатор пользователя.
	 * @return Пользователя по идентифкатору.
	 */
	public synchronized User getUserById(int id) {
		return this.storage.get(id);
	}
}
