package unam.ciencias.computoconcurrente;
import java.lang.Math;
public class PrimeNumberCalculator {
    private int threads;

    public PrimeNumberCalculator() {
        this.threads = 1;
    }

    public PrimeNumberCalculator(int threads) {
        this.threads = threads > 1 ? threads : 1;
    }

    public boolean isPrime(int n) {
        Math.abs(n);
        if(n ==0 || n == 1)
            return false;
        for(int i=2;this.threads*i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }  

}