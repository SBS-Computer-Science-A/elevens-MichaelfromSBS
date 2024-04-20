import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck {

    /**
     * cards contains all the cards in the deck.
     */
    private ArrayList<Card> cards = new ArrayList<>();

    /**
     * size is the number of not-yet-dealt cards.
     * Cards are dealt from the top (highest index) down.
     * The next card to be dealt is at size - 1.
     */
    private int size;
    private String[] ranks1;
    private String[] suits1;
    private int[] values1;


    /**
     * Creates a new <code>Deck</code> instance.<BR>
     * It pairs each element of ranks with each element of suits,
     * and produces one of the corresponding card.
     * @param ranks is an array containing all of the card ranks.
     * @param suits is an array containing all of the card suits.
     * @param values is an array containing all of the card point values.
     */
    public Deck(String[] ranks, String[] suits, int[] values) {
        ranks1 = ranks;
        suits1 = suits;
        values1 = values;
        for(int i = 0;i<suits.length;i++){
            for(int j = 0;j<ranks.length;j++){
                cards.add(new Card(ranks[j],suits[i],values[j]));
                size++;
            }
        }
    }


    /**
     * Determines if this deck is empty (no undealt cards).
     * @return true if this deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Accesses the number of undealt cards in this deck.
     * @return the number of undealt cards in this deck.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Apply an "efficient selection shuffle" to the argument.
     * The selection shuffle algorithm conceptually maintains two sequences
     * of cards: the selected cards (initially empty) and the not-yet-selected
     * cards (initially the entire deck). It repeatedly does the following until
     * all cards have been selected: randomly remove a card from those not yet
     * selected and add it to the selected cards.
     * An efficient version of this algorithm makes use of arrays/arraylists to avoid
     * searching for an as-yet-unselected card.
     */
    public void shuffle() {
        ArrayList<Card> selected = new ArrayList<>();
        size = 0;
        while(!cards.isEmpty()){
            cards.remove(0);
        }
        for(int i = 0;i<suits1.length;i++){//generate the full deck again
            for(int j = 0;j<ranks1.length;j++){
                cards.add(new Card(ranks1[j],suits1[i],values1[j]));
                size++;
            }
        }
        int length = cards.size();
        for(int i=0;i<length;i++){
            int place = (int)(cards.size()*Math.random());
            selected.add(cards.get(place));
            cards.remove(place);
        }
        for(int i = length-1;i>=0;i--){
            cards.add(selected.get(i));
            selected.remove(i);
        }
    }

    /**
     * Deals a card from this deck.
     * @return the card just dealt, or null if all the cards have been
     *         previously dealt.
     */
    public Card deal() {
        if(cards.isEmpty()){
            return null;
        }
        size--;
        return cards.remove(0);
    }

    /**
     * Generates and returns a string representation of this deck.
     * @return a string representation of this deck.
     */
    @Override
    public String toString() {
        String rtn = "size = " + size + "\nUndealt cards: \n";

        for (int k = size - 1; k >= 0; k--) {
            rtn = rtn + cards.get(k);
            if (k != 0) {
                rtn = rtn + ", ";
            }
            if ((size - k) % 2 == 0) {
                // Insert carriage returns so entire deck is visible on console.
                rtn = rtn + "\n";
            }
        }

        rtn = rtn + "\nDealt cards: \n";
        for (int k = cards.size() - 1; k >= size; k--) {
            rtn = rtn + cards.get(k);
            if (k != size) {
                rtn = rtn + ", ";
            }
            if ((k - cards.size()) % 2 == 0) {
                // Insert carriage returns so entire deck is visible on console.
                rtn = rtn + "\n";
            }
        }

        rtn = rtn + "\n";
        return rtn;
    }

}
