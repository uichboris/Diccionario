package Modelo;

public class Tripleta 
{
    private int fila, columna;
    private Object valor;
    
    public Tripleta(int f, int c, Object v)
    {
        fila = f;
        columna = c;
        valor = v;
    }

    public int retornaFila() 
    {
        return fila;
    }

    public void asingaFila(int f)
    {
        fila = f;
    }

    public int retornaColumna() 
    {
        return columna;
    }

    public void asignaColumna(int c) 
    {
        columna = c;
    }

    public Object retornaValor() 
    {
        return valor;
    }

    public void asignaValor(Object v) 
    {
        valor = v;
    }
    
}
