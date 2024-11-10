/*
Exercise 12.1:
Encapsulate the deck-building code from Section 12.6 in a method called makeDeck that takes no parameters and returns a fully-populated array of Cards.

Exercise 12.2:
In some card games, Aces are ranked higher than Kings. Modify the compareTo method to implement this ordering.

Exercise 12.3:
In Poker a “flush” is a hand that contains five or more cards of the same suit. A hand can contain any number of cards.

    1. Write a method called suitHist that takes an array of cards as a parameter and that returns a histogram of the suits in the hand. Your solution should only traverse the array once as in Section 7.7.

    2. Write a method called hasFlush that takes an array of cards as a parameter and returns true if the hand contains a flush (and false otherwise).
*/
public class CardDeck {

    // instance variables . . . 
    Card[] cards = new Card[52];

    // default constructor
    public CardDeck() {

        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(rank, suit);
                index = index + 1;
            } // end-for rank
        } // end-for suit

    } // end constructor . . . 

    @Override
    public String toString() {

        int index = 0;
        Card crd;
        String thesuit, therank, retstr;
        retstr = "";
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                crd = cards[index];
                thesuit = Card.SUITS[crd.getSuit()];
                therank = Card.RANKS[crd.getRank()];
                retstr += (therank + " of " + thesuit + " : ");
                index = index + 1;
            } // end-for rank
            retstr += "\n";
        } // end-for suit

        return retstr;

    } // end-toString

} // end-class CardDeck
