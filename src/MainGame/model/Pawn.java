package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/***********************************************
 * Created by Cameron Sprowls on 3/18/2016.
 *
 * Class for the pawn piece
 **********************************************/
public class Pawn extends ChessPiece{

    private boolean hasMoved;

    public Pawn(Player p) {
        super(p);
        hasMoved = false;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public boolean isValidMove (Move m, IChessPiece[][] board) {
        if (!super.isValidMove(m, board))
            return false;
        else {
        /* add more Pawn-specific validation rules here */
            //see if they are moving diagonally, one space
            if((m.toColumn != m.fromColumn+1 || m.toColumn != m.toColumn-1) &&
                    m.toRow != m.fromRow+1){
                return false;
            }

            //this check needs to go after the other check
            //check to see if they are trying to move 2 spaces
            if((!hasMoved && m.toRow == m.toRow+2) &&
                    m.fromColumn == m.toColumn){
                hasMoved = true;
                return true;
            }
            //check to see if they are trying to move 1 space
            if(m.toRow == m.fromRow+1 &&
                    m.fromColumn == m.toColumn) {
                hasMoved = true;
                return true;
            }



            //I think that's it... for now
            return false;
        }
    }
}
