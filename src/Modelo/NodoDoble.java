package Modelo;

public class NodoDoble 
{
    private NodoDoble Li, Ld;
    private Object dato;
    
    public NodoDoble(Object d)
    {
        Li = null;
        Ld = null;
        dato = d;
    }

    public NodoDoble retornaLi() {
        return Li;
    }

    public void asignaLi(NodoDoble x) {
        Li = x;
    }

    public NodoDoble retornaLd() {
        return Ld;
    }

    public void asignaLd(NodoDoble x) {
        Ld = x;
    }

    public Object retornaDato() {
        return dato;
    }

    public void asignaDato(Object d) {
        dato = d;
    }   
}
