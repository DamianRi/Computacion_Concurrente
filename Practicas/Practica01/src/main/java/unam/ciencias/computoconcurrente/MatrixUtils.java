package unam.ciencias.computoconcurrente;

public class MatrixUtils implements Runnable{
    private int threads;
    private static int[] posiblesMinimos; // Arreglo para que cada hilo guarde su minimo encontrado
    private static int i;
    private static int j;
    private static int[] matrixGlobal; 
    private static int secciones; 


    public MatrixUtils() {
        this.threads = 1;
        this.i = 0;
        this.j = this.threads -1;
    }


    public MatrixUtils(int threads) {
        this.threads = threads;
        posiblesMinimos = new int[this.threads];
        this.i = 0;
        this.j = this.threads -1;
    }

    @Override
    public void run() {
        int minimoLocal = 0;
        int compara;
        /*
        System.out.println("Numero de hilos: "+threads);
        System.out.println("Hilo ID: "+ hiloID);
        
        System.out.println("Renglon a revisar: " + k);
        
        System.out.println(matrixGlobal[0][0]);
        System.out.println("ID: "+ID);
        System.out.println("Secciones: "+secciones);
        System.out.println("i: "+hiloID*secciones);
        */ 
        String ID = Thread.currentThread().getName();
        int hiloID = Integer.valueOf(ID);
        for (int i = hiloID*secciones; i < (hiloID+1)*secciones -1; i++) {
            if (minimoLocal > matrixGlobal[i]) {
                minimoLocal = matrixGlobal[i];
            }
        }
        posiblesMinimos[hiloID] = minimoLocal;
    }

    public int findMinimum(int[][] matrix) throws InterruptedException{
        if (this.threads == 1) {
            return minimo(matrix);
        }else{
            posiblesMinimos = new int[threads];
            secciones = matrix.length*matrix[0].length/threads;
            System.out.println("Número de Secciones: "+secciones);
            matrixGlobal = new int[matrix.length*matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrixGlobal[i*matrix[0].length + j] = matrix[i][j];
                }
            }

            for (int i = 0; i < this.threads; i++) {
                Thread t = new Thread(new MatrixUtils());
                t.setName(String.valueOf(i));
                t.start();
                t.join();
                //Thread.sleep(100);
            }

            int minimoGlobal = 0;
            for (int i = 0; i < posiblesMinimos.length; i++) {
                if (minimoGlobal > posiblesMinimos[i]) {
                    minimoGlobal = posiblesMinimos[i];
                }
            }
            return minimoGlobal;
        }
    }

    public int minimo(int[][] matrix){
        int min = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]<= min){
                    min = matrix[i][j];
                }
            }
            
        }
        return min;
    }
  
}
