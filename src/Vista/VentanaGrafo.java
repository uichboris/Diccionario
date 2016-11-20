package Vista;
import Controlador.Controlador;
import Modelo.NodoDoble;
import Modelo.Tripleta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class VentanaGrafo extends JFrame
{
    Controlador controlador;
    PanelGrafo panelGrafo;
    Color colorFondo;
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    ArrayList <JLabel> palabras = new ArrayList();
    
    public VentanaGrafo() throws IOException
    {
        super("Grafo");
        panelGrafo = new PanelGrafo(); 
        panelPrincipal.add(panelGrafo,BorderLayout.CENTER);
        colorFondo = Color.BLUE;       
        panelPrincipal.setBackground(colorFondo);
        controlador = new Controlador();
        controlador.crearMatriz();
        setContentPane(panelPrincipal);  
        panelGrafo.repaint();
        Dimension tamaño = getSize();
        tamaño.height = 540;
        tamaño.width = 800;
        setSize(tamaño);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                salir();
            }
        });
    }  
    
    public void salir()
    {
        int opcion = JOptionPane.showConfirmDialog(null,"¿Seguro desea salir?", 
                    "Grafos 1.0", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(opcion == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }            
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run()
            {
                try{
                    boolean nimbusFound = false;
                        for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
                        {
                            if(info.getName().equals("Nimbus"))
                            {
                                UIManager.setLookAndFeel(info.getClassName());
                                nimbusFound = true;
                                break;
                            }
                        }
                        if(!nimbusFound)
                        {
                            int option = JOptionPane.showConfirmDialog(null,
                                    "Nimbus no ha sido encontrado\n"+
                                    "¿Quieres continuar?",
                                    "Warning",JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
                            if(option == JOptionPane.NO_OPTION)
                            {
                                System.exit(0);
                            }
                        }
                    VentanaGrafo ventanaGrafo = new VentanaGrafo();
                   //mcg.pack();
                    ventanaGrafo.setLocationRelativeTo(null);
                    ventanaGrafo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    ventanaGrafo.setResizable(false);
                    ventanaGrafo.setVisible(true); 
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getStackTrace());
                    e.printStackTrace();
                }
            }
        });
    }
    
    public class PanelGrafo extends JPanel
    {   
        int ejeX,ejeY;  
        boolean primera = false;

        @Override
        public void setLayout(LayoutManager mgr) {
            super.setLayout(null); //To change body of generated methods, choose Tools | Templates.
        }

        public PanelGrafo()
        {
            setPreferredSize(new Dimension(450,495));
        }
        
        @Override        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int i;
            int n = 50;
            int ultimaPosicionX = 0;
            int ultimaPosicionY = 0;
            int longitud = controlador.getDiccionario().longitudLista();
            int[][] matrizPosiciones = new int[longitud][2];
            for(i=0; i<longitud;i++)
            {
                for(int j=0; j<2;j++)
                {
                    matrizPosiciones[i][j] = 0;
                } 
            }   
            i  = 1;
            do
            {
                int n1 = n+ultimaPosicionX, n2 = n;
                ultimaPosicionX = n1;
                ultimaPosicionY = n2;
                if(matrizPosiciones[i-1][0]==0)
                {
                    g.setColor(Color.red);
                    g.fillRect(n1, n2, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawString(controlador.retornaPalabra(i), n1, n2);
                    matrizPosiciones[i-1][0]=n1;
                    matrizPosiciones[i-1][1]=n2;
                }
                NodoDoble p = controlador.getMatrizAdyGrafo().primerNodo();
                while(p!=controlador.getMatrizAdyGrafo().nodoCabeza())
                {
                    Tripleta t = (Tripleta)p.retornaDato();
                    if(t.retornaFila()==i)
                    {
                        int posX = matrizPosiciones[i-1][0]+50;
                        int posY = matrizPosiciones[i-1][1];
                        for(int j =0; j<longitud; j++)
                        {
                            if(posX==matrizPosiciones[j][0] && posY==matrizPosiciones[j][1])
                            {
                                posY = posY+50;
                            }
                        }
                        if(matrizPosiciones[t.retornaColumna()-1][0]==0)
                        {
                            g.setColor(Color.red);
                            g.fillRect(posX, posY, 10, 10);
                            g.setColor(Color.BLACK);
                            g.drawString(controlador.retornaPalabra(t.retornaColumna()), posX, posY);
                            matrizPosiciones[t.retornaColumna()-1][0]=posX;
                            matrizPosiciones[t.retornaColumna()-1][1]=posY;
                            g.setColor(Color.BLACK);
                            g.drawLine(matrizPosiciones[i-1][0]+5, matrizPosiciones[i-1][1]+5, posX+5, posY+5);
                        }
                        ultimaPosicionX = posX;
                        ultimaPosicionY = posY;
                    }
                    p = p.retornaLd();
                }                
                i = i+1;
            }while(i<=controlador.getDiccionario().longitudLista());
//            g.drawImage(imagenes.get(DatosDelJuego.Ambiente),125,36,this);
//            g.drawImage(imagenes.get(DatosDelJuego.ImagenTablero),125,65,this);
//            if(estado == DatosDelJuego.MOVIMIENTO)
//            {
//                int p = controlador.getPersonaje().getValor();
//                g.drawImage(imagenes.get(p),ejeX,ejeY,this);
//                estado = DatosDelJuego.MOVIMIENTO_DETENIDO;
//                for(int i = 0;i<12; i++) 
//                {
//                    for(int j = 0;j<10; j++)
//                    {
//                        int ejeXElemento = controlador.getTablero().getElemento(i, j).getCoordenada_x();
//                        int ejeYElemento = controlador.getTablero().getElemento(i, j).getCoordenada_y();
//                        g.drawImage(imagenes.get(controlador.getTablero().getElemento(i, j).getValor()),ejeXElemento,ejeYElemento,this);                    
//                    }
//                } 
//            }
//            else if(primera==false)
//            {
//                int p = controlador.getPersonaje().getValor();
//                ejeX = controlador.getPersonaje().getEjeX();
//                ejeY = controlador.getPersonaje().getEjeY();
//                g.drawImage(imagenes.get(p),ejeX,ejeY,this);
//                for(int i = 0;i<12; i++) 
//                {
//                    for(int j = 0;j<10; j++)
//                    {
//                        controlador.definePosicionElementos(i, j, ejeX, ejeY);
//                        int ejeXElemento = controlador.getTablero().getElemento(i, j).getCoordenada_x();
//                        int ejeYElemento = controlador.getTablero().getElemento(i, j).getCoordenada_y();
//                        g.drawImage(imagenes.get(controlador.getTablero().getElemento(i, j).getValor()),ejeXElemento,ejeYElemento,this);                    
//                    }
//                }               
//            }
        }
    }
        
}

