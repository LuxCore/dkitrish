package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Проверка генерации карточной колоды.
 */
public class CardDeckTest {
	/**
	 * Проверка метода генерации карточной колоды.
	 */
	@Test
	public void testGenerateCardDeck() {
		CardDeck cardDeck = new CardDeck();
		List<Card> actualCards = cardDeck.generate();
		List<Card> expectedCards = new ArrayList<>();
		expectedCards.add(new Card(CardSuit.Diamond, CardValue.V_6));
		expectedCards.add(new Card(CardSuit.Diamond, CardValue.V_7));
		expectedCards.add(new Card(CardSuit.Diamond, CardValue.V_8));
		expectedCards.add(new Card(CardSuit.Heart, CardValue.V_6));
		expectedCards.add(new Card(CardSuit.Heart, CardValue.V_7));
		expectedCards.add(new Card(CardSuit.Heart, CardValue.V_8));
		expectedCards.add(new Card(CardSuit.Spade, CardValue.V_6));
		expectedCards.add(new Card(CardSuit.Spade, CardValue.V_7));
		expectedCards.add(new Card(CardSuit.Spade, CardValue.V_8));
		expectedCards.add(new Card(CardSuit.Club, CardValue.V_6));
		expectedCards.add(new Card(CardSuit.Club, CardValue.V_7));
		expectedCards.add(new Card(CardSuit.Club, CardValue.V_8));

		assertEquals(expectedCards, actualCards);
	}
}