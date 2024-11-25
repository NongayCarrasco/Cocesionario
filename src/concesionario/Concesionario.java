/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package concesionario;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Nongay
 */
public class Concesionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //El número de clientes máximos que pueden acceder a la vez a probar un coche serán 4, el número de coches totales
        Semaphore semaforo = new Semaphore(Coche.NUM_COCHES);
                
        //Inicializo los coches 
        Coche[] coches = new Coche[Coche.NUM_COCHES];
        for (int i = 0; i < Coche.NUM_COCHES; i++){
            coches[i] = new Coche(i + 1); //Con "i + 1" nos aseguramos de que idCoche empieze en 1 y no en 0
        }
        
        //Creo los hilos que serán los clientes
        for (int i = 1; i <= Cliente.NUM_CLIENTES; i++){
            //i representará idCliente, semaforo controlará el flujo de los hilos (clientes) y coches es el array que representa a los 4 coches que el concesionario tiene disponibles para probar
            Cliente c = new Cliente(i, semaforo, coches);
            c.start();
        }
    }
    

    
}
