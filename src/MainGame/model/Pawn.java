package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class Pawn extends ChessPiece{

    private boolean hasMoved;
    private Player p;
    public Pawn(Player p) {
        super(p);
        this.p = p;
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
            //see if they're black and trying to move their pawn UP the board
            //if(p == Player.BLACK && (m.fromRow - m.toRow < 0)){
            //    return false;
            //}

            //see if they're white and trying to move their pawn DOwN the board
            //if(p == Player.WHITE && (m.toRow - m.fromRow < 0)){
            //    return false;
           // }
            
            //see if they are moving diagonally, one space, and capture
            if((m.toColumn == m.fromColumn+1 || m.toColumn == m.toColumn-1) &&
                    (m.toRow == m.fromRow+1 || m.toRow == m.fromRow-1) &&
                    board[m.toRow][m.toColumn] != null &&
                    board[m.toRow][m.toColumn].player() != this.player()){
                return true;
            }

            //check to see if they are trying to move 2 spaces
            if(!hasMoved && m.toRow == m.fromRow+2 && m.fromColumn == m.toColumn){
                hasMoved = true;
                return true;
            }
            //check to see if they are trying to move 1 space
            if((m.toRow == m.fromRow+1 || m.toRow == m.fromRow-1) &&
                    m.fromColumn == m.toColumn) {
                hasMoved = true;
                return true;
            }
            //I think that's it... for now
            return false;
        }
    }
}