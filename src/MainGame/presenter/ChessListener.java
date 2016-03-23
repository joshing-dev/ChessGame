package maingame.presenter;

import java.util.EventListener;

public interface ChessListener extends EventListener {
   void pieceMoved(ChessMoveEvent ev);
}
