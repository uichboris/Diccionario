package Controlador;

import java.io.IOException;
import Modelo.*;

public class Controlador 
{
    AdministradorArchivos admArch;
    ListaDoblementeLigada diccionario;
    MatrizEnForma2 matrizAdyGrafo;
    
    public Controlador() throws IOException
    {
        admArch = new AdministradorArchivos();
        diccionario = admArch.leerArchivo();
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
                    /*if(tp.longitud()==tq.longitud())
                    {*/
                        System.out.println("TP: ");
                        tp.recorreIzqDer();
                        System.out.println("TQ: ");
                        tq.recorreIzqDer();
                        System.out.println("POS P: "+posP);                        
                        System.out.println("POS Q: "+posQ);
                        if(tp.cantidadLetrasDiferentes(tq) == 1)
                        {
                            Tripleta t = new Tripleta(posP, posQ, 1);
                            NodoDoble x = new NodoDoble(t);
                            matrizAdyGrafo.conectaPorFilas(x);
                            matrizAdyGrafo.conectaPorColumnas(x);
                        }
                    /*}*/
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
        while(!diccionario.finDeRecorrido(p))
        {
            Hilera palabra = (Hilera)p.retornaDato();
            palabra.recorreIzqDer();
            p = p.retornaLd();
        }       
    }

    public ListaDoblementeLigada getDiccionario() {
        return diccionario;
    }

    public void setDiccionario(ListaDoblementeLigada diccionario) {
        this.diccionario = diccionario;
    }

    public MatrizEnForma2 getMatrizAdyGrafo() {
        return matrizAdyGrafo;
    }

    public void setMatrizAdyGrafo(MatrizEnForma2 matrizAdyGrafo) {
        this.matrizAdyGrafo = matrizAdyGrafo;
    }
    
    
    
}
