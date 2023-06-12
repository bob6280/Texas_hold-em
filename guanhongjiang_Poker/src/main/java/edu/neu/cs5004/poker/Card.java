package edu.neu.cs5004.poker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {


    public enum Suit {
        CLUB,
        DIAMOND,
        HEART,
        SPADE
    }

    public enum Value {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    private Suit suit;
    private Value value;
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }
}
