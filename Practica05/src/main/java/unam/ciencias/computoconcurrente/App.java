package unam.ciencias.computoconcurrente;

public class App {

    public static void main(String[] args) {
        int numFilosofos = 5;
        Object[] palillos = new Object[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Object();
        }

        Filosofo[] filosofos = new Filosofo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            Object palilloIzquierdo = palillos[i];
            Object palilloDerecho = palillos[(i + 1) % numFilosofos];
            filosofos[i] = new Filosofo(i, palilloIzquierdo, palilloDerecho);
            new Thread(filosofos[i]).start();
        }

        boolean todosHanComido = false;
        while (!todosHanComido) {
            todosHanComido = true;
            for (Filosofo filosofo : filosofos) {
                if (!filosofo.haComido()) {
                    todosHanComido = false;
                    break;
                }
            }

            if (!todosHanComido) {
                try {
                    Thread.sleep(100); // Esperar un poco para dar tiempo a los filósofos a comer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Todos los filósofos han comido al menos una vez.");
    }
}

/*

Ejemplo de ejecución

$ java -jar target/practica05-1.0.jar

Filósofo 0 está pensando...
Filósofo 3 está pensando...
Filósofo 2 está pensando...
Filósofo 1 está pensando...
Filósofo 4 está pensando...

Filósofo 2 tomó el tenedor izquierdo.
Filósofo 2 tomó el tenedor derecho.
Filósofo 2 entra en su sección crítica (comienza a comer).
Filósofo 2 está comiendo.

Filósofo 3 tomó el tenedor derecho.
Filósofo 0 tomó el tenedor izquierdo.
Filósofo 0 tomó el tenedor derecho.
Filósofo 0 entra en su sección crítica (comienza a comer).
Filósofo 0 está comiendo.
Filósofo 0 está pensando...
Filósofo 2 está pensando...

Filósofo 1 tomó el tenedor derecho.
Filósofo 1 tomó el tenedor izquierdo.
Filósofo 1 entra en su sección crítica (comienza a comer).
Filósofo 1 está comiendo.

Filósofo 3 tomó el tenedor izquierdo.
Filósofo 3 entra en su sección crítica (comienza a comer).
Filósofo 3 está comiendo.
Filósofo 3 está pensando...

Filósofo 4 tomó el tenedor izquierdo.
Filósofo 4 tomó el tenedor derecho.
Filósofo 4 entra en su sección crítica (comienza a comer).
Filósofo 4 está comiendo.
Filósofo 4 está pensando...

Filósofo 0 tomó el tenedor izquierdo.
Filósofo 1 está pensando...
Filósofo 0 tomó el tenedor derecho.
Filósofo 0 entra en su sección crítica (comienza a comer).
Filósofo 0 está comiendo.

Filósofo 2 tomó el tenedor izquierdo.
Filósofo 2 tomó el tenedor derecho.
Filósofo 2 entra en su sección crítica (comienza a comer).
Filósofo 2 está comiendo.

Todos los filósofos han comido al menos una vez.

Filósofo 3 tomó el tenedor derecho.
Filósofo 0 está pensando...
Filósofo 2 está pensando...
Filósofo 3 tomó el tenedor izquierdo.
....

*/