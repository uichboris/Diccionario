package Modelo;

public class MatrizEnForma2
{
    private NodoDoble mat;
    
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
    
    public NodoDoble nodoMatriz()
    {
        return mat;
    }
    
    public NodoDoble nodoCabeza()
    {
        return mat.retornaLd();
    }
    
    public NodoDoble primerNodo()
    {
        return nodoCabeza().retornaLd();        
    }
    
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
    
    public void conectaPorColumnas(NodoDoble x)
    {
        //int i;
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
