package Vista;
import Controlador.Controlador;
import Modelo.NodoDoble;
import Modelo.Tripleta;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class VentanaGrafo extends JFrame implements ActionListener
{
    private final Controlador controlador;
    private final PanelGrafo panelGrafo;
    private PanelPersonaje panelPersonaje;
    private JLabel recorrido;
    private JPanel panelSur;
    private JPanel panelEste;
    private JButton cargar;
    private JButton mostrar;
    private JComboBox listaPalabras;
    private JComboBox listaPalabras2;    
    private final JPanel panelPrincipal = new JPanel(new BorderLayout());
    private boolean leido;
    private final JFileChooser select= new JFileChooser();
    private File archivo;
    private BufferedImage image;
    private int estado;
    private int contador;
    
    public VentanaGrafo()
    {        
        super("Grafos 1.0");        
        leido = false;
        contador = 0;
        controlador = new Controlador();
        panelGrafo = new PanelGrafo(); 
        JScrollPane scrollPane = new  JScrollPane(panelGrafo);
        panelPrincipal.add(scrollPane);      
        panelPrincipal.setBackground(Color.LIGHT_GRAY);        
        crearPanelEste();
        crearPanelSur();
        pack();        
        setContentPane(panelPrincipal);  
        panelGrafo.repaint();
        Dimension tamaño = getSize();
        tamaño.height = 700;
        tamaño.width = 1200;
        setSize(tamaño);
        controlador.iniciarContador(50);
        play();
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                salir();
            }
        });
    }
    
    public void play()
    {
        Thread t = new Thread()
        {
            public void run()
            {
                while(true)
                {           
                    if(estado==1)
                    {
                        while(contador<=3)
                        {
                            try 
                            {
                                image = ImageIO.read(getClass().getClassLoader().getResource("Vista/res/images/personaje"+contador+".png"));                                
                                panelPersonaje.repaint();
                                if(controlador.getContador().getTiempo()<=0)
                                {
                                    contador = contador+1;
                                    controlador.reiniciarContador();
                                    controlador.iniciarContador(50);
                                }
                            } catch (IOException ex) 
                            {
                                Logger.getLogger(VentanaGrafo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } 
                        contador = 0;
                    }
                    if(estado==3)
                    {
                        return;                        
                    }
                    try
                    {                        
                        Thread.sleep(3);
                        
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
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

        public PanelGrafo()
        {
            this.setBackground(Color.WHITE);
        }
        
        @Override
        public Dimension getPreferredSize() 
        {
            return new Dimension(10000, 10000);
        }
        
        @Override        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if(leido==true)
            {
                controlador.crearMatriz();                
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
                    int n1 = n+ultimaPosicionX, n2 = n+ultimaPosicionY;
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
                            int posX = matrizPosiciones[i-1][0]+100;
                            int posY = matrizPosiciones[i-1][1];
                            for(int j =0; j<longitud; j++)
                            {
                                if((posX==matrizPosiciones[j][0] && posY==matrizPosiciones[j][1])||(posX-matrizPosiciones[j][0]<50 && posY-matrizPosiciones[j][1]<50))
                                {
                                    posY = posY+50+(t.retornaColumna()*5);
                                    posX = posX-25;
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
                            else
                            {
                                g.drawLine(matrizPosiciones[i-1][0]+5, matrizPosiciones[i-1][1]+5, matrizPosiciones[t.retornaColumna()-1][0]+5, matrizPosiciones[t.retornaColumna()-1][1]+5);
                            }
                            ultimaPosicionX = posX;
                            ultimaPosicionY = posY;
                        }
                        p = p.retornaLd();
                    }                
                    i = i+1;
                }while(i<=controlador.getDiccionario().longitudLista());
            }
        }
    }
    
    public class PanelPersonaje extends JPanel
    {   
        public PanelPersonaje()
        {
            setPreferredSize(new Dimension(400,120));
            setBorder(BorderFactory.createEmptyBorder(0,10,10,10));       
            setVisible(true);  
            this.setBorder(BorderFactory.createCompoundBorder( //Define el borde del panel
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createTitledBorder("Ayuda")));
        }
        
        @Override        
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 10, 50, null);
            estado = 1;
        }
    }
    
    public void crearPanelEste()
    {        
        panelEste = new JPanel(new BorderLayout());
        panelPersonaje = new PanelPersonaje();
        panelEste.setPreferredSize(new Dimension(500,100));
        panelEste.add(crearRecorrido(),BorderLayout.CENTER);
        panelEste.add(panelPersonaje, BorderLayout.SOUTH);
        panelEste.setBorder(BorderFactory.createEmptyBorder(5,5,5,10));       
        panelEste.setVisible(true);
        panelPrincipal.add(panelEste,BorderLayout.EAST);        
    }   
    
    public void crearPanelSur()
    {
        panelSur = new JPanel(new BorderLayout());        
        panelSur.add(crearOpciones(),BorderLayout.CENTER);        
        panelSur.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));       
        panelSur.setVisible(true);
        panelPrincipal.add(panelSur,BorderLayout.SOUTH);        
    } 
    
    public JPanel crearRecorrido()//Crea el panel para el ingreso del usuario
    {
        recorrido = new JLabel();
        JPanel panelRecorrido = new JPanel();
        panelRecorrido.add(recorrido);
        panelRecorrido.setBorder(BorderFactory.createCompoundBorder( //Define el borde del panel
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createTitledBorder("Recorridos")));
        return panelRecorrido;
    }
    
    public JPanel crearOpciones()//Crea el panel para el ingreso del usuario
    {
        JPanel panelOpciones = new JPanel();
        cargar = new JButton("Cargar Archivo");
        mostrar = new JButton("Mostrar recorrido más corto");
        cargar.addActionListener(this);
        mostrar.addActionListener(this);
        listaPalabras = new JComboBox();
        listaPalabras2 = new JComboBox();        
        panelOpciones.add(cargar);
        panelOpciones.add(listaPalabras);      
        panelOpciones.add(listaPalabras2);
        panelOpciones.add(mostrar);        
        panelOpciones.setBorder(BorderFactory.createCompoundBorder( //Define el borde del panel
                BorderFactory.createEmptyBorder(10,10,10,10),
              BorderFactory.createTitledBorder("Opciones")));
        return panelOpciones;
    }
    
    public void actionPerformed(ActionEvent e)//Recibe la acción al hacer click en alguno de los botones
    { 
        if(e.getSource() == cargar)
        {
            if(select.showDialog(this,"Abrir archivo")==JFileChooser.APPROVE_OPTION)
            {
                archivo=select.getSelectedFile();
                if (archivo.canRead()) 
                {
                    if (archivo.getName().endsWith("txt")) 
                    {
                        try {
                            controlador.leerArchivo(archivo);
                            leido = true;
                            panelGrafo.repaint();
                            panelSur.repaint();
                            panelEste.repaint();
                            listaPalabras.removeAllItems();
                            listaPalabras2.removeAllItems();
                            for(int i = 1; i<=controlador.getDiccionario().longitudLista();i++)
                            {
                                String palabra = controlador.retornaPalabra(i);
                                listaPalabras.addItem(palabra);
                                listaPalabras2.addItem(palabra);
                            }  
                        
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(VentanaGrafo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Seleccione un archivo de texto");
                    }
                }    
            }        
        }
        else
        {
            if(e.getSource()==mostrar)
            {
                if(leido)
                {
                    String palabra = (String)listaPalabras.getSelectedItem();
                    recorrido.setText("Desde la palabra "+palabra+", se puede llegar a: ");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Debes cargar un archivo antes.");
                }
            }
        }
    }
}

