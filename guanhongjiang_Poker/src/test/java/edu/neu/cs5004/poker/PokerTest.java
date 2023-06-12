package edu.neu.cs5004.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerTest {


    Deck new_deck = new Deck();

    @Test
    void highestHand() {
        Poker test_game = new Poker(5);
        Hand highest_hand = test_game.highestHand(test_game.hands);
        int expected_player_amount = 5;
        int expected_hand_size = 5;
        assertEquals(expected_player_amount, test_game.hands.size());
        assertEquals(expected_hand_size, highest_hand.hand.size());
        System.out.println(highest_hand.getTypeHand());
    }
}