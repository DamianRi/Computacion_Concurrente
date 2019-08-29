package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrimeNumberCalculatorTest {

    PrimeNumberCalculator primeNumberCalculator;

    @Test
    void zeroIsNotPrime() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertFalse(primeNumberCalculator.isPrime(0));
    }

    @Test
    void oneIsNotPrime() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertFalse(primeNumberCalculator.isPrime(1));
    }

    @Test
    void negativeIsPrime() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertTrue(primeNumberCalculator.isPrime(-131));
    }


    @Test
    void isPrimeSequential() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertTrue(primeNumberCalculator.isPrime(191));
    }

    @Test
    void isNotPrimeSequential() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertFalse(primeNumberCalculator.isPrime(192));
    }

    @Test
    void isPrimeConcurrent() {
        primeNumberCalculator = new PrimeNumberCalculator(2);

        assertTrue(primeNumberCalculator.isPrime(191));
    }

    @Test
    void isNotPrimeConcurrent() {
        primeNumberCalculator = new PrimeNumberCalculator(2);

        assertFalse(primeNumberCalculator.isPrime(192));
    }

    @Test
    void isPrimeSequentialBigNumber() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertTrue(primeNumberCalculator.isPrime(1297633));
    }

    @Test
    void isNotPrimeSequentialBigNumber() {
        primeNumberCalculator = new PrimeNumberCalculator();

        assertFalse(primeNumberCalculator.isPrime(1298777));
    }

    @Test
    void isPrimeConcurrentBigNumber() {
        primeNumberCalculator = new PrimeNumberCalculator(4);

        assertTrue(primeNumberCalculator.isPrime(1297633));
    }

    @Test
    void isNotPrimeConcurrentBigNumber() {
        primeNumberCalculator = new PrimeNumberCalculator(4);

        assertFalse(primeNumberCalculator.isPrime(1298777));
    }
}