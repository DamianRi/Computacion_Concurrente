package unam.ciencias.computoconcurrente;

public class MatrixUtils implements Runnable{
    private int threads;
    private static int[] posiblesMinimos; // Arreglo para que cada hilo guarde su minimo encontrado
    private static int[] matrixGlobal; 
    private static int secciones; 

    public MatrixUtils() {
        this.threads = 1;
    }

    public MatrixUtils(int threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        int minimoLocal = 0;
        String ID = Thread.currentThread().getName();
        int hiloID = Integer.valueOf(ID);

        // Cachamos que algun hilo no sobre pase el rango del arreglo
        if (matrixGlobal.length - ((hiloID+1)*secciones -1) < secciones) {
            //System.out.println("Un hilo entro en el caso que no se divide correctamente");        
            for (int i = hiloID*secciones; i < matrixGlobal.length; i++) {
                if (minimoLocal > matrixGlobal[i]) {
                    minimoLocal = matrixGlobal[i];
                }
            }    
        }else{
            //System.out.println("Un hilo entro en el caso normal");
            for (int i = hiloID*secciones; i < (hiloID+1)*secciones -1; i++) {
                if (minimoLocal > matrixGlobal[i]) {
                    minimoLocal = matrixGlobal[i];
                }
            }
        }
        posiblesMinimos[hiloID] = minimoLocal;
    }

    public int findMinimum(int[][] matrix) throws InterruptedException{

        //Caso en el que solo hay un hilo, recorremos toda la matriz
        if (this.threads == 1) {
            return minimo(matrix);

        }else{
            
            posiblesMinimos = new int[threads];
            secciones = matrix.length*matrix[0].length/threads; // numero de elementos que recorre cada hilo
            matrixGlobal = new int[matrix.length*matrix[0].length]; // Creamos la matriz unidimensional

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrixGlobal[i*matrix[0].length + j] = matrix[i][j]; // Pasamos la matrix a un arreglo unidimensional
                }
            }

            // Creamos 'threads' cantidad de hilos 
            for (int i = 0; i < this.threads; i++) {
                Thread t = new Thread(new MatrixUtils());
                t.setName(String.valueOf(i)); // Les asignamos un nombre especifico
                t.start();
                t.join();
            }

            // Buscamos el mínimo global de los minimos de cada hilo
            int minimoGlobal = 0;
            for (int i = 0; i < posiblesMinimos.length; i++) {
                if (minimoGlobal > posiblesMinimos[i]) {
                    minimoGlobal = posiblesMinimos[i];
                }
            }
            return minimoGlobal;
        }
    }

    /**
     * Metodo que recorre una matriz de dos dimensiones 
     * @param matrix - matriz de dos dimensiones 
     * @return min - el elemento con el valor minimo en la matriz
     */
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
