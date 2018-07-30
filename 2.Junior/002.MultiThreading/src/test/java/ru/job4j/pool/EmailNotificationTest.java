package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование исполнителя {@link java.util.concurrent.ExecutorService}.
 */
public class EmailNotificationTest {
	/**
	 * Блокирующая очередь подписчиков.
	 */
	private BlockingQueue<User> users = new LinkedBlockingQueue<>();

	/**
	 * Количество процессоров компьютера.
	 */
	private static final int NCPU = Runtime.getRuntime().availableProcessors();

	/**
	 * Счётчик для проверки того, что все подписчики обработаны.
	 */
	private AtomicInteger count = new AtomicInteger();

	/**
	 * Подготовка подписчиков для рассылки.
	 */
	@Before
	public void setUp() {
		for (int i = 1; i <= 20; i++) {
			String userName = "User_" + i;
			String userEmail = userName + "@email.com";
			this.users.offer(new User(userName, userEmail));
		}
	}

	/**
	 * Тест формирования текста для письма в {@link java.util.concurrent.ExecutorService}
	 * и отправка письма в потоке.
	 * <p>Для задачи использован {@link Callable}, чтобы задачу для выполнения
	 * дать всем потокам. Поскольку {@link ExecutorService} не располагает
	 * таким методом, как {@link Thread#join()}, то вызывается метод
	 * {@link ExecutorService#invokeAll}.</p>
	 *
	 * @throws InterruptedException выбрасывается в случае прерывания работы
	 *                              потока.
	 */
	@Test
	public void testSendingAnEmail() throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(NCPU);
		Callable<String> c = () -> {
			try {
				while (!EmailNotificationTest.this.users.isEmpty()) {
					User user = EmailNotificationTest.this.users.take();
					EmailNotification email = new EmailNotification();
					email.emailTo(user);
					sendNotification(email);
					EmailNotificationTest.this.count.incrementAndGet();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return Thread.currentThread().getName() + " finished work.";
		};
		List<Callable<String>> callables = new ArrayList<>();
		for (int i = 0; i < NCPU; i++) {
			callables.add(c);
		}
		List<Future<String>> l = pool.invokeAll(callables);
		pool.shutdown();
		for (Future<String> f : l) {
			try {
				System.out.println(f.get());
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		assertEquals(20, this.count.get());
	}

	/**
	 * Отправка уведомления подписчику.
	 *
	 * @param emailNotification Письмо-уведомление.
	 */
	private void sendNotification(EmailNotification emailNotification) {
		System.out.format("Email%n%12s%s.%n%12s%s%n",
				"subject: ", emailNotification.getSubject(),
				"body: ", emailNotification.getBody());
	}
}