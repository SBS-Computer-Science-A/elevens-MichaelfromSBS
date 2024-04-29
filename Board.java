import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class Board {

    /**
     * The cards on this board.
     */
    private Card[] cards;

    /**
     * The deck of cards being used to play the current game.
     */
    private Deck deck;

    private int size;
    /**
     * Creates a new <code>Board</code> instance.
     */
    public Board(int size, String[] ranks, String[] suits, int[] pointValues) {
        deck = new Deck(ranks, suits, pointValues);
        this.size = size;
        cards = new Card[size];
        newGame();
    }

    /**
     * Start a new game by shuffling the deck and
     * dealing some cards to this board.
     */
    public void newGame() {
        deck.shuffle();
        dealMyCards();
    }

    /**
     * Accesses the size of the board.
     * Note that this is not the number of cards it contains,
     * which will be smaller near the end of a winning game.
     * @return the size of the board
     */
    public int size() {
        return size;
    }

    /**
     * Determines if the board is empty (has no cards).
     * @return true if this board is empty; false otherwise.
     */
    public boolean isEmpty() {
        if(cards.length==0){
            return true;
        }
        return false;
    }

    /**
     * Deal a card to the kth position in this board.
     * If the deck is empty, the kth card is set to null.
     * @param k the index of the card to be dealt.
     */
    public void deal(int k) {
        if(deck.size()==0){
            cards[k] = null;
        }
        else{
            cards[k] = deck.deal();
        }
//        for(int i = 0;i<cards.length;i++){
//            try{
//                if(cards[i].rank()!=null){
//                    System.out.print(cards[i].rank() + " ");
//                }
//            }
//            catch(Exception e){
//                System.out.print("null ");
//            }
//        }
//        System.out.println();
        System.out.println(all_Null() + " " + deck.isEmpty());
    }

    public boolean all_Null(){//a way of checking if the board is empty, used to check if the game is won
        for(int i = 0;i<cards.length;i++){
            try{
                if(cards[i]!=null){
                    return false;
                }
            }
            catch(Exception e){

            }
        }
        return true;
    }
    /**
     * Accesses the deck's size.
     * @return the number of undealt cards left in the deck.
     */
    public int deckSize() {
        return deck.size();
    }

    /**
     * Accesses a card on the board.
     * @return the card at position k on the board.
     * @param k is the board position of the card to return.
     */
    public Card cardAt(int k) {
        return cards[k];
    }

    /**
     * Replaces selected cards on the board by dealing new cards.
     * @param selectedCards is a list of the indices of the
     *        cards to be replaced.
     */
    public void replaceSelectedCards(Integer[] selectedCards) {
        for(int i = 0;i<selectedCards.length;i++){
            deal(selectedCards[i]);
        }
    }

    /**
     * Gets the indexes of the actual (non-null) cards on the board.
     *
     * @return a List that contains the locations (indexes)
     *         of the non-null entries on the board.
     */
    public Integer[] cardIndexes() {
        int num = 0;
        for(int i =0;i<size;i++){
            if(cards[i]!=null){
                num++;
            }
        }
        Integer[] valid = new Integer[num];
        num = 0;
        for(int i = 0;i<size;i++){
            if(cards[i]!=null){
                valid[num] = i;
                num++;
            }
        }
        return valid;
    }

    /**
     * Generates and returns a string representation of this board.
     * @return the string version of this board.
     */
    public String toString() {
        String s = "";
        for (int k = 0; k < cards.length; k++) {
            s = s + k + ": " + cards[k] + "\n";
        }
        return s;
    }

    /**
     * Determine whether or not the game has been won,
     * i.e. neither the board nor the deck has any more cards.
     * @return true when the current game has been won;
     *         false otherwise.
     */
    public boolean gameIsWon() {
        return (all_Null()&& deck.isEmpty());
    }

    /**
     * @return true if the selected indices are all on the board (i.e. 
     * selectedCards is not empty or null, and it doesn't have negative
     * or too-big indices;
     *         false otherwise.
     */
    public boolean isLegal(Integer[] selectedCards) {
        for(int i =0;i<selectedCards.length;i++){
            if(selectedCards[i]==null||selectedCards[i]<0||selectedCards[i]>=size){
                return false;
            }
        }
        return true;
    }


    /**
     * @return true if there is at least one card left on the board;
     *         false otherwise.
     */
    public boolean anotherPlayIsPossible() {
        return cards.length>=1;
    }


    /**
     * Deal cards to this board to start the game.
     */
    private void dealMyCards() {
        for(int i = 0;i<size();i++){
            cards[i] = deck.deal();
        }
    }

}
