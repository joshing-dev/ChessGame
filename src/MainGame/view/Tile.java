package maingame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class Tile extends JPanel {

   private static final int SIZE = 64;

   protected abstract Color backColor();

   private int row, col;
   private BufferedImage img;

   public Tile() {
      setMinimumSize(new Dimension(SIZE, SIZE));
      setPreferredSize(new Dimension(SIZE, SIZE));
   }

   public BufferedImage erase() {
      BufferedImage b = img;
      img = null;
      repaint();
      return b;
   }

   public void drawPiece(BufferedImage img, boolean flag) {
      final int W = img.getWidth();
      final int H = img.getHeight();
      if (flag)
         this.img = img.getSubimage(0, 0, W / 2, H);
      else
         this.img = img.getSubimage(W / 2, 0, W / 2, H);
      repaint();
   }

   public void drawPiece(BufferedImage img) {
      this.img = img;
      repaint();
   }

   public void setLoc(int r, int c) {
      row = r;
      col = c;
   }

   public String getLoc() {
      return row + ":" + col;
   }

   @Override
   public void paintComponent(Graphics g) {
      g.setColor(backColor());
      g.fillRect(0, 0, SIZE, SIZE);
      if (img != null) {
         g.drawImage(img, 0, 0, SIZE, SIZE, 0, 0, img.getWidth(),
               img.getHeight(), null);
      }
   }
}
