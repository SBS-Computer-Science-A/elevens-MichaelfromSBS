import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board{

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
        {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
        {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};



    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE,RANKS,SUITS,POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    public boolean isLegal(Integer[] selectedCards) {
        if(selectedCards.length!=2&&selectedCards.length!=3){
            return false;
        }
        else if(selectedCards.length==2){
            return containsPairSum11(selectedCards);
        }
        else if(selectedCards.length == 3){
            return containsJQK(selectedCards);
        }
        return false;
    }


    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    public boolean anotherPlayIsPossible() {
        Integer[] check_pair = new Integer[2];
        Integer[] check_JQK = new Integer[3];
        for(int i =0;i<size()-1;i++){//first check if there is pair of 11
            for(int j = i+1;j<size();j++){
                check_pair[0]=i;
                check_pair[1]=j;
                if(containsPairSum11(check_pair)){
                    return true;
                }
            }
        }
        for(int i=0;i<size()-2;i++){
            for(int j = 0;j<size()-1;j++){
                for(int k = 0;k<size();k++){
                    check_JQK[0] = i;
                    check_JQK[1] = j;
                    check_JQK[2] = k;
                    if(containsJQK(check_JQK)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Deal cards to this board to start the game.
     */

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(Integer[] selectedCards) {
        for(int i = 0;i<selectedCards.length-1;i++){
            for(int j = i+1;j<selectedCards.length;j++){
                try{
                    if(/*cardAt(selectedCards[i])!=null&&cardAt(selectedCards[j])!=null&&*/cardAt(selectedCards[i]).pointValue()+cardAt(selectedCards[j]).pointValue()==11){
                        return true;
                    }
                }
                catch (Exception e){

                }
//                if(cardAt(selectedCards[i])!=null&&cardAt(selectedCards[j])!=null&&cardAt(selectedCards[i]).pointValue()+cardAt(selectedCards[j]).pointValue()==11){
//                    return true;
//                }
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(Integer[] selectedCards) {
        for(int i = 0;i<selectedCards.length;i++){
            try{
                if(cardAt(selectedCards[i]).rank().equals("jack")){
                    for(int j = 0;j<selectedCards.length;j++){
                        try{
                            if(cardAt(selectedCards[j]).rank().equals("queen")){
                                for(int k = 0; k<selectedCards.length;k++){
                                    try{
                                        if(cardAt((selectedCards[k])).rank().equals("king")){
                                            return true;
                                        }
                                    }
                                    catch(Exception e){

                                    }
                                }
                                return false;
                            }
                        }
                        catch(Exception e){

                        }
                    }
                    return false;
                }
            }
            catch(Exception e){

            }
        }
        return false;
    }
}
