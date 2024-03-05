package unam.ciencias.computoconcurrente;

import java.util.concurrent.Semaphore;

/**
 * Cada filósofo se ejecuta en un hilo.
 */
public class Filosofo implements Runnable {

    private int id;
    private Semaphore palilloIzquierdo, palilloDerecho;

    public Filosofo(int id, Semaphore palilloIzquierdo, Semaphore palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
    }

    public void run() {
        //Aqui va tu codigo
    }

    private void piensa() throws InterruptedException {
        //Aqui va tu codigo
    }

    private void come() throws InterruptedException {
        //Aqui va tu codigo
    }

}