# Práctica 3: Semáforos - El problema de los Filósofos

La práctica 3 se centra en resolver un problema de concurrencia de recursos, evitando los problemas de inanición (hambruna) y exclusión mutua. El objetivo de esta práctica es implementar una solución a un problema clásico de sincronización mediante el uso de semáforos y subprocesos (hilos).

## Ejercicio 1: El problema de los Filósofos

El problema de los filósofos fue planteado por E. W. Dijkstra, un pionero en concurrencia, para ejemplificar los conceptos de abrazos mortales (deadlocks) e inanición (hambruna).

Se imagina una mesa redonda con cinco sillas alrededor. En la mesa hay cinco platos de arroz y cinco palillos (tenedores). Cada silla representa a un filósofo que pasa su tiempo pensando y comiendo. Cada filósofo piensa durante un tiempo, luego le da hambre, así que toma los dos palillos a su lado para poder comer. Una vez que termina de comer, el filósofo suelta los palillos y vuelve a pensar.

![P-Filosofos](/Practica03/img/problema-filosofos.jpg)

El objetivo es programar una solución a este problema considerando evitar los abrazos mortales y la inanición. El programa debe mostrar el estado de cada filósofo e imprimir mensajes indicando cuando cada uno entra a su sección crítica (comienza a comer) y cuando todos han comido al menos una vez.

Se pueden utilizar las bibliotecas de hilos y semáforos de Java, pero se otorgarán puntos extras si se implementa la solución sin utilizar estas bibliotecas. No olvide documentar ampliamente el código.

## Entregables

Es necesario presentar `2` archivos para la entrega: `1` archivo Java `Filosofo.java` y una captura de pantalla que evidencie la ejecución exitosa de los archivos `dpdr.java` y `dphi.java`. Adicionalmente, se solicita incluir en un comentario privado los nombres de los integrantes del equipo.

- Se permite la importación de las bibliotecas necesarias para llevar a cabo la práctica.
- Los participantes pueden crear todos los métodos auxiliares, atributos, etc., que sean necesarios para completar la práctica.
- La calificación se compone de el correcto funcionamiento de la práctica, la calidad del código y la ejecución correcta de los archivos `dpdr.java` y `dphi.java`.
