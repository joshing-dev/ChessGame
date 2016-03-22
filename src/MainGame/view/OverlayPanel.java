package MainGame.view;

import gvprojects.chess.model.Move;
import MainGame.presenter.ChessMoveEvent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class OverlayPanel extends JComponent {

   private int x1, y1, x2, y2;
   private Stroke brush;
   private JPanel underneath;
   private int sx, sy, dx, dy;

   public OverlayPanel(JPanel p) {
      brush = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
      addMouseListener(mousy);
      addMouseMotionListener(mousy);
      underneath = p;
      x1 = y1 = x2 = y2 = -1;
   }

   private MouseAdapter mousy = new MouseAdapter() {

      private int[] parsePosition(String x) {
         int[] vals = { 0, 0 };
         String[] toks = x.split(":");
         vals[0] = Integer.parseInt(toks[0]);
         vals[1] = Integer.parseInt(toks[1]);
         return vals;
      }

      @Override
      public void mousePressed(MouseEvent ev) {
         x1 = ev.getX();
         y1 = ev.getY();
         Component c = underneath.getComponentAt(x1, y1);
         if (c instanceof Tile) {
            Tile t = (Tile) c;
            int pos[] = parsePosition(t.getLoc());
            sx = pos[0];
            sy = pos[1];
         } else
            x1 = y1 = -1;
      }

      @Override
      public void mouseDragged(MouseEvent ev) {
         x2 = ev.getX();
         y2 = ev.getY();
         Component c = underneath.getComponentAt(x2, y2);
         if (c instanceof Tile) {
            Tile t = (Tile) c;
            int pos[] = parsePosition(t.getLoc());
            dx = pos[0];
            dy = pos[1];
            repaint();
         } else
            x2 = y2 = -1;
      }

      @Override
      public void mouseReleased(MouseEvent ev) {
         ChessMoveEvent mev = new ChessMoveEvent(new Move(sx, sy, dx, dy));
         firePropertyChange("move", null, mev);
         x1 = y1 = x2 = y2 = -1;
         repaint();
      }
   };

   @Override
   protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      if (x1 != -1 && x2 != -1) {
         g2.setStroke(brush);
         g2.setColor(Color.RED);
         g2.translate(x1, y1);
         g2.rotate(Math.atan2(y2 - y1, x2 - x1));
         int L = (int) Math.hypot(x2 - x1, y2 - y1);
         g.drawLine(0, 0, L, 0);
         g.drawLine(L - 20, 15, L, 0);
         g.drawLine(L - 20, -15, L, 0);
      }
   }

}
