/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionario;

/**
 * Esta clase representará los coches
 * @author Nongay
 */
public class Coche {
    public static final int NUM_COCHES = 4; //Constante que define el número de coches en total
    private final int idCoche; //ID de cada coche
    private boolean libre; //Estado del coche, devolverá true (libre) o false (ocupado)
    
    public Coche(int idCoche) {
        this.idCoche = idCoche;
        this.libre = true; //Al principio el coche no estará en uso
    }

    public synchronized boolean isLibre() {
        return libre;
    }

    public synchronized void setLibre(boolean libre) {
        this.libre = libre;
    }

    public synchronized int getIdCoche() {
        return idCoche;
    }
    
    public synchronized void cogerCoche(int idCliente) throws InterruptedException{
        //Si el coche al que intenta acceder el hilo(cliente) está ocupado, esperará
        while(!libre){
            wait(); 
        }
        libre = false; //Asigno el coche, que dejará de estar libre
    }
    
    public synchronized void dejarCoche(int idCliente){
        libre = true; //El cliente deja el coche, que pasa a estar libre de nuevo
        notifyAll(); //Notificamos a los cliente que el recurso(coche) está liberado
    }    
}
