package unam.ciencias.computoconcurrente;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils implements Runnable {
    private int threads;
    private static int matriz[][];
    private int inicio,fin;
    private static List<Double> listaPromedios;
    public MatrixUtils() {
        this.threads = 1;
    }

    public MatrixUtils(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public MatrixUtils(int inicio, int fin) {
        this.inicio=inicio;
        this.fin=fin;
    }

    @Override
    public void run() {
        //Aqui va tu codigo
        for(int i= inicio; i< fin; i++){
            double suma=0;
            for(int j=0; j< matriz[i].length;j++)
                suma+=matriz[i][j];

            listaPromedios.add(suma/matriz[i].length);
        }

    }

    public double findAverage(int[][] matrix) throws InterruptedException {

        Thread[] hilos= new Thread[threads];
        listaPromedios=new ArrayList<>();
        matriz=matrix;
        int rango=  matrix.length/threads;
        inicio=0;
        fin =rango;

        for (int i = 0; i < threads; i++) {
            Runnable runnable = (i !=threads-1) ? new MatrixUtils(inicio,fin) : new MatrixUtils(inicio, matrix.length);
            hilos[i] = new Thread(runnable);
            hilos[i].start();
            inicio=fin;
            fin+=rango;
        }

        for(Thread thread: hilos)
            thread.join();

        double sumaFInal=0;

        for(double entero: listaPromedios)
            sumaFInal+=entero;

        return sumaFInal/listaPromedios.size() ;
    }

}
