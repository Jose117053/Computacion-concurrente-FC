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

        promedioSegmento=sum/matrizDividida.length;
       // System.out.println(promedioSegmento+" este es el promedio de un solo segmento");
        listaPromedios.add(promedioSegmento);
      //  System.out.println(listaPromedios);
    }

    public double findAverage(int[][] matrix) throws InterruptedException {


        //Aqui va tu codigo
        Thread[] hilos = new Thread[4];
        //  System.out.println(hilos.length + "hilos length");
        //System.out.println(matrix.length + "matriz length");
        int rango = matrix.length / 4;
        int inicio = 0;
        int fin = rango;


        listaPromedios = new ArrayList<Double>();
        for (int i = 0; i < threads; i++) {
            if (i != threads - 1) {
                for (int j = inicio; j < fin; j++) {
                   // System.out.println(fin + " fin");
                  //  System.out.println("ejecucion " + j);
                    matrizDividida = matrix[j];
                    Runnable runnable = new MatrixUtils(matrizDividida);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                }
            } else {
                for (int j = inicio; j < matrix.length; j++) {
                    matrizDividida = matrix[j];
                    Runnable runnable = new MatrixUtils(matrizDividida);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                }


            }
            inicio = fin;
            fin += rango;
        }

            try {
                for (int m = 0; m < 4; m++)
                    hilos[m].join();
            } catch (Exception e) {

            }


            double sumafinal = 0;

          //  System.out.println(listaPromedios.toString() + " promedios lista");
            for (double p : listaPromedios) {

                sumafinal += p;

            }


            sumafinal /= listaPromedios.size();

            //   System.out.println(sumafinal+ "ESTA ES LA SUMA FINAL SE SUOPNE");
            return sumafinal;


    }

}
