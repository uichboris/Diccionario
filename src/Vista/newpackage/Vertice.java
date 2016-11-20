/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.newpackage;

/**
 *
 * @author jfwc1
 */
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class Vertice extends JComponent{
    
//    public Vertice(){
//        repaint();
//    }
    
   public void paintComponent(Graphics g)
    {
        Graphics2D g2=(Graphics2D)g;
      
//      draw Ellipse2D.Double g2.draw(new Ellipse2D.Double(x, y,rectwidth,rectheight));
        Ellipse2D.Double elipse=new Ellipse2D.Double(10,10,50,20);
        g2.draw(elipse);
        g2.fill(elipse);
        
        
//        g.drawString("x",60,200);
//        g.drawString("Segunda linea",70,300);
    }
   
   
//    public void paint (Graphics g)
//    {
//        super.paint(g);
//
//        g.setColor (Color.blue);
//        g.drawLine (0, 70, 100, 70);
//        g.drawRect (150, 70, 50, 70);
//        g.drawRoundRect (250, 70, 50, 70, 6, 6);
//        g.drawOval (350, 70, 50, 70);
//        int [] vx1 = {500, 550, 450};
//        int [] vy1 = {70, 120, 120};
//        g.drawPolygon (vx1, vy1, 3);
//
//        g.setColor (Color.red);
//        g.fillRect (150, 270, 50, 70);
//        g.fillRoundRect (250, 270, 50, 70, 6, 6);
//        g.fillOval (350, 270, 50, 70);
//        int [] vx2 = {500, 550, 450};
//        int [] vy2 = {270, 320, 320};
//        g.fillPolygon (vx2, vy2, 3);
//    }
}
