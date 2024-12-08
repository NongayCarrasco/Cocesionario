/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionario;

/**
 * Esta será la clase monitor y representará los coches del concesionario
 * @author Nongay
 */
public class Coche {
    public static final int NUM_COCHES = 4; //Constante que define el número de coches en total
    private final int idCoche;
    private boolean libre; //Estado del coche, devolverá true (libre) o false (ocupado)
    
    /**
     * Constructor por parámetros
     * @param idCoche id de cada coche
     */
    public Coche(int idCoche) {
        this.idCoche = idCoche;
        this.libre = true; //Al principio el coche no estará en uso
    }

    /**
     * Obtiene el estado del coche
     * @return Estado del coche
     */
    public synchronized boolean isLibre() {
        return libre;
    }

    /**
     * Modifica el estado del coche
     * @param libre Nuevo estado del coche
     */
    public synchronized void setLibre(boolean libre) {
        this.libre = libre;
    }

    /**
     * Obtiene el id del coche
     * @return id del coche
     */
    public synchronized int getIdCoche() {
        return idCoche;
    }
    
    /**
     * Simula el momento en el que el cliente empieza a probar una coche
     * @param idCliente id del cliente
     * @throws InterruptedException 
     */
    public synchronized void cogerCoche(int idCliente) throws InterruptedException{
        //Si el coche al que intenta acceder el hilo(cliente) está ocupado, esperará
        while(!libre){
            wait(); 
        }
        libre = false; //Asigno el coche, que dejará de estar libre
    }
    
    /**
     * Simula el momento en el que el cliente deja de probar una coche
     * @param idCliente id del cliente
     */
    public synchronized void dejarCoche(int idCliente){
        libre = true; //El cliente deja el coche, que pasa a estar libre de nuevo
        notifyAll(); //Notificamos a los cliente que el recurso(coche) está liberado
    }    
}
