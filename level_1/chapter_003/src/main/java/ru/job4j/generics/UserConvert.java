package ru.job4j.generics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serves for converting of List of Users into Map of Users.
 */
public class UserConvert {
	/**
	 * Converts List of User to Map of User.
	 *
	 * @param list Given list of users.
	 *
	 * @return Map of users.
	 */
	public Map<Integer, User> process(List<User> list) {
		Map<Integer, User> users = new HashMap<>();

		for (User user : list) {
			users.put(user.getId(), user);
		}

		return users;
	}
}
