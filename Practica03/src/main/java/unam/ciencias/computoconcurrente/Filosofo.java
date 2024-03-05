package unam.ciencias.computoconcurrente;

import java.util.concurrent.Semaphore;

/**
 * Cada filósofo se ejecuta en un hilo.
 */
public class Filosofo implements Runnable {

    private int id;
    private Semaphore palilloIzquierdo, palilloDerecho;
    private boolean haComido;

    public Filosofo(int id, Semaphore palilloIzquierdo, Semaphore palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.haComido = false;
    }

    public void run() {
        //Aqui va tu codigo
    }

    private void pensar() throws InterruptedException {
        //Aqui va tu codigo
    }

    private void comer() throws InterruptedException {
        //Aqui va tu codigo
    }

    public boolean haComido() {
        return haComido;
    }
}
