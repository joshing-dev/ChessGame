package gvprojects.chess.presenter;

import java.util.EventListener;

public interface ChessListener extends EventListener {
   void pieceMoved(ChessMoveEvent ev);
}
