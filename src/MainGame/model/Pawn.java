package maingame.model;

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
            if(p == Player.BLACK && (m.fromRow - m.toRow < 0)){
                return false;
            }

            //see if they're white and trying to move their pawn DOwN the board
            if(p == Player.WHITE && (m.toRow - m.fromRow < 0)){
                return false;
            }
            
            //see if they are moving diagonally, one space, and capture
            if((Math.abs(m.fromColumn - m.toColumn) == 1) && (Math.abs(m.fromRow - m.toRow) == 1) &&
                    board[m.toRow][m.toColumn] != null &&
                    board[m.toRow][m.toColumn].player() != this.player()){
                return true;
            }

            //check to see if they are trying to move 2 spaces
            if(!hasMoved &&
                    board[m.fromRow][m.fromColumn].player() == Player.BLACK && m.toRow == m.fromRow-2 &&
                    m.fromColumn == m.toColumn &&
                    board[m.toRow][m.toColumn] == null)
            {
                if(board[m.toRow][m.toColumn] == this)
                hasMoved = true;
                return true;
            }
            if(!hasMoved &&
                    board[m.fromRow][m.fromColumn].player() == Player.WHITE && m.toRow == m.fromRow+2 &&
                    m.fromColumn == m.toColumn &&
                    board[m.toRow][m.toColumn] == null)
            {
                if(board[m.toRow][m.toColumn] == this)
                hasMoved = true;
                return true;
            }

            //check to see if they are trying to move 1 space
            if((m.toRow == m.fromRow+1 || m.toRow == m.fromRow-1) &&
                    m.fromColumn == m.toColumn
                    && board[m.toRow][m.toColumn] == null) {
                hasMoved = true;
                return true;
            }
            return false;
        }
    }
}