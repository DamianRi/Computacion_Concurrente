package unam.ciencias.computoconcurrente;
import java.lang.Math;
public class PrimeNumberCalculator implements Runnable{

    private int threads;
    private static int numPrimo;
    public static boolean result;
    public static int longitudSubInter; //Dividimos el intervalo [2,N-1] en this.threads cantidad de sub interbalos, uno por cada hilo


    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }
    

    public boolean isPrime(int n) throws InterruptedException{
        result = true;
        numPrimo = n;
        longitudSubInter = (int) numPrimo/threads;
        if( Math.abs(n) == 0 ||  Math.abs(n) == 1){
            return false;
        }else{
            for (int i = 0; i < this.threads; i++) { // Creamos 'threas' cantidad de hilos
               
                Thread t = new Thread(new PrimeNumberCalculator()); // Aquí se instancia un objeto de la clase dentro del hilo
                t.setName(String.valueOf(i)); // Asignamos un nombre para que cada hilo sepa su rango a revisar 
                t.start();
                t.join();
            }
        }
        return result;  

    }
    

    @Override
    public void run(){
        String ID = Thread.currentThread().getName();
        int idhilo = Integer.valueOf(ID);

        if ((longitudSubInter*(idhilo+1)) >= numPrimo) { // Caso en el que el intervalo sobre pase el valor del número primo 
            for(int i = 2 + longitudSubInter * idhilo; i < numPrimo-1; i++){
                if( Math.abs(numPrimo)%i == 0){
                    result = false;
                    return;
                } 
            }            
        }else{
            for(int i = 2 + longitudSubInter * idhilo; i < (longitudSubInter*(idhilo+1))-1; i++){
                if( Math.abs(numPrimo)%i == 0){
                    result = false;
                    return;
                } 
            }
        }
    }

    
}
