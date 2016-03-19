package MainGame.test;

import MainGame.model.Pawn;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class PawnTest extends ChessPieceTest{
    protected IChessPiece make(Player p) {
        return new Pawn(p);
    }

    @Override
    protected Move getValidMove(int row, int col) {
        int newRow = row + 1;
        if (newRow >= board.length) {
            newRow = row - 1;
        }
        return new Move(row, col, newRow, col);
    }
}
