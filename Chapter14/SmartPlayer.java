/*
 * For Exercise 14.1
 * 
 * A class for a crazy eights player that has different logic for playing cards in hand. Prioritizes playing cards with a higher score value.
 */
public class SmartPlayer extends Player {

    //private String name;
    //private Hand hand;

    public SmartPlayer(String name) {
        super(name);
    }

    @Override public Card play(Eights eights, Card prev) {
        CardCollection playChoices = new CardCollection("The player's possible plays");

        //loop through hand, if a card can be played(cardMatches) add it to the possible plays collection
        for (int i = 0; i < this.getHand().size(); i++) {
            Card card = this.getHand().getCard(i);
            if (cardMatches(card, prev)) {
                playChoices.addCard(card);
            }
        }

        //If a player has no possible plays, draw for a playable card. Otherwise determine the best card to play
        if (playChoices.size() < 1) {
            return drawForMatch(eights, prev);
        } else {
            //Set current best card to first card in playChoices to compare other cards if needed.
            Card bestCard = playChoices.getCard(0);
            //for every card in playChoices, if its score is worth more than current bestCard it becomes the new bestCard
            for (int i = 0; i < playChoices.size(); i++) {
                if (scoreCard(playChoices.getCard(i)) > scoreCard(bestCard)) {
                    bestCard = playChoices.getCard(i);
                }
            }
            //really ugly return, also needed a new method in CardCollection.java(getCardIndex). 
            //Couldn't figure out a better way since I dont use searchForMatch which does the actual removing of the card from player's hand.
            return this.getHand().popCard(this.getHand().getCardIndex(bestCard));
        }
    }

    // Scores a card based on the rules of Crazy Eights (8s are worth 50, Court Cards are worth 10, all other cards face value.)
    // Used to determine which card is worth playing to minimize penalty points.
    private static int scoreCard(Card card) {
        if (card.getRank() == 8) {
            return 50;
        } else if (card.getRank() > 10) {
            return 10;
        } else {
            return card.getRank();
        }
    }
}