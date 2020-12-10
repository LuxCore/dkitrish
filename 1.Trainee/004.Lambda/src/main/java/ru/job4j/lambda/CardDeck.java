package ru.job4j.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Карточная колода.
 */
public class CardDeck {
	/**
	 * Генерация карточной колоды.
	 * @return Карточная колода.
	 */
	public List<Card> generate() {
		return Arrays.stream(CardSuit.values())
				.flatMap(suit -> Arrays.stream(CardValue.values())
						.map(value -> new Card(suit, value)))
				.collect(Collectors.toList());
	}
}
