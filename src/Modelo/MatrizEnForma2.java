package Modelo;

public class MatrizEnForma2
{
    private NodoDoble mat;
    
    /**
     * Constructor de la clase
     * @param m es el número de filas que tendrá la matriz
     * @param n es el número de columnas que tendrá la matriz
     */
    public MatrizEnForma2(int m, int n)
    {
        Tripleta t = new Tripleta(m,n,0);
        mat = new NodoDoble(t);
        Tripleta tx = new Tripleta(0, 0, null);
        NodoDoble x = new NodoDoble(tx);
        x.asignaLi(x);
        x.asignaLd(x);
        mat.asignaLd(x);
    }
    
    /**
     * Retorna el nodo Matriz que contiene la cantidad de columnas y filas de
     * la matriz
     * @return mat que es de tipo nodoDoble
     */
    public NodoDoble nodoMatriz()
    {
        return mat;
    }
    
    /**
     * Retorna el nodo cabeza
     * la matriz
     * @return nodoCabeza que es de tipo NodoDoble
     */
    public NodoDoble nodoCabeza()
    {
        return mat.retornaLd();
    }
    
    /**
     * Retorna el primer nodo de la matriz
     * @return primerNodo que es de tipo NodoDoble
     */
    public NodoDoble primerNodo()
    {
        return nodoCabeza().retornaLd();        
    }
    
    /**
     * Recorre la matriz, ordenandola por sus filas.
     */
    public void muestraMatrizPorFilas()
    {
        System.out.println("Matriz por filas: ");
        int f, c;
        Object v;
        NodoDoble q = nodoCabeza().retornaLd();
        Tripleta t;
        while(q!=nodoCabeza())
        {
            t = (Tripleta)q.retornaDato();
            f = t.retornaFila();
            c = t.retornaColumna();
            v = t.retornaValor();
            System.out.println(f+""+c+""+v);            
            q = q.retornaLd();
        }
    }
    
    /**
     * Recorre la matriz, ordenandola por sus columnas.
     */
    public void muestraMatrizPorColumnas()
    {
        System.out.println("Matriz por columnas: ");
        int f, c;
        Object v;
        NodoDoble q = nodoCabeza().retornaLi();
        Tripleta t;
        while(q!=nodoCabeza())
        {
            t = (Tripleta)q.retornaDato();
            f = t.retornaFila();
            c = t.retornaColumna();
            v = t.retornaValor();
            System.out.println(c+""+f+""+v);            
            q = q.retornaLi();            
        }
    }
    
    /**
     * Conecta la matriz por filas con su liga derecha
     * @param x de tipo NodoDoble, es el nodo que se conectará a la matriz
     */
    public void conectaPorFilas(NodoDoble x)
    {
        //int i;
        Tripleta tx = (Tripleta)x.retornaDato();
        NodoDoble p = nodoCabeza();
        NodoDoble anterior = p;
        NodoDoble q = p.retornaLd();
        Tripleta tq = (Tripleta)q.retornaDato();
        while(q!=p && tq.retornaFila()<tx.retornaFila())
        {
            anterior = q;
            q = q.retornaLd();
            tq = (Tripleta)q.retornaDato();
        }
        while(q!=p && tq.retornaFila()==tx.retornaFila() && tq.retornaColumna()<tx.retornaColumna())
        {
            anterior = q;
            q = q.retornaLd();
            tq = (Tripleta)q.retornaDato();
        }
        anterior.asignaLd(x);
        x.asignaLd(q);
    }
    
    /**
     * Conecta por columnas cada una de las columnas que pertenecen a la matriz
     * por medio de su liga izquierda
     * @param x de tipo NodoDoble, es el nodo que se conectará a la matriz.
     */
    public void conectaPorColumnas(NodoDoble x)
    {
        Tripleta tx = (Tripleta)x.retornaDato();
        NodoDoble p = nodoCabeza();
        NodoDoble anterior = p;
        NodoDoble q = p.retornaLi();
        Tripleta tq = (Tripleta)q.retornaDato();
        while(q!=p && tq.retornaColumna()<tx.retornaColumna())
        {
            anterior = q;
            q = q.retornaLi();
            tq = (Tripleta)q.retornaDato();
        }
        while(q!=p && tq.retornaColumna()==tx.retornaColumna() && tq.retornaFila()<tx.retornaFila())
        {
            anterior = q;
            q = q.retornaLi();
            tq = (Tripleta)q.retornaDato();
        }
        anterior.asignaLi(x);
        x.asignaLi(q);
        Tripleta tmat = (Tripleta)mat.retornaDato();
        tmat.asignaValor((int)tmat.retornaValor()+1);
    }   
    
    /**
     * Retorna un nodo dados una fila y una columna
     * @param f es de tipo Int y se refiere a la fila del nodo
     * @param c es de tipo Int y se refiere a la columna del nodo
     * @return p que es de tipo NodoDoble
     */
    public NodoDoble retonarNodoEn(int f, int c)
    {
        NodoDoble p;
        Tripleta t;
        p = primerNodo();
        while(p!=nodoCabeza())
        {
            t = (Tripleta)p.retornaDato();
            if(t.retornaFila()==f && t.retornaColumna()==c)
            {
                return p;
            }
            else
            {
                p = p.retornaLd();
            }
        }
        return p;
    }
}
