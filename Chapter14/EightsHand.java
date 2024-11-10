//import java.util.ArrayList;

public class EightsHand extends Hand {

    //private ArrayList<EightsCard> eightsCards;
    
    public EightsHand(String label) {
        super(label);
    }

    public int scoreHand() {
        int totalPenalty = 0;
        for (int i = 0; i < size(); i++) {
            
            totalPenalty += scoreCard(getCard(i));
            
        }
        return (totalPenalty * -1);
    }

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
