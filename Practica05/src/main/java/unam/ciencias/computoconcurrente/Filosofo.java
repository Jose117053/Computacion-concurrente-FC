package unam.ciencias.computoconcurrente;

/**
 * Cada filósofo se ejecuta en un hilo.
 */
public class Filosofo implements Runnable {
    private int id;
    private Object palilloIzquierdo, palilloDerecho;
    private boolean haComido;
    private final String color;


    public Filosofo(int id, Object palilloIzquierdo, Object palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.haComido = false;
        this.color = "\033[3" + (id % 8 + 1) + "m";
    }

    public void run() {
        //Aqui va tu codigo
        try {
            while (!haComido) {
                pensar();
                System.out.println(color);
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Esto no deberia pasar");
        }
    }

    private void pensar() throws InterruptedException {
        //Aqui va tu codigo
        System.out.println(color +"Filósofo " + id + " está pensando...");
        Thread.sleep((long)(Math.random() * 1000));
    }

    /**
     * Hint: Aqui es donde se sincronizan los procesos para agarrar los palillos
     * Utiliza synchronized()
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
     */
    private void comer() throws InterruptedException {
        //Aqui va tu codigo
        synchronized (palilloIzquierdo) {

            System.out.println(color +"Filósofo " + id + " tomó el tenedor izquierdo.");

            synchronized (palilloDerecho) {

                System.out.println(color +"Filósofo " + id + " tomó el tenedor derecho.");
                System.out.println(color +"Filósofo " + id + " entra en su sección crítica (comienza a comer).");
                System.out.println(color +"Filósofo " + id + " está comiendo.");
                Thread.sleep(1000);
                haComido = true;
                System.out.println(color +"Filósofo " + id + " soltó el tenedor derecho.");

            }

            System.out.println(color +"Filósofo " + id + " soltó el tenedor izquierdo.\n");

        }
    }

    public boolean haComido() {
        return haComido;
    }


}
