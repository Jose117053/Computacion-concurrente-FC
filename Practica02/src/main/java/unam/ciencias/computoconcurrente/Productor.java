package unam.ciencias.computoconcurrente;

class Productor implements Runnable {//no debe producir si el buffer está lleno
    private Buffer buffer;
    private static int idCounter = 0;
    private int id;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
        this.id = idCounter++;
    }

    public void run() {
        //Aqui va tu codigo

    }
}
