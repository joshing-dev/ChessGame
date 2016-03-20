package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/19/2016.
 **********************************************************/
public class Knight extends ChessPiece {

    public Knight(Player p) {
        super(p);
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public boolean isValidMove (Move m, IChessPiece[][] board) {
        if (!super.isValidMove(m, board))
            return false;
        // 2 left/right + 1 up/down
        // 2 up/down + 1 left/right

        // Test left first
        if(m.fromColumn == m.toColumn - 2 && (m.fromRow == m.toRow -1 || m.fromRow == m.toRow + 1 ))
            return true;
        // Test right second
        if(m.fromColumn == m.toColumn + 2 && (m.fromRow == m.toRow -1 || m.fromRow == m.toRow + 1 ))
            return true;
        // Test up third
        if(m.fromRow == m.toRow - 2 && (m.fromColumn == m.toColumn -1 || m.fromColumn == m.toColumn + 1 ))
            return true;
        // Test down fourth
        if(m.fromRow == m.toRow + 2 && (m.fromColumn == m.toColumn -1 || m.fromColumn == m.toColumn + 1 ))
            return true;

        return false;


        }

}
