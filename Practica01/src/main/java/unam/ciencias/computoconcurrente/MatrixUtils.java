package unam.ciencias.computoconcurrente;
import java.util.ArrayList;
import java.util.List;
/*
IDEAS:
As the problem says. I will divide de given matrix N times
for now Ill try that it works in the exaplme of the class App
how? I dont know
 */
public class MatrixUtils implements Runnable {
    private int threads;
    private double promedioSegmento;
    private int[] matrizDividida;
    private static List<Double> listaPromedios;

    public MatrixUtils() {
        this.threads = 1;
    }

    public MatrixUtils(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public MatrixUtils(int[] matrizDividida) {
        this.matrizDividida = matrizDividida;
        this.promedioSegmento = 0;
    }


    @Override
    public void run() {
        //Aqui va tu codigo
        double sum=0;
        for (int j : matrizDividida)
            sum += j;
        listaPromedios.add(sum/matrizDividida.length);
    }

    public double findAverage(int[][] matrix) throws InterruptedException {
        Thread[] hilos = new Thread[threads];
        int rango = matrix.length / threads;
        int inicio = 0;
        listaPromedios = new ArrayList<Double>();

        for (int i = 0; i < threads; i++) {
            int finalJ = (i == threads - 1) ? matrix.length : rango;
            for (int j = inicio; j < finalJ; j++) {
                matrizDividida = matrix[j];
                Runnable runnable = new MatrixUtils(matrizDividida);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
            }
            inicio = rango;
            rango += rango;
        }
        try {
                for (int m = 0; m < threads; m++)
                    hilos[m].join();
            } catch (Exception e) {

            }

        double sumafinal = 0;
        for (int p=0; p< listaPromedios.size();p++)
            sumafinal += listaPromedios.get(p);

        sumafinal /= listaPromedios.size();
        return sumafinal;

    }

}
