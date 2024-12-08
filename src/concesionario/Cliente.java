/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionario;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase representa los clientes
 * @author Nongay
 */
public class Cliente extends Thread {
    public static final int NUM_CLIENTES = 9; //Constante que define el número de clientes en total
    private final int idCliente;
    private final Semaphore semaforo;  
    private final Coche[] coches; 

    /**
     * Constructor por parámetros
     * @param idCliente id de cada cliente
     * @param semaforo Semáforo que controlará cuantos clientes podrán acceder a los coches
     * @param coches Array que contiene los 4 coches
     */
    public Cliente(int idCliente, Semaphore semaforo, Coche[] coches) {
        this.idCliente = idCliente;
        this.semaforo = semaforo;
        this.coches = coches;
    }
    
    /**
     * Método que le da funcionalidad al hilo que se va a ejecutar
     */
    @Override
    public void run(){
        try {
            //Protejo la sección crítica para que no pueda acceder a ella ningún otro hilo
            semaforo.acquire();
                //Bucle que recorre el array de coches
                for (Coche coche : coches){
                    //Intenta coger el coche (hasta que el cliente no coja el coche no lo podrá probar)
                    coche.cogerCoche(idCliente);
                    
                    //El cliente prueba el coche
                    probarCoche(coche);
                    
                    //El cliente dejará de probar el coche, el recurso queda liberado
                    System.out.println("Cliente " + idCliente + " dejo de probar vehiculo " + coche.getIdCoche());
                    coche.dejarCoche(idCliente);    
                }
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //Libero la sección crítica para que otros hilos (clientes) puedan acceder a ella
            semaforo.release();
        }
    }
    
    /**
     * Simula el momento en el que el cliente está probando un coche
     * @param coche Coche que está probando el cliente
     */
    private void probarCoche(Coche coche){
        System.out.println("Cliente " + idCliente + " probando vehiculo " + coche.getIdCoche());
       
        try {
            //Simulo el tiempo que el cliente estará probando el coche, entre 2 y 5 segundos
            long tiempo = (long) (Math.random() * 3000 + 2000);
                
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
