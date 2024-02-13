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

    @Override
    public void run() {
        //Aqui va tu codigo
        for(int i=inicioSegmento; i < finalSegmento; i++) {
            if (numero % i == 0) {
                this.resultado=false;
             //   System.out.println("aqui detecto que no es un numero primo, debe de decir false, dice: " + resultado);
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
        //Aqui va tu codigo
        Thread[] hilos=new Thread[threads];
        int intervalo= n/threads;
        finalSegmento=intervalo;
        inicioSegmento=2;
        numero=n;
        resultado=true;
        for(int i=0; i<threads;i++){

            if(i !=threads -1) {
                Runnable runnable = new PrimeNumberCalculator(numero, inicioSegmento, finalSegmento);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
                inicioSegmento = finalSegmento;
                finalSegmento += intervalo;
            }else{
                Runnable runnable = new PrimeNumberCalculator(numero, inicioSegmento, numero);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
            }

        }

        for(int i=0; i<threads;i++)
            hilos[i].join();


       // System.out.println(resultado);

        return resultado;
    }

}
