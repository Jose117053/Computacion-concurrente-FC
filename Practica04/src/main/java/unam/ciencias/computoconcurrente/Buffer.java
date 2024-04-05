package unam.ciencias.computoconcurrente;

import java.util.LinkedList;
import java.util.Random;

class Buffer {

   
    private int capacidad = 2; //Tamaño máximo del buffer
    private int count = 0;

    private String[] listaPasteles={"3 Leches", "Cheesecake", "Chocolate", "Nuez", "Moka"};
     private Random random=new Random();
    private static LinkedList<String> pastelesDisponibles=new LinkedList<>();

    public synchronized void producir(int id) {
        //Aqui va tu codigo 
        while(count == capacidad){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
        count++;

        int aux=random.nextInt(listaPasteles.length);
        String seleccionado=listaPasteles[aux];
        pastelesDisponibles.add(seleccionado);
        System.out.println("\u001B[32mEl pastelero "+id + " ha producido un pastel de " +seleccionado+"\u001B[0m");
        notifyAll();
    }

    public synchronized void consumir(int id) {

         while(count == 0){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
        count--;

        String consumido= pastelesDisponibles.removeFirst();
        System.out.println("\u001B[34mEl consumidor "+id + " ha consumido un pastel de " +consumido +"\u001B[0m");
        notifyAll();
    }

}
