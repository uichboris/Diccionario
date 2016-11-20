package Controlador;

import java.io.IOException;
import Modelo.*;

public class Controlador 
{
    private AdministradorArchivos admArch;
    private ListaDoblementeLigada diccionario;
    private MatrizEnForma2 matrizAdyGrafo;
    private int menoresCostos[][], rutas[][], adyaMas[][], costos[][];
    
    public Controlador() throws IOException
    {
        admArch = new AdministradorArchivos();
        diccionario = admArch.leerArchivo();        
    }
    
    public void crearMatrizPosicionesGrafo()
    {
        int i;
        int longitud = diccionario.longitudLista();
        int[][] matrizPosiciones = new int[longitud][2];
        for(i=0; i<longitud;i++)
        {
            for(int j=0; j<2;j++)
            {
                matrizPosiciones[i][j] = 0;
            } 
        } 
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
            crearCostos();
        }
    }
    
    public void crearCostos()
    {
        NodoDoble p;
        int n;
        p = matrizAdyGrafo.primerNodo();
        Tripleta t = (Tripleta)matrizAdyGrafo.nodoMatriz().retornaDato();
        n = t.retornaFila();
        costos = new int[n+1][n+1];
        while(p!=matrizAdyGrafo.nodoCabeza())
        {
            t = (Tripleta)p.retornaDato();
            costos[t.retornaFila()][t.retornaColumna()] = 1;
            p = p.retornaLd();            
        }
    }
    
    public void crearCostosMinimos()
    {
        int i, j, n;
        n = costos.length;
        menoresCostos = new int[n][n];
        rutas = new int[n][n];
        for(i = 1; i<n;i++)
        {
            for(j = 1; j<n;j++)
            {
               menoresCostos[i][j] = costos[i][j] ;
               rutas[i][j] = 0;
            }
        }
        rellenarCostosMinimos(1,1, 0, 1);
    }
    
    public void rellenarCostosMinimos(int i, int j, int aux, int aux2)
    {
        int n = costos.length;
        while(i<n)
        {
            while(j<n)
            {
                if(costos[i][j] == 1)
                {
                    if(adyaMas[aux2][j]==1)
                    {                    
                        aux = aux+1;
                        rutas[j][aux2] = i;
                        menoresCostos[aux2][j] = aux;
                        menoresCostos[j][aux2] = aux;
                    }
                    rellenarCostosMinimos(j,j+1,aux, aux2);
                    aux = aux-1;
                }
                j = j+1;
            }
            i=i+1;
            j = i;
            aux = 0; 
            aux2 = aux2+1;
        }
    }
    
    public void recorreRutas()
    {
        int l = rutas.length;
        System.out.println("RUTAS: "+l);
        for(int i = 1; i<l;i++)
        {
           for(int j = 1; j<l;j++)
           {
               System.out.println("Fila: "+i+" Columna: "+j+" Dato: "+rutas[i][j]);
           } 
        }
    }
    
    public void recorreCostosMinimos()
    {
        int l = menoresCostos.length;
        System.out.println("COSTOS MINIMOS: "+l);
        for(int i = 1; i<l;i++)
        {
           for(int j = 1; j<l;j++)
           {
               System.out.println("Fila: "+i+" Columna: "+j+" Dato: "+menoresCostos[i][j]);
           } 
        }
    }
    public void recorreCostos()
    {
        int l = costos.length;
        System.out.println("COSTOS: "+l);
        for(int i = 1; i<l;i++)
        {
           for(int j = 1; j<l;j++)
           {
               System.out.println("Fila: "+i+" Columna: "+j+" Dato: "+costos[i][j]);
           } 
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
     
    public void proceseRutas()
    {
        int i, j, n;
        n = costos.length;
        for(i = 1; i<n; i++)
        {
            for(j = i+1; j<n; j++)
            {     
                if(menoresCostos[i][j]!=0)
                {
                    System.out.println("Para llegar a: "+j+" desde "+i+" la ruta es: ");
                    recorra(i,j);
                    System.out.println(j+" y el costo es: "+menoresCostos[i][j]);
                }
            }
        }
    }
    
    public void recorra(int i, int j)
    {
        int k;
        ListaDoblementeLigada aux = new ListaDoblementeLigada();
        NodoDoble p;
        k = rutas[j][i];
        j = k;
        if(k!=0)
        {
            aux.insertar(k, null);
        }
        p = aux.primerNodo();
        while(j!=i && j!=0)
        {
            k = rutas[j][i];
            j = k;
            if(k!=0)
            {
                aux.insertar(k, p);
            }
            p = p.retornaLd();
        }
        p = aux.ultimoNodo();
        while(!aux.finDeRecorrido(p))
        {
            System.out.print(p.retornaDato()+", ");
            p = p.retornaLi();
        }
    }
    
    public void cierreTransitivo()
    {
        NodoDoble p;
        int i, j, k, n;
        p = matrizAdyGrafo.primerNodo();
        Tripleta t = (Tripleta)matrizAdyGrafo.nodoMatriz().retornaDato();
        n = t.retornaFila();
        adyaMas = new int[n+1][n+1];
        while(p!=matrizAdyGrafo.nodoCabeza())
        {
            t = (Tripleta)p.retornaDato();
            adyaMas[t.retornaFila()][t.retornaColumna()] = 1;
            p = p.retornaLd();            
        }
        for(k = 1; k<=n; k++)
        {
            for(i = 1; i<=n; i++)
            {
                for(j = 1; j<=n; j++)
                {
                    if(adyaMas[i][k]==1 && adyaMas[k][j] ==1 && i!=j)
                    {
                        adyaMas[i][j]=1;
                    }
                }
            }
        }
    }
    
    public void recorreCierreTransitivo()
    {
        cierreTransitivo();
        int l = adyaMas.length;
        System.out.println("Cierre transitivo: ");
        for(int i = 1; i<l;i++)
        {
           for(int j = 1; j<l;j++)
           {
               System.out.println("Fila: "+i+" Columna: "+j+" Dato: "+adyaMas[i][j]);
           } 
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
    
//    public String palabras(){
//       String palabras="";
//       ListaDoblementeLigada dic= getDiccionario();
//        while (!dic.ultimoNodo()) {
//            palabra +=dic.getpalabra;
//            dic.next;
//        }
//        
//        
//        return palabras;
//    }
    
}
