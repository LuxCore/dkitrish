package ru.job4j.pool;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	 * Пул потоков.
	 */
	private ExecutorService pool = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors());

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
		this.pool.submit(() -> send(EmailNotification.this.subject,
				EmailNotification.this.body, "email"));
	}

	/**
	 * Метод отсылки сообщения.
	 *
	 * @param subject Тема письма.
	 * @param body    Тело письма.
	 * @param email   Письмо.
	 */
	public void send(String subject, String body, String email) {

	}

	/**
	 * Закрытие пула потоков.
	 */
	public void close() {
		this.pool.shutdown();
		while (!this.pool.isTerminated()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Получение темы письма.
	 *
	 * @return тему письма.
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Получение содержимого письма.
	 *
	 * @return содержимое письма.
	 */
	public String getBody() {
		return this.body;
	}
}
