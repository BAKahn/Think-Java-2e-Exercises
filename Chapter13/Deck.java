import java.util.Random;

/**
 * A deck of playing cards (of fixed length).
 */
public class Deck {

    // This is a class variable so we don't have to create
    // a new Random object every time we call randomInt.
    private static Random random = new Random();

    private Card[] cards;

    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    /**
     * Constructs a deck of n cards (all null).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }

    /**
     * Gets the internal cards array.
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Displays each of the cards in the deck.
     */
    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }

    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        StringBuilder deckString = new StringBuilder();
        for (Card card : this.cards) {
            deckString.append(card);
            deckString.append('\n');
        }
        return deckString.toString();
    }

    /**
     * Randomly permutes the array of cards.
     * 
     * choose a random number between i and length - 1
     * swap the ith card and the randomly-chosen card
     */
    public void shuffle() {
        for (int i = 0; i < this.cards.length; i++) {
            swapCards(i, randomInt(i, (this.cards.length -1)));            
        }
    }

    /**
     * Chooses a random number between low and high, including both.
     */
    private static int randomInt(int low, int high) {
        int randomInt = random.nextInt(high - low + 1) + low;
        return randomInt;
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    private void swapCards(int i, int j) {
        Card temp = this.cards[i];
        this.cards[i] = this.cards[j];
        this.cards[j] = temp;
    }

    /**
     * Sorts the cards (in place) using selection sort.
     */
    public void selectionSort() {
        for (int i = 0; i < this.cards.length; i++) {
            //find the index of lowest card from current card to all other cards to the right.
            int lowest = this.indexLowest(i, (this.cards.length - 1));
            swapCards(i, lowest);
        }
    }

    /**
     * Finds the index of the lowest card
     * between low and high inclusive.
     */
    private int indexLowest(int low, int high) {

        //for tracking lowest card and its index throughout loop. Intailized as the supplied low in case the first card/index is the lowest.
        int lowestIndex = low;
        Card lowestCard = this.cards[low];

        // look at each card to the right of low through and including high.
        for (int i = low + 1; i <= high; i++) {
            //compare current card in the loop to the stored lowest, set it to the new lowest if lower.
            if (this.cards[i].compareTo(lowestCard) < 0) {
                lowestIndex = i;
                lowestCard = this.cards[i];
            }
        }
        return lowestIndex;
    }

    /**
     * Returns a subset of the cards in the deck.
     */
    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }

    /**
     * Combines two previously sorted subdecks.
     */
    private static Deck merge(Deck d1, Deck d2) {
        // create a new deck, d3, big enough for all the cards
        Deck d3 = new Deck(d1.cards.length + d2.cards.length);

        // use the index i to keep track of where we are at in
        // the first deck, and the index j for the second deck
        int i = 0;
        int j = 0;

        // the index k traverses the result deck
        // increment i or j (depending on card)
        for (int k = 0; k < d3.cards.length; k++) {
            // if d1 is empty, use top card from d2
            if (i == d1.cards.length) {
                d3.cards[k] = d2.cards[j];
                j++;
            // if d2 is empty, use top card from d1    
            } else if (j == d2.cards.length) {
                d3.cards[k] = d1.cards[i];
                i++;
            // otherwise, compare the top two cards
            // add lowest card to the new deck at k
            } else if (d1.cards[i].compareTo(d2.cards[j]) < 0) {
                d3.cards[k] = d1.cards[i];
                i++;
            } else {
                d3.cards[k] = d2.cards[j];
                j++;
            }
        }
        return d3;
    }

    /**
     * Returns a sorted copy of the deck using selection sort.
     */
    public Deck almostMergeSort() {
        // still works with decks with an odd number of cards.
        int midIndex = this.cards.length / 2;
        // divide the deck into two subdecks
        // d2 will have 1 more card than d1 for odd decks. 
        Deck d1 = this.subdeck(0, (midIndex - 1));
        Deck d2 = this.subdeck(midIndex, (this.cards.length - 1));

        // sort the subdecks using selectionSort
        d1.selectionSort();
        d2.selectionSort();
        // merge the subdecks, return the result
        return merge(d1, d2);
    }

    /**
     * Returns a sorted copy of the deck using merge sort.
     */
    public Deck mergeSort() {
        // if the deck has 0 or 1 cards, return it
        if (this.cards.length <= 1) {
            return this;
        }

        // otherwise, divide the deck into two subdecks
        int midIndex = this.cards.length / 2;
        // d2 will have 1 more card than d1 for odd decks. 
        Deck d1 = this.subdeck(0, (midIndex - 1));
        Deck d2 = this.subdeck(midIndex, (this.cards.length - 1));

        // sort the subdecks using mergeSort
        d1 = d1.mergeSort();
        d2 = d2.mergeSort();
        
        // merge the subdecks
        // return the result
        return merge(d1, d2);
    }

    /**
     * Reorders the cards (in place) using insertion sort.
     */
    public void insertionSort() {
    }

    /**
     * Helper method for insertion sort.
     */
    private void insert(Card card, int i) {
    }

}
