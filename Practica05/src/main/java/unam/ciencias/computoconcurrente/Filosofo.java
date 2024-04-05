package unam.ciencias.computoconcurrente;

/**
 * Cada filósofo se ejecuta en un hilo.
 */
public class Filosofo implements Runnable {
    private int id;
    private Object palilloIzquierdo, palilloDerecho;
    private boolean haComido;

    public Filosofo(int id, Object palilloIzquierdo, Object palilloDerecho) {
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

    /**
     * Hint: Aqui es donde se sincronizan los procesos para agarrar los palillos
     * Utiliza synchronized()
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
     */
    private void comer() throws InterruptedException {
        //Aqui va tu codigo
    }

    public boolean haComido() {
        return haComido;
    }
}
