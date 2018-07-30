package ru.job4j.waitnotify;

import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование параллельного поиска файлов и чтения их содержимого.
 */
public class ParallelSearchTest {
	/**
	 * Тестирование параллельного поиска файлов и чтения их содержимого.
	 */
	@Test
	public void testParallelSearch() {
		List<String> extensions = new LinkedList<>();
		extensions.add("java");
		extensions.add("xml");
		extensions.add("txt");
		extensions.add("sql");
		File file = new File(getClass().getClassLoader().getResource("testParallelSearch").getFile());
		String root = file.getAbsolutePath();
		ParallelSearch prlSearch = new ParallelSearch(root, "Искомый_текст", extensions);
		try {
			prlSearch.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(2, prlSearch.result().size());
	}
}