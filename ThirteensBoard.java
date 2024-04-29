import java.util.ArrayList;

/**
 * The ThirteensBoard class represents the board in a game of Thirteens.
 */
public class ThirteensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

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
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

    /**
     * Creates a new <code>ThirteensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE,RANKS,SUITS,POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Thirteens, the legal groups are (1) a pair of non-face cards
     * whose values add to 13, and (2) a king.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    public boolean isLegal(Integer[] selectedCards) {
        if(selectedCards.length!=2&&selectedCards.length!=1){
            return false;
        }
        else if(selectedCards.length==2){
            return containsPairSum13(selectedCards);
        }
        else if(selectedCards.length == 1){
            return containsKing(selectedCards);
        }
        return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Thirteens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 13, or (2) a king.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    public boolean anotherPlayIsPossible() {
        Integer[] check_pair= new Integer[2];//using array to use contains- methods
        Integer[] check_king = new Integer[1];
        for(int i =0;i<size()-1;i++){//first check if there is pair of 11
            for(int j = i+1;j<size();j++){
                check_pair[0]=i;
                check_pair[1]=j;
                if(containsPairSum13(check_pair)){
                    return true;
                }
            }
        }
        for(int i=0;i<size();i++){//then check if there is king
            check_king[0] = i;
            if(containsKing(check_king)){
                return true;
            }
        }
        return false;
    }

    /**
     * Look for an 13-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return a list of the indexes of an 13-pair, if an 13-pair was found;
     *         an empty list, if an 13-pair was not found.
     */
    private boolean containsPairSum13(Integer[] selectedCards) {
        for(int i = 0;i<selectedCards.length-1;i++){
            for(int j = i+1;j<selectedCards.length;j++){
                try{
                    if(/*cardAt(selectedCards[i])!=null&&cardAt(selectedCards[j])!=null&&*/cardAt(selectedCards[i]).pointValue()+cardAt(selectedCards[j]).pointValue()==13){
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
     * Look for a king in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a king.
     * @return a list of the index of a king, if a king was found;
     *         an empty list, if a king was not found.
     */
    private boolean containsKing(Integer[] selectedCards) {
        for(int i = 0;i<selectedCards.length;i++){
            try{
                if(cardAt(selectedCards[i]).rank().equals("king")){
                    return true;
                }
            }
            catch(Exception e){

            }
        }
        return false;
    }

}
