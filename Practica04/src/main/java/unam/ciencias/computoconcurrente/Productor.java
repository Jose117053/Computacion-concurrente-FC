package unam.ciencias.computoconcurrente;

class Productor implements Runnable {
    private Buffer buffer;
    private static int idCounter = 0;
    private int id;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
        this.id = idCounter++;
    }

    public void run() {
        buffer.producir(id);
    }
}
