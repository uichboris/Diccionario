package Vista;

import Controlador.Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PruebaPractica 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        Controlador c = new Controlador();
        c.crearMatriz();
        c.getMatrizAdyGrafo().muestraMatrizPorColumnas();
        c.getMatrizAdyGrafo().muestraMatrizPorFilas();
        c.recorrerDiccionario();
    }
    
}
