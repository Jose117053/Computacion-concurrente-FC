package unam.ciencias.computoconcurrente;

public class MatrixUtils implements Runnable {
    private int threads;
    private double promedioSegmento;
    private int[] matrizDividida;
    private Double[] promedios;
    private double suma;
    static int contador=0;

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
        suma=0.0;
        for(int i=0;i<matrizDividida.length;i++){
            suma+=matrizDividida[i];
        }



        contador++;
        //puesto que se va a ejecutar esta linea antes que hilos.start();


    }

    public double findAverage(int[][] matrix) throws InterruptedException {
        //Aqui va tu codigo
        promedios=new Double[4];

        int rango=4/threads;
        int inicio=0;
        Thread[] hilos= new Thread[threads];
        for(int i=0; i< threads;i++){
            for(int j=inicio; j< rango;j++){
                matrizDividida=matrix[j];
                Runnable runn=new MatrixUtils(matrizDividida);
                hilos[i]= new Thread(runn);
                hilos[i].start();

            }
            inicio+=rango;

        }
        for(int i=0; i< threads; i++)
            hilos[i].join();
        promedioSegmento = suma/matrizDividida.length;
        System.out.println("contador: "+contador);
        promedios[contador]= promedioSegmento;// se supone que debe de fallar aqui



        Double promedioFInal=0.0;
        for(int i=0; i<promedios.length;i++)
            promedioFInal+=promedios[i];

        return promedioFInal/promedios.length ;
    }

}
