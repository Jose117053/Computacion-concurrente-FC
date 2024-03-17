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

        try {
            while (!haComido()) {
                pensar();
                System.out.println();
                tomarPalillos();
                comer();
                soltarPalillos();
                System.out.println();
            }
        } catch (InterruptedException e) {
            System.out.println("esto no deberia pasar: " );
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep((long) (Math.random() * 2000));
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " entra en su sección crítica (comienza a comer).");
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep((long) (Math.random() * 2000));
    }

    public boolean haComido() {
        return haComido;
    }

    private void tomarPalillos() throws InterruptedException {
        palilloIzquierdo.acquire();
        System.out.println("Filósofo " + id + " tomó el tenedor izquierdo.");
        palilloDerecho.acquire();
        System.out.println("Filósofo " + id + " tomó el tenedor derecho.");
    }


    private void soltarPalillos() {
        palilloIzquierdo.release();
        System.out.println("Filósofo " + id + " ha terminado de comer");
        System.out.println("Filósofo " + id + " soltó el tenedor izquierdo.");
        palilloDerecho.release();
        System.out.println("Filósofo " + id + " soltó el tenedor derecho.");
        haComido = true; // para que se ejecute al final
    }

}
