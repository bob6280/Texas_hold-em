package edu.neu.cs5004.poker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> new_deck;
    private int players;


    public Deck() {
        new_deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                if (value == Card.Value.ONE) {
                    continue;
                }
                new_deck.add(new Card(suit, value));
            }
        }
        Collections.shuffle(new_deck);
    }

    public List<Card> deal_cards() {
        List<Card> new_hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            new_hand.add(new_deck.get(i));
        }

        return new_hand;
    }

}

