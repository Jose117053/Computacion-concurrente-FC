package unam.ciencias.computoconcurrente;

public class PrimeNumberCalculator implements Runnable {
    private int threads;
    private int numero;
    private int inicioSegmento;
    private int finalSegmento;
    private static boolean resultado;

    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public PrimeNumberCalculator(int numero, int inicioSegmento, int finalSegmento) {
        this.numero = numero;
        this.inicioSegmento = inicioSegmento;
        this.finalSegmento = finalSegmento;
    }

    /**
     * Dado un rango de numeros diferente para cada hilo
     * se comprueba que el numero de referencia no sea multiplo
     * de algun numero dentro del rango.
     * */
    @Override
    public void run() {
        for(int i=inicioSegmento; i < finalSegmento; i++) {
            if(!resultado)//cuando algun hilo detecta que no es primo ya no tiene caso que los demas hilos verifiquen su rango de numeros
                return;    //Hay una mayor diferencia en numeros bastante grnades
            if (numero % i == 0) {
                resultado = false;
                return;
            }
        }

    }

    /**
    * Metodo para saber si el número dado es primo.
    * @param n Número que queremos ver si es primo.
    * @throws InterruptedException
    * @return Si es primo o no es primo.
    */
    public boolean isPrime(int n) throws InterruptedException {
        if(n<2)
            return false;

        Thread[] hilos=new Thread[threads];
        int intervalo= n/threads;
        finalSegmento=intervalo;
        inicioSegmento=2;
        numero=n;
        resultado=true;

        for (int i = 0; i < threads; i++) {
            finalSegmento = (i != threads - 1) ? finalSegmento : numero;
            Runnable runnable = new PrimeNumberCalculator(numero, inicioSegmento, finalSegmento);
            hilos[i] = new Thread(runnable);
            hilos[i].start();
            inicioSegmento = finalSegmento;
            finalSegmento += intervalo;
        }

        for(int i=0; i<threads;i++)
            hilos[i].join();

        return resultado;
    }

}
