import java.util.Scanner;
import java.util.ArrayList;
/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    //private ArrayList<Player> players; -add import java.util.ArrayList;
    private ArrayList<Player> players;
    //private SmartPlayer one;
    //private Player two;
    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;


    /**
     * Initializes the state of the game.
     */
    public Eights(int playerNum) {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        this.players = new ArrayList<>();

        // deal cards to each player
        for (int i = 0; i < playerNum; i++) {
            Player player = new Player("Player" + (i + 1));
            deck.deal(player.getHand(), 5);
            players.add(player);
        }

        //one = new SmartPlayer("Allen");
        //deck.deal(one.getHand(), 5);
        //two = new Player("Chris");
        //deck.deal(two.getHand(), 5);
        

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        in = new Scanner(System.in);
    }

    /**
     * Returns true if any hand is empty.
     */
    public boolean isDone() {
        //return one.getHand().isEmpty() || two.getHand().isEmpty();
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        int currnetIndex = players.indexOf(current);
        if (currnetIndex == players.size() - 1) {
            return players.get(0);
        } else {
            return players.get(currnetIndex + 1);
        }

        //if (current == one) {
        //    return two;
        //} else {
        //    return one;
        //}
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        //one.display();
        //two.display();
        for (Player player : players) {
            player.display();
        }
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.lastCard();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        Player activePlayer = players.get(0);

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            takeTurn(activePlayer);
            activePlayer = nextPlayer(activePlayer);
        }

        // display the final score
        //one.displayScore();
        //two.displayScore();
        for (Player player : players) {
            player.displayScore();
        }
    }

    /**
     * Creates the game and runs it.
     
    public static void main(String[] args) {
        Eights game = new Eights(4);
        game.playGame();
    }
*/
}
