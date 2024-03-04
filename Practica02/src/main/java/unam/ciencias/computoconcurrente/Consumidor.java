package unam.ciencias.computoconcurrente;

class Consumidor implements Runnable {//no debe consumir si el bufeer compartido está vacio
    private Buffer buffer;
    private static int idCounter = 0;
    private int id;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
        this.id = idCounter++;
    }

    public void run() {
        //Aqui va tu codigo
    }
}
