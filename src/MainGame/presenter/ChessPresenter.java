package maingame.presenter;

import maingame.model.ChessModel;
import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.Move;
import maingame.view.GUI;
import maingame.view.IView;


public class ChessPresenter {

   private IView view;
   private IChessModel model;

   public ChessPresenter(IView v, IChessModel m) {
      view = v;
      model = m;
      // v.movePiece(1, 4, 3, 4);
      v.setChessMoveListener(moveListener);
      v.showStatusMessage("Welcome! Drag the mouse to move pieces");
   }

   private ChessListener moveListener = new ChessListener() {

      @Override
      public void pieceMoved(ChessMoveEvent ev) {
         Move m = (Move) ev.getSource();
         System.out.println(m.fromRow + " : " + m.fromColumn);
         if (model.isValidMove(m)) {
            model.move(m);
            view.movePiece(m.fromRow, m.fromColumn, m.toRow, m.toColumn);
            view.showStatusMessage(model.currentPlayer().name()
                  + "'s turn to play");
            view.addMoveRecord(loc(m));
         } else
            //view.showErrorMessage("Can't move that piece");
            // STOP WITH THE DIALOG
          System.out.println("Can't do that.");
      }
   };

   private String loc(Move m) {
      return String.valueOf((char) ('A' + m.fromColumn)) + (m.fromRow + 1)
            + " - " + String.valueOf((char) ('A' + m.toColumn)) + (m.toRow + 1);
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      // TODO Replace the null below with an instance of your own chess model
      new ChessPresenter(new GUI(), new ChessModel());
   }

}
