package unam.ciencias.computoconcurrente;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Recepcionista {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore empty = new Semaphore(2); // Numero de cuantas personas puede atender a la vez
    private Semaphore full = new Semaphore(0);
    private static final String[] listaPasteles={"3 Leches", "Cheesecake", "Chocolate", "Moka","3 Leches" };
    private final Random random=new Random(listaPasteles.length);
    private static final LinkedList<String> pastelesDisponibles=new LinkedList<>();


    public void producir(int id) throws InterruptedException {
        //Aqui va tu codigo
            empty.acquire();
            mutex.acquire();

            int aux=random.nextInt(4);//listapasteles.length
            String seleccionado=listaPasteles[aux];
            pastelesDisponibles.add(seleccionado);
            System.out.println("El pastelero "+id + " ha producido un pastel de" +seleccionado );

            mutex.release();
            full.release();


    }

    public void consumir(int id) throws InterruptedException {
        //Aqui va tu codigo
        full.acquire();
        mutex.acquire();

        String consumido= pastelesDisponibles.removeFirst();
        System.out.println("El consumidor "+id + " ha consumido un pastel de " +consumido );

        mutex.release();
        empty.release();

    }
}
