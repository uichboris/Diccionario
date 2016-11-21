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
    
    public ListaDoblementeLigada leerDialogos() throws IOException
    {
        File archivo = new File("src/Vista/res/Dialogos.txt");
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String cadena = br.readLine();
        System.out.println(cadena);
        ListaDoblementeLigada dialogos = new ListaDoblementeLigada();
        dialogos.insertar(cadena, null);
        NodoDoble p = dialogos.primerNodo();
        while ((cadena = br.readLine())!=null) 
        {
            dialogos.insertar(cadena, p);
            System.out.println(cadena);
            p = p.retornaLd();
        }
        return dialogos;        
    }
}
