package Controlador;

import java.io.IOException;
import Modelo.*;
import java.io.File;

public class Controlador {

    private final AdministradorArchivos admArch;
    private ListaDoblementeLigada diccionario;
    private final ListaDoblementeLigada dialogos;
    private MatrizEnForma2 matrizAdyGrafo;
    private Contador contador;
    private int menoresCostos[][], rutas[][], adyaMas[][], costos[][];

    /**
     * Constructor de la clase controlador que hace todos los llamados a las 
     * clases 
     * de modelo
     */
    public Controlador() throws IOException 
    {
        admArch = new AdministradorArchivos();
        dialogos = admArch.leerDialogos();
    }
    
/**
 * El controlador llama al método de administrador de archivos
 * @param archivo es el parametro que llega de la vista, del archivo 
 * seleccionado por el usuario
 * @throws IOException 
 */
    public void leerArchivo(File archivo) throws IOException {
        diccionario = admArch.leerArchivo(archivo);
    }

    /**
     * Regresa la palabra, desde el diccionario como string en la posicion dada
     * @param posicion
     * @return 
     */
    public String retornaPalabra(int posicion) {
        Hilera palabra = new Hilera();
        NodoDoble p = diccionario.primerNodo();
        int i = 1;
        while (!diccionario.finDeRecorrido(p) && i <= posicion) {
            palabra = (Hilera) p.retornaDato();
            p = p.retornaLd();
            i = i + 1;
        }
        return palabra.devuelvePalabraString();
    }

    /**
     * Crea la matriz de adyacencia del grafo
     */
    public void crearMatriz() {
        int n = diccionario.longitudLista();
        matrizAdyGrafo = new MatrizEnForma2(n, n);
        NodoDoble p = diccionario.primerNodo();
        int posP = 1;
        while (!diccionario.finDeRecorrido(p)) {
            int posQ = posP + 1;
            Hilera tp = (Hilera) p.retornaDato();
            NodoDoble q = p.retornaLd();
            while (!diccionario.finDeRecorrido(q)) {
                Hilera tq = (Hilera) q.retornaDato();
                if (tp.cantidadLetrasDiferentes(tq) == 1) {
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
            cierreTransitivo();
            crearCostosMinimos();            
        }
    }

    /**
     * Método que devuelve la lista ligada que contiene los dialogos, para el
     * personaje de ayuda.
     * @return el atributo dialogos que es del tipo listadoblementeligada
     */
    public ListaDoblementeLigada getDialogos() {
        return dialogos;
    }

    /**
     * Método que devuleve la lista ligada que contiene las palabras del
     * diccionario
     * @return  atributo diccionario que es del tipo listadoblementeligada
     */
    public ListaDoblementeLigada getDiccionario() {
        return diccionario;
    }

    /**
     * Devuelve la matriz de adyacencia perteneciente al grafo.
     * @return el atripbuto matrizAfyGrafo que es de tipo MatrizEnForma2
     */
    public MatrizEnForma2 getMatrizAdyGrafo() {
        return matrizAdyGrafo;
    }

    /**
     * Devuelve el contador, atributo que tiene como objetivo usar un timer
     * para las animaciones del personaje de ayuda
     * @return el atributo contador que es de tipo Contador
     */
    public Contador getContador() {
        return contador;
    }

    /**
     * Inicializa el contador con un tiempo dado
     * @param tiempo es un atributo de tipo entero que inicializa el timer de la 
     * clase contador
     */
    public void iniciarContador(int tiempo) {
        contador = new Contador(tiempo);
        contador.iniciarContador();
    }

    /**
     * Reinicia el contador, para que no se acumulen los tiempos ingresados 
     * anteriormente
     */
    public void reiniciarContador() {
        contador.reiniciarTimer();
    }

    /**
     * Crea una matriz con los costos que da ir de un nodo a otro
     */
    public void crearCostos() {
        NodoDoble p;
        int n;
        p = matrizAdyGrafo.primerNodo();
        Tripleta t = (Tripleta) matrizAdyGrafo.nodoMatriz().retornaDato();
        n = t.retornaFila();
        costos = new int[n + 1][n + 1];
        while (p != matrizAdyGrafo.nodoCabeza()) {
            t = (Tripleta) p.retornaDato();
            costos[t.retornaFila()][t.retornaColumna()] = 1;
            p = p.retornaLd();
        }
    }

    /**
     * Crea una matriz con los costos minímos que es de ir de un nodo a otro
     */
    public void crearCostosMinimos() {
        int i, j, n;
        n = costos.length;
        menoresCostos = new int[n][n];
        rutas = new int[n][n];
        for (i = 1; i < n; i++) {
            for (j = 1; j < n; j++) {
                menoresCostos[i][j] = costos[i][j];
                rutas[i][j] = 0;
            }
        }
        rellenarCostosMinimos(1, 1, 0, 1);
    }

    /**
     * Rellena la matriz de costos minímos
     * @param i es la posición i en la matriz costos
     * @param j es la posición j en la matriz costos
     * @param aux es una posicion auxiliar para recorrer el cierre transitivo
     * @param aux2  es una posicion auciliar para recorrer el cierre transitivo
     */
    public void rellenarCostosMinimos(int i, int j, int aux, int aux2) {
        int n = costos.length;
        while (i < n) {
            while (j < n) {
                if (costos[i][j] == 1) {
                    if (adyaMas[aux2][j] == 1) {
                        aux = aux + 1;
                        rutas[j][aux2] = i;
                        menoresCostos[aux2][j] = aux;
                        menoresCostos[j][aux2] = aux;
                    }
                    rellenarCostosMinimos(j, j + 1, aux, aux2);
                    aux = aux - 1;
                }
                j = j + 1;
            }
            i = i + 1;
            j = i;
            aux = 0;
            aux2 = aux2 + 1;
        }
    }


    /**
     * Con nodos dados, este método devuelve cual es el menor recorrido
     * para ir desde i hasta j
     * @param i es el nodo desde el cual se iniciará el recorrido
     * @param j es el nodo hacia cual llega el recorrido
     * @return recorridos que es de tipo string y contiene el recorrido más 
     * corto para ser impreso en pantalla
     */
    public String proceseRutas(int i, int j) 
    {
        String recorrido;
        if(i==j)
        {
            return "Las palabras son las mismas";
        }
        if(adyaMas[i][j]==0)
        {
            return "No se puede ir desde "+ retornaPalabra(i) +" hasta "+retornaPalabra(j)+".";
        }
        recorrido = "<html>Para llegar a " + retornaPalabra(j) + " desde " + retornaPalabra(i) + " la ruta es: ";
        recorrido = recorrido+"<br>"+recorra(i, j)+retornaPalabra(j)+"."+"</html>";
        return recorrido;
    }

    /**
     * Recorre la matriz de recorridos buscando el menor recorrido para ir
     * desde el nodo i hasta j
     * @param i nodo desde el cual inicia recorrido
     * @param j nodo en el cual termina el recorrido
     * @return ruta que es de tipo String y contiene la ruta más corta.
     */
    public String recorra(int i, int j) {
        int k;
        ListaDoblementeLigada aux = new ListaDoblementeLigada();
        NodoDoble p;
        String ruta = "";
        k = rutas[j][i];
        j = k;
        if (k != 0) {
            aux.insertar(k, null);
        }
        p = aux.primerNodo();
        while (j != i && j != 0) {
            k = rutas[j][i];
            j = k;
            if (k != 0) {
                aux.insertar(k, p);
            }
            p = p.retornaLd();
        }
        p = aux.ultimoNodo();
        while (!aux.finDeRecorrido(p)) {
            ruta= ruta+retornaPalabra((int)p.retornaDato()) + ", ";
            p = p.retornaLi();
        }
        return ruta;
    }

    /**
     * Crea una matriz con todas las posibilidades a las cuales se puede ir 
     * desde cualquier nodo i, hasta cualquier nodo j
     */
    public void cierreTransitivo() {
        NodoDoble p;
        int i, j, k, n;
        p = matrizAdyGrafo.primerNodo();
        Tripleta t = (Tripleta) matrizAdyGrafo.nodoMatriz().retornaDato();
        n = t.retornaFila();
        adyaMas = new int[n + 1][n + 1];
        while (p != matrizAdyGrafo.nodoCabeza()) {
            t = (Tripleta) p.retornaDato();
            adyaMas[t.retornaFila()][t.retornaColumna()] = 1;
            p = p.retornaLd();
        }
        for (k = 1; k <= n; k++) {
            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    if (adyaMas[i][k] == 1 && adyaMas[k][j] == 1 && i != j) {
                        adyaMas[i][j] = 1;
                    }
                }
            }
        }
    }
   
}
