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

 
import java.lang.Object;
// org.jgrapht.Graphs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class dibujo extends JFrame {

    private JPanel contentPane;
    private String  x="hola mundo";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    dibujo frame = new dibujo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public dibujo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setBounds(0,0,800,600);
    }
    
    
     public void paint (Graphics g)
    {
       super.paint(g);
        g.drawString(x,10,200);
        g.drawString("Segunda linea",10,300);
    }

    
//    public void paint (Graphics g)
//    {
//       super.paint(g);
//        g.setColor (Color.blue);
//        g.drawString("Primer linea",10,200);
//        g.drawString("Segunda linea",10,300);
//    }
    
}