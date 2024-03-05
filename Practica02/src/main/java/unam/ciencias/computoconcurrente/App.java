package unam.ciencias.computoconcurrente;

public class App {
    static int numProductores;
    static int numConsumidores;
    public static void main(String[] args) {
        // Crear buffer compartido
        Buffer buffer = new Buffer();

        // Número de productores y consumidores
        numProductores = 5;
        numConsumidores = 3;

        // Crear arreglos de productores y consumidores
        Productor[] productores = new Productor[numProductores];
        Consumidor[] consumidores = new Consumidor[numConsumidores];

        // Inicializar productores
        for (int i = 0; i < numProductores; i++) {
            productores[i] = new Productor(buffer);
            new Thread(productores[i]).start();
        }

        // Inicializar consumidores
        for (int i = 0; i < numConsumidores; i++) {
            consumidores[i] = new Consumidor(buffer);
            new Thread(consumidores[i]).start();
        }
    }
}

/*
mau@nostromo:~/Practica02$ java -jar target/practica02-1.0.jar 

Productor 0 produjo: 0
Productor 1 produjo: 0
Consumidor 0 consumió: 0
Consumidor 1 consumió: 0
Productor 2 produjo: 0
Consumidor 2 consumió: 0
Productor 3 produjo: 0
Productor 1 produjo: 1
Consumidor 0 consumió: 1
Productor 1 produjo: 2
Consumidor 0 consumió: 2
Productor 2 produjo: 1
Consumidor 1 consumió: 1
Productor 0 produjo: 1
...

 */
