package ru.job4j.pool;

import java.text.MessageFormat;

/**
 * Уведомление на электропочту.
 */
public class EmailNotification {
	/**
	 * Тема письма.
	 */
	private String subject;

	/**
	 * Содержимое письма.
	 */
	private String body;
	/**
	 * Создание/подготовка к отправлению уведомительного письма
	 * подписчику на почту.
	 *
	 * @param user Пользователь, которому необходимо отправить письмо.
	 */
	public void emailTo(User user) {
		this.subject = MessageFormat.format(
				"Notification for {0} to email {1}.",
				user.getUserName(), user.getEmail());
		this.body = MessageFormat.format(
				"Add a new event to {0}.",
				user.getUserName());
	}

	/**
	 * Получение темы письма.
	 *
	 * @return тему письма.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Получение содержимого письма.
	 *
	 * @return содержимое письма.
	 */
	public String getBody() {
		return body;
	}
}
