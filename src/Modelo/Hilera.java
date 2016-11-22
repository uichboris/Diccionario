package Modelo;

public class Hilera extends ListaDoblementeLigada 
{      
    public Hilera(){}//Constructor de la clase Hilera
    
    /**
     * Construye la hilera con un String dado
     * @param hilera es de tipo String, es una palabra cualquiera
     */
    public void construirHilera(String hilera)
    {
        char letra;
        letra = hilera.charAt(0);
        insertar(letra,null);
        NodoDoble p = primerNodo();
        for(int i=1;i<hilera.length();i++) //Pasa el String a char y construye la hilera representada como Lista Doblemente Ligada
        {
            letra = hilera.charAt(i);
            insertar(letra, p);
            p = p.retornaLd();
        }
    }
    
    /**
     * Devuelve la longitud de la hilera
     * @return i que es de tipo Int y contiene el valor de la longitud de la
     * hilera
     */
    public int longitud() 
    {
        NodoDoble p;
        p = primerNodo();
        int i = 0;
        while(!finDeRecorrido(p))
        {
           i=i+1;
           p = p.retornaLd();
        }
        return i;
    }
    
    /**
     * Método que devuelve una hilera formada como un string nuevamente
     * @return palabra que es de tipo String, contiene la hilera concatenada 
     * como un String.
     */
    public String devuelvePalabraString()
    {
        String palabra;
        char letra;
        NodoDoble p = primerNodo();
        letra = (char)p.retornaDato();
        palabra = Character.toString(letra);
        p = p.retornaLd();
        while(!finDeRecorrido(p))
        {
            letra = (char)p.retornaDato();
            palabra = palabra+Character.toString(letra);
            p = p.retornaLd();
        }
        return palabra;
    }
    
    /**
     * Compara 2 hileras diferentes y devuelve cuantas letras distintas hay 
     * entre ambas
     * @param b que es de tipo Hilera, es la hilera con la cual se compara
     * la hilera que invoca el método
     * @return n que es de tipo Int y contiene la cantidad de letras diferentes
     * entre ambas hileras.
     */
    public int cantidadLetrasDiferentes(Hilera b)
    {
        NodoDoble p, q;
        int n = 0;
        p = primerNodo();
        q = b.primerNodo();
        while(!finDeRecorrido(p) || !b.finDeRecorrido(q))
        {
            if(p == null && q.retornaDato()!=null)
            {
                n=n+1;
                q = q.retornaLd();
            }
            else
            {
                if(q == null && p.retornaDato()!=null)
                {
                    n=n+1;
                    p = p.retornaLd();
                }
                else
                {
                    if(p.retornaDato()!=q.retornaDato())
                    {
                        n = n+1;
                    }
                    p = p.retornaLd();
                    q = q.retornaLd();
                }  
            }
        }
        return n;
    }

}
