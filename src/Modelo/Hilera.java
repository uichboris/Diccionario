package Modelo;
/**
 *
 * @author marioh.ramirez
 */
public class Hilera extends ListaDoblementeLigada 
{      
    public Hilera(){}//Constructor de la clase Hilera
    
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
    
    public int longitud() //Retorna la longitud de la hilera
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
    

    public NodoDoble posicion(Hilera s)//Devuelve el nodo en el cuál comience la hilera que se ingresa como parámetro, dentro de otra hilera
    {
        NodoDoble p, pp, q;
        p = primerNodo();
        pp = p;
        q = s.primerNodo();
        while(!finDeRecorrido(p) && !s.finDeRecorrido(q))
        {
            if(p.retornaDato()==q.retornaDato())
            {
                p = p.retornaLd();
                q = q.retornaLd();
                if(s.finDeRecorrido(q))
                {
                    return pp;
                }
            }
            else
            {
                pp = pp.retornaLd();
                p = pp;
                q = s.primerNodo();
            }
        }
        return null;        
    }
    
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
                if(p.retornaDato()!=q.retornaDato())
                {
                    n = n+1;
                }
                p = p.retornaLd();
                q = q.retornaLd();
            }            
        }
        return n;
    }

}
