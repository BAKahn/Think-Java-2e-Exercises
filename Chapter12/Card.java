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
import java.util.Arrays;

public class Card {

    private final int rank;
    private final int suit;

    public Card() {
        rank = 0;
        suit = 0;
    } // end-default constructor

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    } // end-constructor

    public static final String[] RANKS = {
        null, "Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};

    public static final String[] SUITS = {
        "Clubs", "Diamonds", "Hearts", "Spades"};

    public String toString() {
            return RANKS[this.rank] + " of " + SUITS[this.suit];
    } // end-toString

    public boolean equals(Card that) {
        return this.rank == that.rank
               && this.suit == that.suit;
    } // end-equals

    public int compareTo(Card that) {
        // 12.2 - - - 
        int thisRank, thatRank;
        thisRank = this.getRank();
        thatRank = that.getRank();
        if (thisRank == 1) thisRank = 20;
        if (thatRank == 1) thatRank = 20;

        if (this.suit < that.suit) {
                return -1;
            } 
        if (this.suit > that.suit) {
            return 1;
        }
        if (thisRank < thatRank) {
            return -1;
        }
        if (thisRank > thatRank) {
            return 1;
        }
        return 0;
    } // end-compareTo

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }

    //12.1 - makeDeck
    public static Card[] makeDeck() {
        Card[] deck = new Card[52]; 
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck[index] = new Card(rank, suit);
                index = index + 1;
            } // end-for rank
        } // end-for suit
        return deck;
    } // end-makeDeck

    //12.3 - suitHist and hasFlush
    public static int[] suitHist(Card[] hand) {
        int[] suitHist = new int[4];

        // for each card in the Card array (hand), increment an integer in the histogram array based on the suit.
        for (int i = 0; i < hand.length; i++) {
            switch (hand[i].getSuit()) {
                case 0:
                    suitHist[0]++;
                    break;
                case 1:
                    suitHist[1]++;
                    break;
                case 2:
                    suitHist[2]++;
                    break;
                case 3:
                    suitHist[3]++;
                    break;
            }
        }
        return suitHist;
    }

    public static boolean hasFlush(Card[] hand) {
        //suit histogram
        int[] suitHist = suitHist(hand);

        //if the suit histogram contains a 5 or higher, return true
        for(int i = 0; i < suitHist.length; i++) {
            if (suitHist[i] >= 5) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //test makedeck
        //Card[] testDeck = makeDeck();
        //System.out.println(Arrays.toString(testDeck));

        //test suitHist
        //Create cards for the test hand.
        Card card1 = new Card(2, 1); //2 of Diamonds
        Card card2 = new Card(4, 1); //4 of Diamonds
        Card card3 = new Card(7, 1); //7 of Diamonds
        Card card4 = new Card(10, 1); //10 of Diamonds
        Card card5 = new Card(12, 1); //Queen of Diamonds
        //Create "hand" array
        Card[] testhand = {card1, card2, card3, card4, card5};

        System.out.println(Arrays.toString(testhand));
        System.out.println(Arrays.toString(suitHist(testhand)));

        //test hasFlush
        System.out.println("Flush?: " + hasFlush(testhand));
    }

} // end-class Card
