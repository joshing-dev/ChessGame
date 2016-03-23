package maingame.view;



import maingame.presenter.ChessListener;
import maingame.presenter.ChessMoveEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI implements IView {

   /* TODO: change the path to the chess piece images */
   private final String IMAGE_PATH = "Images/";
   private JFrame top;
   private Tile[][] tiles;
   private BufferedImage img;
   private ChessListener clistener;
   private MovePanel mp;
   private JLabel status;

   public GUI() {
      top = new JFrame();
      JPanel board = new JPanel();
      mp = new MovePanel();
      status = new JLabel();
      status.setBorder(BorderFactory.createTitledBorder("..."));
      top.add(status, BorderLayout.SOUTH);
      board.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(Color.BLACK, 3)));
      // ctr.setPreferredSize(new Dimension(800,800));
      board.setLayout(new GridLayout(8, 0));
      File imgFile = new File(IMAGE_PATH + "Pawn.png");
      try {
         img = ImageIO.read(imgFile);
         tiles = new Tile[8][8];
         for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
               tiles[i][j] = (i + j) % 2 != 0 ? new WhiteTile()
                     : new DarkTile();
               tiles[i][j].setLoc(i, j);
            }
         for (int i = 7; i >= 0; i--) {
            // center.add(new JLabel(String.valueOf(i+1)));
            for (int j = 0; j < 8; j++) {
               board.add(tiles[i][j]);
            }
         }
         for (int k = 0; k < 8; k++) {
            tiles[1][k].drawPiece(img, true);
            tiles[6][k].drawPiece(img, false);
         }
         imgFile = new File(IMAGE_PATH + "Rook.png");
         img = ImageIO.read(imgFile);
         tiles[0][0].drawPiece(img, true);
         tiles[0][7].drawPiece(img, true);
         tiles[7][0].drawPiece(img, false);
         tiles[7][7].drawPiece(img, false);

         imgFile = new File(IMAGE_PATH + "Knight.png");
         img = ImageIO.read(imgFile);
         tiles[0][1].drawPiece(img, true);
         tiles[0][6].drawPiece(img, true);
         tiles[7][1].drawPiece(img, false);
         tiles[7][6].drawPiece(img, false);

         imgFile = new File(IMAGE_PATH + "Bishop.png");
         img = ImageIO.read(imgFile);
         tiles[0][2].drawPiece(img, true);
         tiles[0][5].drawPiece(img, true);
         tiles[7][2].drawPiece(img, false);
         tiles[7][5].drawPiece(img, false);

         imgFile = new File(IMAGE_PATH + "Queen.png");
         img = ImageIO.read(imgFile);
         tiles[0][3].drawPiece(img, true);
         tiles[7][3].drawPiece(img, false);

         imgFile = new File(IMAGE_PATH + "King.png");
         img = ImageIO.read(imgFile);
         tiles[0][4].drawPiece(img, true);
         tiles[7][4].drawPiece(img, false);

         // top.add(centerBack, BorderLayout.CENTER);
         top.add(board, BorderLayout.CENTER);
         top.add(mp, BorderLayout.EAST);
         OverlayPanel over = new OverlayPanel(board);
         top.setGlassPane(over);
         over.setVisible(true);
         over.addPropertyChangeListener("move", proper);
         top.pack();
         top.setVisible(true);
         top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      } catch (IOException e) {
         JOptionPane.showMessageDialog(top, "Be sure to set image path correctly");
      }
   }

   private PropertyChangeListener proper = new PropertyChangeListener() {

      @Override
      public void propertyChange(PropertyChangeEvent evt) {
         Object obj = evt.getNewValue();
         if (clistener != null)
            clistener.pieceMoved((ChessMoveEvent) obj);
      }
   };

   @Override
   public void movePiece(int r1, int c1, int r2, int c2) {
      BufferedImage old = tiles[r1][c1].erase();
      tiles[r2][c2].drawPiece(old);
   }

   @Override
   public void setChessMoveListener(ChessListener listener) {
      clistener = listener;
   }

   @Override
   public void showStatusMessage(String m) {
      status.setText(m);
   }

   @Override
   public void showErrorMessage(String msg) {
      JOptionPane.showMessageDialog(top, msg);
   }

   @Override
   public void addMoveRecord(String str) {
      mp.addMove(str);
   }

}
