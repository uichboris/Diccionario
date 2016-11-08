package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdministradorArchivos 
{
    
    FileReader fr;
    BufferedReader br;
    
    public AdministradorArchivos() throws FileNotFoundException, IOException
    {
        fr = new FileReader("Diccionario.txt");
        br = new BufferedReader(fr);
    }
    
    public ListaDoblementeLigada leerArchivo() throws IOException
    {
        String cadena = br.readLine();
        ListaDoblementeLigada diccionario = new ListaDoblementeLigada();
        Hilera palabra = new Hilera();
        palabra.construirHilera(cadena.toLowerCase());
        diccionario.insertar(palabra, null);
        NodoDoble p = diccionario.primerNodo();
        while ((cadena = br.readLine())!=null) 
        {
            palabra = new Hilera();
            palabra.construirHilera(cadena.toLowerCase());
            diccionario.insertar(palabra, p);
            p = p.retornaLd();
        }
        p = diccionario.primerNodo();
        return diccionario;        
    }
}
