package edu.neu.cs5004.poker;

import java.util.ArrayList;
import java.util.List;

public class Poker {

    /**
     * This function returns the highest hand in the given array.
     * @param hands An array of hands to compare
     * @return The highest hand
     */
    public Deck game_deck;
    public List<Hand> hands;
    public Hand hand = new Hand();
    public int player_num;




    public Poker(int player_num){
        game_deck = new Deck();
        hands = hand.create_player_hands(player_num);
    }


    public Hand highestHand(List<Hand> hands) {
        Hand highesthand = hands.get(0);
        for(Hand hand: hands){
            if(highesthand.getTypeHand().ordinal() < hand.getTypeHand().ordinal()){
                highesthand = hand;
            } else if (highesthand.getTypeHand().ordinal() == hand.getTypeHand().ordinal()) {
                if(highesthand.getHighestValue().ordinal() < hand.getHighestValue().ordinal()){
                    highesthand = hand;
                }
            }
        }
        return highesthand;
    }
}
