package unam.ciencias.computoconcurrente;

import java.util.HashMap;
import java.util.Map;

/**
 * Cada filósofo se ejecuta en un hilo.
 */
public class Filosofo implements Runnable {
    private int id;
    private Object palilloIzquierdo, palilloDerecho;
    private boolean haComido;
    private static final Map<Integer, String> colores = new HashMap<>();
    static {
        colores.put(0, "\u001B[31m"); // cyan
        colores.put(1, "\u001B[32m"); // Verde
        colores.put(2, "\u001B[33m"); // Amarillo
        colores.put(3, "\u001B[34m"); // Azul
        colores.put(4, "\u001B[35m"); // Morado
    }

    public Filosofo(int id, Object palilloIzquierdo, Object palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.haComido = false;
    }

    public void run() {
        //Aqui va tu codigo
        try {
            while (!haComido) {
                pensar();
                System.out.println(colores.get(id));
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println();
        }
    }

    private void pensar() throws InterruptedException {
        //Aqui va tu codigo
        System.out.println(colores.get(id) +"Filósofo " + id + " está pensando...");
        Thread.sleep((long)(Math.random() * 1000)); // Pensar durante un tiempo aleatorio
    }

    /**
     * Hint: Aqui es donde se sincronizan los procesos para agarrar los palillos
     * Utiliza synchronized()
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
     */
    private void comer() throws InterruptedException {
        //Aqui va tu codigo
        synchronized (palilloIzquierdo) {

            System.out.println(colores.get(id)+"Filósofo " + id + " tomó el tenedor izquierdo.");

            synchronized (palilloDerecho) {

                System.out.println(colores.get(id) +"Filósofo " + id + " tomó el tenedor derecho.");
                System.out.println(colores.get(id) +"Filósofo " + id + " entra en su sección crítica (comienza a comer).");
                System.out.println(colores.get(id) +"Filósofo " + id + " está comiendo.");
                Thread.sleep(1000);
                haComido = true;
                System.out.println(colores.get(id) +"Filósofo " + id + " soltó el tenedor derecho.");

            }

            System.out.println(colores.get(id) +"Filósofo " + id + " soltó el tenedor izquierdo.\n");

        }
    }

    public boolean haComido() {
        return haComido;
    }


}
