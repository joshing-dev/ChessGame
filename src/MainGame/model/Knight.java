package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**
 * Created by Eldridge on 3/19/2016.
 */
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
        return false;
        }

}
