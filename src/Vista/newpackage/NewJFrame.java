/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.newpackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jfwc1
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grafico = new javax.swing.JButton();
        panel = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        grafico.setText("graficar");
        grafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficoActionPerformed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(grafico))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grafico)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void graficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficoActionPerformed
        // TODO add your handling code here:
        
        //Vertice v=new Vertice()panel.getGraphics();
//        if (i==0) {
//             Graphics2D g2=(Graphics2D)panel.getGraphics();
//             Ellipse2D.Double elipse=new Ellipse2D.Double(10,10,50,20);
//             g2.draw(elipse);
//             g2.drawString("hola",20 ,24 );
//             i++;
//        } else {
//             Graphics2D g2=(Graphics2D)panel.getGraphics();
//            Ellipse2D.Double elipse=new Ellipse2D.Double(40,40,50,20);
//            g2.draw(elipse);
//            g2.drawString("hola",50 ,54 );
//        }
        
       
        System.out.println(panel.getWidth());
        System.out.println(panel.getHeight());
        
        
        
        
//        i=1;
        Random aleatorio = new Random();
        for (int i = 0; i < 10; i++) {
         n= aleatorio.nextInt(4);
        System.out.println(n);
        }
        
        
         Random generadorAleatorios = new Random();
        
        //genera un numero entre 1 y 5 y lo guarda en la variable numeroAleatorio
        int numeroAleatorio = 10*ThreadLocalRandom.current().nextInt(5, 40 + 1);;
        //imprimo el numero en consola
        System.out.println(numeroAleatorio);
        x=numeroAleatorio;
//    

  //genera un numero entre 1 y 5 y lo guarda en la variable numeroAleatorio
         numeroAleatorio =10* ThreadLocalRandom.current().nextInt(3, 25 + 1);;
        //imprimo el numero en consola
        System.out.println(numeroAleatorio);
             y=numeroAleatorio;
//        }
       
        switch (n) {
            case 0:
                x=x+90;
                y=y+30;
                 Graphics2D g2=(Graphics2D)panel.getGraphics();
                  Ellipse2D.Double elipse=new Ellipse2D.Double(x,y,50,20);
                g2.draw(elipse);
                g2.drawString("hola",x+10 ,y+14 );
                break;
            case 1:
                 x=x+90;
                Graphics2D g3=(Graphics2D)panel.getGraphics();
                Ellipse2D.Double elipse2=new Ellipse2D.Double(x,y,50,20);
                g3.draw(elipse2);
                g3.drawString("hola",x+10 ,y+14 );
                
                break;
            case 2:
                
                y=y+30;
                Graphics2D g4=(Graphics2D)panel.getGraphics();
                  Ellipse2D.Double elipse3=new Ellipse2D.Double(x,y,50,20);
                g4.draw(elipse3);
                g4.drawString("hola",x+10 ,y+14 );
                break;
            case 3:
                 x=x-90;
                y=y-30;
                Graphics2D g5=(Graphics2D)panel.getGraphics();
                  Ellipse2D.Double elipse4=new Ellipse2D.Double(x,y,50,20);
                g5.draw(elipse4);
                g5.drawString("hola",x+10 ,y+14 );
                break;    
            default:
                throw new AssertionError();
        }
 
//      repaint();
        
    }//GEN-LAST:event_graficoActionPerformed
    
 
    
    
//    public void paint(Graphics g)
//    {
//       super.paint(g);
//       if(i==1){
//        g.drawString(s,x,y);
//        g.drawString("Segunda linea",60,300);
//      }
//    }    
//    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
          
           
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton grafico;
    private javax.swing.JScrollPane panel;
    // End of variables declaration//GEN-END:variables
    private String s ="homa";
    private int x=50;
    private int y=50;
    private int i=0;
    private int n=0;
    
    
   
}
