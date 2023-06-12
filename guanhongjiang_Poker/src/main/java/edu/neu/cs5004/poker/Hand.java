package edu.neu.cs5004.poker;


import java.util.*;

import static edu.neu.cs5004.poker.Card.Value;


public class Hand {
    /**
     * The Hand class represents a hand of poker cards. Every hand should have a collection of
     * Card objects and a Type.
     * <p>
     * Objective: A hand is made up of 5 cards. Implement a method for processing the combination of
     * cards being passed in. Think back to the RPN calculator, which used conditional statements for
     * assigning a value. Combine this with helper methods to determine what kind of hand you have.
     * <p>
     * Potential helper methods (Optional):
     * <p>
     * Method 1: Given 5 cards, record the frequencies/ counts of a value. This will be
     * useful for finding pairs, three of a kinds, four of a kinds, and full house combinations.
     * <p>
     * Method 2: Given 5 cards, check if the values are consecutive (ie. SIX, SEVEN,
     * EIGHT, NINE, TEN). This will be useful for finding Straights, Straight Flushes,
     * and Royal Flushes.
     * <p>
     * Method 3: Given 5 cards, check if the suits are the same. This will be useful for
     * identifying Flushes, Straight Flushes, and Royal Flushes.
     * <p>
     * Additional Suggestions (Optional):
     * 1. Implement a custom comparator for the card values (Think back to one of your midterm
     * questions). Use the enum's ordinals (indexes) to compare the values.
     * <p>
     * 2 (NEW). Write a method that will compare the current hand with some other hand. Start by
     * evaluating the hand type. If two hands have the same type, compare the values. This will
     * help you implement the poker logic in Poker.java.
     * <p>
     * 3. You might want to use a data collection that automatically sorts itself or has a
     * sort() method. Sorting will be determined by how you override the default comparator.
     */

    public List<Card> hand;
    private Type type;
    private Map<Value, Integer> cardCount;
    public Deck game_deck = new Deck();

    private class cardComparator implements Comparator<Card>{
        @Override
        public int compare(Card o1, Card o2) {
            return o1.getValue().ordinal() - o2.getValue().ordinal();
        }
    }


    public enum Type {
        /**
         * The Type enum represents the possible combinations of poker hands.
         * Values are ranked from weakest to strongest (ie. SINGLE < PAIR < TWO PAIRS ..etc)
         * Enums have ordinals, which act like indexes. You can access these to compare rankings.
         */
        SINGLE, // FIVE CARDS WITH DIFFERENT VALUES AND SUITS ARE NOT THE SAME
        PAIR, // TWO CARDS HAVE THE SAME VALUE
        TWO_PAIR, // TWO SETS OF PAIRS
        THREE_OF_A_KIND, // THREE CARDS HAVE THE SAME VALUE
        STRAIGHT, // FIVE CARDS WITH CONSECUTIVE VALUES (ie. SIX, SEVEN, EIGHT, NINE, TEN)
        FLUSH, // FIVE CARDS WITH THE SAME SUIT
        FULL_HOUSE, // THREE OF A KIND + PAIR
        FOUR_OF_A_KIND, // FOUR CARDS HAVE THE SAME VALUE
        STRAIGHT_FLUSH, // FIVE CARDS WITH CONSECUTIVE VALUES AND SAME SUIT
        ROYAL_FLUSH // STRAIGHT FLUSH WITH ACE (ie. TEN OF HEARTS, JACK OF HEARTS, QUEEN OF HEARTS,
        // KING OF HEARTS, ACE OF HEARTS)
    }

    public Hand() {
        this.hand = game_deck.deal_cards();
        Collections.sort(hand, new cardComparator());
        this.type = getTypeHand();
        this.cardCount = countEachCard();
    }

    public List<Hand> create_player_hands(int player_number){
        List<Hand> player_hands = new ArrayList<>();
        for (int i = 0; i < player_number; i++) {
            Hand hand = new Hand();
            player_hands.add(hand);
        }
        return player_hands;
    }

    public Map<Value, Integer> countEachCard() {
        Map<Value, Integer> temp_map = new HashMap<>();
        for (Card card : hand) {
            if(temp_map.containsKey(card.getValue())){
                temp_map.put(card.getValue(), temp_map.get(card.getValue())+1);
            }
            else{
                temp_map.put(card.getValue(), 1);
            }
        }
        return temp_map;
    }

    public boolean straight_check(){
        cardComparator comparator = new cardComparator();
        for(int i = 0; i < 4; i++){
            if (comparator.compare(this.hand.get(i), this.hand.get(i+1)) != -1){
                return false;
            }
        }
        return true;
    }

    public boolean flush_check(){
        for(int i = 0; i< 4; i++){
            if(this.hand.get(i).getSuit() != this.hand.get(i+1).getSuit()){
                return false;
            }
        }
        return true;
    }

    public Type getTypeHand() {
        if (countEachCard().containsValue(2) && countEachCard().keySet().size() == 4) {
            return Type.PAIR;
        } else if (countEachCard().containsValue(3)) {
            if (countEachCard().keySet().size() == 2) {
                return Type.FULL_HOUSE;
            } else {
                return Type.THREE_OF_A_KIND;
            }
        } else if (countEachCard().containsValue(2) && countEachCard().keySet().size() == 3) {
            return Type.TWO_PAIR;
        } else if (countEachCard().containsValue(4)) {
            return Type.FOUR_OF_A_KIND;
        }

        if (straight_check() == true) {
            return Type.STRAIGHT;
        } else if (flush_check() == true) {
            return Type.FLUSH;
        } else if (straight_check() == true && flush_check() == true) {
            if (hand.get(0).getValue() == Value.TEN) {
                return Type.ROYAL_FLUSH;
            } else {
                return Type.STRAIGHT_FLUSH;
            }
        }
        return Type.SINGLE;
    }

    public Value getHighestValue(){
        Value highestvalue = Value.ONE;
        for(Card cards: hand){
            if(cards.getValue().ordinal() > highestvalue.ordinal()){
                highestvalue = cards.getValue();
            }
        }
        return highestvalue;
    }
}


