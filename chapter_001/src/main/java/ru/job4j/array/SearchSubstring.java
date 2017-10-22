package ru.job4j.array;

/**
 * Searching a substring in string.
 *
 * @author Denis.Kitrish
 * @since 22.10.2017
 * @version 1.0
 */
public class SearchSubstring {
	/**
	 * Checks whether orogin string contains substring.
	 *
	 * @param origin String where needed to find a substring.
	 * @param sub Search string.
	 *
	 * @return boolean If string contains sub then true.
	 */
	public boolean contains(String origin, String sub) {
		char[] chorig = origin.toCharArray();
		char[] chsub = sub.toCharArray();

		for (int i = 0; i < chorig.length; i++) {
			// Если длина подстроки превышает длину оригинала,
			// то выходим сразу.
			if (i > chorig.length - chsub.length) {
				return false;
			}

			for (int j = 0; j < chsub.length; j++) {
				// Если текущий символ в оригинальной строке не совпадает
				// с текущим символом подстроки, выходим из внутреннего цикла.
				if (chorig[i + j] != chsub[j]) {
					break;
				}

				if (j == chsub.length - 1) {
					return true;
				}
			}
		}

		return false;
	}
}