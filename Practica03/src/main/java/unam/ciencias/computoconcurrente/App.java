package unam.ciencias.computoconcurrente;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] palillos = new Semaphore[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Semaphore(1);
        }

        Filosofo[] filosofos = new Filosofo[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            Semaphore palilloIzquierdo = palillos[i];
            Semaphore palilloDerecho = palillos[(i + 1) % numFilosofos];
            filosofos[i] = new Filosofo(i, palilloIzquierdo, palilloDerecho);
            new Thread(filosofos[i]).start();
        }
    }
}

/*

Ejemplo de ejecución

$ java -jar target/practica03-1.0.jar

El filósofo 0 está pensando...
El filósofo 3 está pensando...
El filósofo 2 está pensando...
El filósofo 4 está pensando...
El filósofo 1 está pensando...

El filósofo 0 tomó el tenedor izquierdo.
El filósofo 0 tomó el tenedor derecho.
El filósofo 0 está comiendo.

El filósofo 3 tomó el tenedor derecho.
El filósofo 3 tomó el tenedor izquierdo.
El filósofo 3 está comiendo.

El filósofo 1 tomó el tenedor derecho.
El filósofo 0 está pensando...
El filósofo 1 tomó el tenedor izquierdo.
El filósofo 1 está comiendo.
El filósofo 3 está pensando...

El filósofo 4 tomó el tenedor izquierdo.
El filósofo 4 tomó el tenedor derecho.
El filósofo 4 está comiendo.
El filósofo 1 está pensando...

El filósofo 2 tomó el tenedor izquierdo.
El filósofo 2 tomó el tenedor derecho.
El filósofo 2 está comiendo.

El filósofo 3 tomó el tenedor derecho.
El filósofo 4 está pensando...

El filósofo 0 tomó el tenedor izquierdo.
El filósofo 0 tomó el tenedor derecho.
El filósofo 0 está comiendo.
El filósofo 0 está pensando...
El filósofo 2 está pensando...

El filósofo 3 tomó el tenedor izquierdo.
El filósofo 3 está comiendo.

El filósofo 1 tomó el tenedor derecho.
El filósofo 1 tomó el tenedor izquierdo.
El filósofo 1 está comiendo.
El filósofo 1 está pensando...
....

*/