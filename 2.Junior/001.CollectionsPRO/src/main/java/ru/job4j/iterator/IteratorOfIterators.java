package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов.
 */
public class IteratorOfIterators {
	/**
	 * Конвертерует итератор итераторова в один итератор.
	 *
	 * @param itrOfItr Итератор итераторов целых чисел.
	 * @return Итератор целых чисел.
	 */
	public Iterator<Integer> convert(Iterator<Iterator<Integer>> itrOfItr) {
		return new ItrOfItr(itrOfItr);
	}

	/**
	 * Итератор по итераторам.
	 */
	private class ItrOfItr implements Iterator<Integer> {
		/**
		 * Внешний/обрамляющий итератор.
		 */
		private Iterator<Iterator<Integer>> outerIterator;

		/**
		 * Внутренний итератор, являющийся элементом внешнего итератора outerIterator.
		 */
		private Iterator<Integer> innerIterator;

		/**
		 * В конструкторе сразу получаем ссылку на первый имеющийся внутренний
		 * итератор.
		 *
		 * @param itrOfItr Итератор итераторов.
		 */
		ItrOfItr(Iterator<Iterator<Integer>> itrOfItr) {
			this.outerIterator = itrOfItr;
			if (itrOfItr.hasNext()) {
				this.innerIterator = itrOfItr.next();
			}
		}

		/**
		 * Если внутренний итератор больше не содержит элементов, то переходим
		 * к следующему внутреннему итератору, если таковой имеется. Чтобы проход
		 * был исключительно по элементам внутренних итераторов, была добавлена
		 * рекурсия для игнорирования пустых итераторов.
		 *
		 * @return true в случае наличия следующего целого числа во внутреннем
		 * итераторе, false - при отстутствии такового.
		 */
		@Override
		public boolean hasNext() {
			boolean result = true;
			if (!this.innerIterator.hasNext()) {
				if (this.outerIterator.hasNext()) {
					this.innerIterator = this.outerIterator.next();
					result = hasNext();
				} else {
					result = false;
				}
			}
			return result;
		}

		/**
		 * Возвращает следующее целое число, содержащееся в текущем внутреннем
		 * итераторе.
		 *
		 * @return Следующее целое число, содержащееся в текущем внутреннем
		 * итераторе.
		 */
		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Элемент не найден.");
			}
			return innerIterator.next();
		}
	}
}
