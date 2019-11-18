package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class PolynomialTest {

    @Test
    void add() { 

        int degree = 4;
        int arr1 [] = {-1,2,3,4};
        int arr2 [] = {6,-3,4,5};

        int arr3 [] = {5,-1,7,9};

        Polynomial esperado = new Polynomial(arr3,0,degree);

        Polynomial polinomio1  = new Polynomial(arr1, 0, degree);
        Polynomial polinomio2  = new Polynomial(arr2, 0, degree);
        Polynomial result2 = new Polynomial(degree);
    
        polinomio2.add(polinomio1,result2);

        Assertions.assertEquals(result2.toString(),esperado.toString());

    }


    @Test
    void multiply() {
        int degree = 4;
        int degree2 = 7;

        int arr1 [] = {0,2,0,3};
        int arr2 [] = {5,0,0,4};
        int arr3 [] = {0,10,0,15,8,0,12};
        
        Polynomial esperado = new Polynomial(arr3 , 0 ,degree2);
        Polynomial polinomio1     = new Polynomial(arr1, 0, degree);
        Polynomial polinomio2     = new Polynomial(arr2, 0, degree);
        Polynomial result = new Polynomial(degree + degree - 1);

        polinomio1.multiply(polinomio2,result);

        Assertions.assertEquals(result.toString(),esperado.toString());

    }
}