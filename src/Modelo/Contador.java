package Modelo;

import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Emanuel
 */
public class Contador implements java.io.Serializable
{
    private static int tiempo; //en milisegundos
    static Timer timer;
    
    public Contador(int tiempo)
    {
        this.tiempo = tiempo;
        this.timer = new Timer();
    }   
    
    public void iniciarContador()
    {
        int delay = 300;
        int periodo = 300;
        this.timer.scheduleAtFixedRate(new TimerTask() 
        {            
            @Override
            public void run() { 
                actualizarContador();
            }
        }, delay, periodo);
    }    
    
    public void reiniciarTimer()
    {
        timer.cancel();
        timer.purge();
    }
    
    /**
     * Actualiza el valor del contador a medida que avanza el tiempo.
     * @return Cadena con el valor actualizado del contador.
     */
    private static void actualizarContador() 
    {
    if (tiempo == 0){        
        timer.cancel();                
    }
    //Convierte el tiempo de milisegundos al formato min:seg
    //String contador = String.format("%02d:%02d", 
    //        (int)((tiempo / (1000*60)) % 60), (int)(tiempo/ 1000) % 60);
    tiempo = tiempo -1000;           
    //return contador;
}
    
    public int getTiempo() 
    {
        return tiempo;
    }    

    public static void setTiempo(int tiempo) 
    {        
        Contador.tiempo = tiempo;
    }
    
    public String retornaTiempo()
    {
        String contador = String.format("%02d:%02d", 
            (int)((tiempo / (1000*60)) % 60), (int)(tiempo/ 1000) % 60);
        return contador;
    }
    
    
    
}