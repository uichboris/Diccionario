package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdministradorArchivos 
{
    private FileReader fr;
    private BufferedReader br;
    
    public AdministradorArchivos(){}
    
    /**
     * Lee un archivo dado por el usuario, construyendo hileras con cada una de 
     * las palabras. Las hileras son guardadas en una lista doblemente ligada
     * para no hacer uso de la lectura del archivo de nuevo.
     * @param archivo es el archivo cargado por el usuario en la vista, es de 
     * tipo File
     * @return diccionario que es de tipo listadoblementeligada contine
     * todas las hileras de las palbras
     * @throws IOException en caso de que el archivo no haya sido encontrado
     */
    public ListaDoblementeLigada leerArchivo(File archivo) throws IOException
    {
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
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
        return diccionario;        
    }
    
    /**
     * Lee el archivo dialogos, un archivo creado especialmente para la ayuda.
     * Guarda cada una de las frases como String en una lista doblemente ligada.
     * @return dialogos que es tipo listadoblementeligada y contiene todos
     * los dialogos dichos por el personaje de ayuda.
     * @throws IOException 
     */
    public ListaDoblementeLigada leerDialogos() throws IOException
    {
        File archivo = new File("src/Vista/res/Dialogos.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String cadena = br.readLine();
        ListaDoblementeLigada dialogos = new ListaDoblementeLigada();
        dialogos.insertar(cadena, null);
        NodoDoble p = dialogos.primerNodo();
        while ((cadena = br.readLine())!=null) 
        {
            dialogos.insertar(cadena, p);
            p = p.retornaLd();
        }
        return dialogos;        
    }
}
