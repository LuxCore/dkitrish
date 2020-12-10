package ru.job4j.lambda;

import java.util.Objects;

/**
 * Карта.
 */
public class Card {
	/**
	 * Масть карты.
	 */
	private CardSuit suit;
	/**
	 * Приоритет масти.
	 */
	private CardValue value;

	/**
	 * Конструктор карты.
	 * @param suit Масть карты.
	 * @param value Приоритет карты.
	 */
	public Card(CardSuit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Card{" + suit + "-" + value + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return suit == card.suit && value == card.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(suit, value);
	}
}
