package unam.ciencias.computoconcurrente;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils implements Runnable {
    private int threads;
    private static int matriz[][];
    private int inicio,fin;
    private static List<Double> listaPromedios=new ArrayList<>();

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
          //  System.out.println("Esta es el promedio de de una linea: "+suma/matriz[i].length);
            listaPromedios.add(suma/matriz[i].length);

        }

    }

    public double findAverage(int[][] matrix) throws InterruptedException {
        Thread[] hilos= new Thread[threads];
        matriz=matrix;

        int rango=  matrix.length/threads;
        int start=0;
        int finish =rango;

        for (int i = 0; i < threads; i++) {
            if(i !=threads-1){
                Runnable runnable= new MatrixUtils(start,finish);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
                start=finish;
                finish+=rango;
            }else{
                Runnable runnable= new MatrixUtils(start,matrix.length);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
            }

        }

        for(int j=0; j<threads; j++)
            hilos[j].join();


        double sumaFInal=0;
        for(double entero: listaPromedios)
            sumaFInal+=entero;

        System.out.println(sumaFInal/listaPromedios.size() + "esto es el promedio final se supone");
        return sumaFInal/listaPromedios.size() ;
    }

}
