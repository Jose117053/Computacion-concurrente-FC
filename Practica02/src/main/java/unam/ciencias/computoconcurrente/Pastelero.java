package unam.ciencias.computoconcurrente;

class Pastelero implements Runnable {//no debe producir si el buffer está lleno
    private Recepcionista buffer;
    private static int idCounter = 0;
    private int id;

    public Pastelero(Recepcionista buffer) {
        this.buffer = buffer;
        this.id = idCounter++;
    }

    public void run() {
        //Aqui va tu codigo
        try {

            buffer.producir(id);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
