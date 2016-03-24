package maingame.presenter;

import gvprojects.chess.model.Move;

import java.awt.AWTEvent;

public class ChessMoveEvent extends AWTEvent {

   public ChessMoveEvent(Move m) {
      super(m, 0);
   }

}
