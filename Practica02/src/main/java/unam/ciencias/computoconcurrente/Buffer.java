package unam.ciencias.computoconcurrente;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Buffer {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore empty = new Semaphore(2); // Numero de cuantas personas puede atender a la vez
    private Semaphore full = new Semaphore(0);
    private String[] listaPasteles={"3 Leches", "Cheesecake", "Chocolate", "Nuez", "Moka"};
    private Random random=new Random();
    private static LinkedList<String> pastelesDisponibles=new LinkedList<>();
    private static int pastelesProducidos=0;
    private static int pastelesConsumidos=0;

    public void producir(int id) throws InterruptedException {

        empty.acquire();
        mutex.acquire();

        int aux=random.nextInt(listaPasteles.length);
        String seleccionado=listaPasteles[aux];
        pastelesDisponibles.add(seleccionado);
        pastelesProducidos++;
        System.out.println("\u001B[32mEl pastelero "+id + " ha producido un pastel de " +seleccionado+"\u001B[0m");

        mutex.release();
        full.release();

    }

    public void consumir(int id) throws InterruptedException {

        //caso cuando consumidores>productores
        if(pastelesProducidos== App.numProductores && pastelesConsumidos==pastelesProducidos){
            System.out.println("Los demas consumidores se quedaron sin pastel :( ");
            System.exit(0);
        }

        full.acquire();
        mutex.acquire();

        String consumido= pastelesDisponibles.removeFirst();
        System.out.println("\u001B[34mEl consumidor "+id + " ha consumido un pastel de " +consumido +"\u001B[0m");
        pastelesConsumidos++;

        mutex.release();
        empty.release();

    }
}
