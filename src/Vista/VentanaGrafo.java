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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class VentanaGrafo extends JFrame
{
    Controlador controlador;
    PanelGrafo panelGrafo;
    Color colorFondo;
    JPanel panelEste;
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    private JFileChooser select= new JFileChooser();
    private String contenido="";
    private FileInputStream entrada;
    private File archivo;
    
    public VentanaGrafo() throws IOException
    {
        super("Grafo");
        controlador = new Controlador();
        panelGrafo = new PanelGrafo(); 
        //panelPrincipal.add(panelGrafo,BorderLayout.CENTER);
        JScrollPane scrollPane = new  JScrollPane(panelGrafo);
        panelPrincipal.add(scrollPane);
        colorFondo = Color.BLUE;       
        panelPrincipal.setBackground(colorFondo);
        crearPanelEste();
        pack();         
        controlador.crearMatriz();
        setContentPane(panelPrincipal);  
        panelGrafo.repaint();
        Dimension tamaño = getSize();
        tamaño.height = 740;
        tamaño.width = 1200;
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
            //setPreferredSize(new Dimension(450,495));            
        }
        
        @Override
    public Dimension getPreferredSize() {
        return new Dimension(3000, 3000);
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
                int n1 = n+ultimaPosicionX, n2 = n+ultimaPosicionY;
                ultimaPosicionX = n1;
                ultimaPosicionY = n2;
                if(matrizPosiciones[i-1][0]==0)
                {
                    if(n2>740)
                    {
                        n2 = 50;
                    }
                    else
                    {
                        if(n1>1200)
                        {
                            n1 = 50;
                        }
                    }
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
    
    public void crearPanelEste()
    {
        panelEste = new JPanel(new BorderLayout());
        panelEste.add(crearOpciones(),BorderLayout.CENTER);
        panelEste.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        panelEste.setBackground(colorFondo);        
        panelEste.setVisible(true);
        panelPrincipal.add(panelEste,BorderLayout.SOUTH);        
    }   
    
    public JPanel crearOpciones()//Crea el panel para el ingreso del usuario
    {
        JPanel panelOpciones = new JPanel();
        JButton cargar = new JButton("Cargar Archivo");
        JButton mostrar = new JButton("Mostrar recorrido más corto");
        JComboBox listaPalabras = new JComboBox();
        JComboBox listaPalabras2 = new JComboBox();
        listaPalabras.removeAllItems();
        listaPalabras2.removeAllItems();
        for(int i = 1; i<=controlador.getDiccionario().longitudLista();i++)
        {
            String palabra = controlador.retornaPalabra(i);
            listaPalabras.addItem(palabra);
            listaPalabras2.addItem(palabra);
        }        
        panelOpciones.add(cargar);
        panelOpciones.add(listaPalabras);
        panelOpciones.add(listaPalabras2);
        panelOpciones.add(mostrar);        
        panelOpciones.setBorder(BorderFactory.createCompoundBorder( //Define el borde del panel
                BorderFactory.createEmptyBorder(5,5,5,5),
              BorderFactory.createTitledBorder("Opciones")));
        return panelOpciones;
    }
    
    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
            // TODO add your handling code here:
        if(select.showDialog(this,"Abrir archivo")==JFileChooser.APPROVE_OPTION){
            archivo=select.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    
                    try {
                        entrada=new FileInputStream(archivo);
                        int ascci;
                        while ((ascci=entrada.read())!=-1) {
                            char caracter=(char)ascci;
                            contenido +=caracter;
                        }
                    } catch (Exception e) {
                    }
                    
//                    palabras.setText(contenido);
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione un archivo de texto");
                }
            }
            
        }
    }
        
}

