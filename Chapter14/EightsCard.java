/*
Exercise 14.4

When we designed the program for this chapter, we tried to minimize the number of classes. As a result, we ended up with a few awkward methods. For example, cardMatches is a static method in Player, but it would be more natural if it were an instance method in Card.

The problem is that Card is supposed to be useful for any card game, not just Crazy Eights. You can solve this problem by adding a new class, EightsCard, that extends Card and provides a method, match, that checks whether two cards match according to the rules of Crazy Eights.

At the same time, you could create a new class, EightsHand, that extends Hand and provides a method, scoreHand, that adds up the scores of the cards in the hand. And while you’re at it, you could add a method named scoreCard to EightsCard
*/

public class EightsCard extends Card {

    public EightsCard(int rank, int suit) {
        super(rank, suit);
    } // end-constructor

    //checks whether two cards match according to the rules of Crazy Eights.
    public boolean cardMatches(Card that) {
        return this.getSuit() == that.getSuit()
            || this.getRank() == that.getRank()
            || this.getRank() == 8;
    }

    /* 
     * Scores a card based on the rules of Crazy Eights (8s are worth 50, Court Cards are worth 10, all other cards face value.)
     */ 
    public int scoreCard() {
        if (this.getRank() == 8) {
            return 50;
        } else if (this.getRank() > 10) {
            return 10;
        } else {
            return this.getRank();
        }
    }
}
