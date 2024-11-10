/**
 * Simulates a simple card game.
 */
public class War {

    public static void main(String[] args) {

        // create and shuffle the deck
        Deck deck = new Deck();
        deck.shuffle();

        // divide the deck into piles
        Pile p1 = new Pile();
        p1.addDeck(deck.subdeck(0, 25));
        Pile p2 = new Pile();
        p2.addDeck(deck.subdeck(26, 51));

        // while both piles are not empty
        mainloop:
        while (!p1.isEmpty() && !p2.isEmpty()) {
            Card c1 = p1.popCard();
            Card c2 = p2.popCard();

            //for testing/visualizing behavior
            System.out.println(c1 + "\n" + c2 + "\n" + "p1 size: " + p1.size() + "\n" + "p2 size: " + p2.size() + "\n");
            

            // compare the cards
            int diff = c1.getRank() - c2.getRank();
            if (diff > 0) {
                p1.addCard(c1);
                p1.addCard(c2);
            } else if (diff < 0) {
                p2.addCard(c1);
                p2.addCard(c2);
            } else {
                // it's a tie...draw four more cards
                Pile tiePile = new Pile();
                tiePile.addCard(c1);
                tiePile.addCard(c2);

                //loop the tie breaker until played cards do not result in a tie
                do {
                    //If one pile does not have at least four cards, the game ends immediately.
                    if (p1.size() < 4) {
                        System.out.println("Player 2 wins!");
                        break mainloop;
                    } else if (p2.size() < 4) {
                        System.out.println("Player 1 wins!");
                        break mainloop;
                    }
                    //players add 4 cards to tiePile and play 4th card
                    tiePile.addCard(p1.popCard());
                    tiePile.addCard(p1.popCard());
                    tiePile.addCard(p1.popCard());
                    c1 = p1.popCard();
                    tiePile.addCard(c1);

                    tiePile.addCard(p2.popCard());
                    tiePile.addCard(p2.popCard());
                    tiePile.addCard(p2.popCard());
                    c2 = p2.popCard();
                    tiePile.addCard(c2);
                } while (c1.getRank() - c2.getRank() == 0);

                //determine winner of the tie pile
                diff = c1.getRank() - c2.getRank();
                //needed because arrayList size changes dynamically... boy did that stump me for a bit.
                int tieSize = tiePile.size();

                if (diff > 0) {
                    //for every card in tie pile, add it to winner's pile
                    for (int i = 0; i < tieSize; i++) {
                        p1.addCard(tiePile.popCard());
                    }
                } else {
                    for (int i = 0; i < tieSize; i++) {
                        p2.addCard(tiePile.popCard());
                    }
                }
            }
        }

        // display the winner
        if (p2.isEmpty()) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
    }

}
