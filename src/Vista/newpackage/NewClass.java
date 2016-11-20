/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.newpackage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author jfwc1
 */
public class NewClass {
 
    

public static void main(String[] args){
        //hay que importar la librerai Random
        //java.util.Random

        //generador de numeros aleatorios
        Random generadorAleatorios = new Random();
         for (int i = 0; i < 100; i++) {
        //genera un numero entre 1 y 5 y lo guarda en la variable numeroAleatorio
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(50, 90 + 1);;
        //imprimo el numero en consola
        System.out.println(numeroAleatorio);
    }
        

    }
}
