/**
 * Test code for Deck and Hand.
 */
public class Test {

    public static void main(String[] args) {

        
        Deck deck = new Deck("Deck");
        deck.shuffle();

        EightsHand eightsHand = new EightsHand("Test EightsHand");
        deck.deal(eightsHand, 5);
        eightsHand.display();

        System.out.println("Total penalty of hand: " + eightsHand.scoreHand());
        /* 
        Hand hand = new Hand("Hand");
        deck.deal(hand, 5);
        hand.display();
        
        Hand drawPile = new Hand("Draw Pile");
        deck.dealAll(drawPile);
        System.out.printf("Draw Pile has %d cards.\n",
                          drawPile.size());


        
        */
    }

}
