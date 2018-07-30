package ru.job4j.waitnotify;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Параллельный поиск файлов.
 */
public class ParallelSearch {
	/**
	 * Корневая папка, от которой нужно производить поиск.
	 */
	private final String root;

	/**
	 * Текст, который необходимо найти в содержимом файлов.
	 */
	private final String text;

	/**
	 * Список расширений файлов, по которым будет производится
	 * поиск.
	 */
	private final List<String> extensions;

	/**
	 * Очередь из найденных файлов по расширению.
	 */
	private final BlockingQueue<Path> files = new LinkedBlockingQueue<>();

	/**
	 * Список путей, которые удовлетворяют искомому тексту.
	 */
	private final List<Path> paths = new ArrayList<>();

	/**
	 * Флаг, говорящий о завершении поиска в папке {@link #root}.
	 */
	private volatile boolean searchFinished;

	/**
	 * Конструктор параллельного поиска файлов.
	 *
	 * @param root       Корневая папка для поиска.
	 * @param text       Текст для поиска.
	 * @param extensions Расширения файлов для поиска.
	 */
	public ParallelSearch(String root, String text, List<String> extensions) {
		this.root = root;
		this.text = text;
		this.extensions = extensions;
	}

	/**
	 * Инициализация нитей для поиска файлов и чтения содержимого файлов.
	 *
	 * @throws InterruptedException выбрасывается в случае прерывания
	 *                              работы потока.
	 */
	public void run() throws InterruptedException {
		Thread read = new Thread(() -> {
			while (!ParallelSearch.this.searchFinished || !ParallelSearch.this.files.isEmpty()) {
				try {
					Path file = ParallelSearch.this.files.take();
					try (BufferedReader br = Files.newBufferedReader(file)) {
						for (String s = br.readLine(); s != null; s = br.readLine()) {
							if (s.contains(ParallelSearch.this.text)) {
								ParallelSearch.this.addPath(file);
								break;
							}
						}
					}
				} catch (InterruptedException | IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		Thread search = new Thread(() -> {
			try {
				Files.walkFileTree(Paths.get(ParallelSearch.this.root),
						new SearchVisitor(ParallelSearch.this.extsToGlob())
				);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				ParallelSearch.this.searchFinished();
			}
		});
		search.start();
		read.start();
		search.join();
		read.join();
	}

	/**
	 * Добавление путей в список {@link #paths}, если в тексте файла
	 * найден искомый текст.
	 *
	 * @param file Путь к файлу, в котором был найден искомый текст.
	 */
	private void addPath(Path file) {
		this.paths.add(file);
	}

	/**
	 * Поисковый посититель файлов.
	 */
	private class SearchVisitor extends SimpleFileVisitor<Path> {
		/**
		 * Шаблон Glob.
		 */
		private PathMatcher matcher;

		/**
		 * Конструктор, принимающий шаблон.
		 *
		 * @param pattern Шаблон Glob.
		 */
		SearchVisitor(String pattern) {
			this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (attrs.isRegularFile() && this.matcher.matches(file)) {
				try {
					ParallelSearch.this.putFileToQueue(file);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return FileVisitResult.CONTINUE;
		}
	}

	/**
	 * Преобразование всех расширений из списка {@code extensions}
	 * в шаблон Glob.
	 * <pre> {@code
	 * glob:**.{java,txt,xml,docx}
	 * } </pre>
	 *
	 * @return строку, содержащую шаблон
	 */
	private String extsToGlob() {
		StringBuilder sb = new StringBuilder("glob:**.{");
		for (String s : extensions) {
			sb.append(s).append(',');
		}
		return sb.deleteCharAt(sb.length() - 1).append('}').toString();
	}

	/**
	 * Помещение найденного пути к файлу в очередь.
	 *
	 * @param file Удовлетворяющий условию поиска путь к файлу.
	 * @throws InterruptedException выбрасывается в случае прерывания.
	 */
	private void putFileToQueue(Path file) throws InterruptedException {
		this.files.put(file);
	}

	/**
	 * Получение списока путей с найденными файлами по расширению и содержимому.
	 *
	 * @return Список путей с найденными файлами по расширению и содержимому.
	 */
	public List<Path> result() {
		return this.paths;
	}

	/**
	 * Взведение флага поиска об окончании.
	 */
	private void searchFinished() {
		this.searchFinished = true;
	}
}
