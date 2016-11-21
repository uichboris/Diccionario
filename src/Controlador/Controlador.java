package Controlador;

import java.io.IOException;
import Modelo.*;
import java.io.File;

public class Controlador 
{
    
    private final AdministradorArchivos admArch;
    private ListaDoblementeLigada diccionario;
    private ListaDoblementeLigada dialogos;
    private MatrizEnForma2 matrizAdyGrafo;
    private Contador contador;
    
    public Controlador() throws IOException
    {
        admArch = new AdministradorArchivos();
        dialogos = admArch.leerDialogos();
    }
    
    public void leerArchivo(File archivo) throws IOException
    {
        diccionario = admArch.leerArchivo(archivo);        
    }
    
    public String retornaPalabra(int posicion)
    {
        Hilera palabra = new Hilera();
        NodoDoble p = diccionario.primerNodo();
        int i = 1;
        while(!diccionario.finDeRecorrido(p) && i<=posicion)
        {
            palabra = (Hilera)p.retornaDato();
            p = p.retornaLd();
            i = i+1;
        }
        return palabra.devuelvePalabraString();
    }   
    
    public void crearMatriz()
    {
        int n = diccionario.longitudLista();
        matrizAdyGrafo = new MatrizEnForma2(n ,n);
        NodoDoble p = diccionario.primerNodo();
        int posP = 1;        
        while(!diccionario.finDeRecorrido(p))
        {
            int posQ = posP+1;
            Hilera tp = (Hilera)p.retornaDato();
            NodoDoble q = p.retornaLd();
            while(!diccionario.finDeRecorrido(q))
                {
                    Hilera tq = (Hilera)q.retornaDato();
                    if(tp.cantidadLetrasDiferentes(tq) == 1)
                    {
                        Tripleta t = new Tripleta(posP, posQ, 1);
                        NodoDoble x = new NodoDoble(t);
                        matrizAdyGrafo.conectaPorFilas(x);                   
                        matrizAdyGrafo.conectaPorColumnas(x);
                        t = new Tripleta(posQ, posP, 1);
                        x = new NodoDoble(t);
                        matrizAdyGrafo.conectaPorFilas(x);                   
                        matrizAdyGrafo.conectaPorColumnas(x);
                    }
                    q = q.retornaLd();
                    posQ = posQ + 1;
                }
            p = p.retornaLd();
            posP = posP + 1;
        }
    }
    
    public void recorrerDiccionario()
    {
        NodoDoble p;
        p = diccionario.primerNodo();
        System.out.println("Diccionario: ");
        while(!diccionario.finDeRecorrido(p))
        {
            Hilera palabra = (Hilera)p.retornaDato();
            palabra.recorreIzqDer();
            p = p.retornaLd();
        }       
    }
    
    public ListaDoblementeLigada getDialogos()
    {
        return dialogos;
    }
    
    public ListaDoblementeLigada getDiccionario() 
    {
        return diccionario;
    }
    
    public MatrizEnForma2 getMatrizAdyGrafo() 
    {
        return matrizAdyGrafo;
    }

    public Contador getContador() 
    {
        return contador;
    }
    
    public String palabras()
    {
       String palabras="";
        NodoDoble p;
        p = diccionario.primerNodo();
        while(!diccionario.finDeRecorrido(p))
        {
            Hilera palabra = (Hilera)p.retornaDato();
            palabras += palabra.devuelvePalabraString();
            palabras += "\n";
            p = p.retornaLd();
        }  
        return palabras;
    }
    
    public void iniciarContador(int tiempo)
    {
        contador = new Contador(tiempo);
        contador.iniciarContador();
    }
    
    public void reiniciarContador()
    {
        contador.reiniciarTimer();
    }
    
}
