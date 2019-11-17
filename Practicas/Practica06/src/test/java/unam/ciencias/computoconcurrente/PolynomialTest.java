package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class PolynomialTest {

    @Test
    void add() { 

        int degree = 4;
        int arr1 [] = {0,2,0,3};
        int arr2 [] = {5,0,0,4};

        int arr3 [] = {5,2,0,7};

        Polynomial esperado = new Polynomial(arr3,0,degree);

        Polynomial p1     = new Polynomial(arr1, 0, degree);
        Polynomial p2     = new Polynomial(arr2, 0, degree);
        Polynomial result2 = new Polynomial(degree);
    
        p2.add(p1,result2);

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
        Polynomial p1     = new Polynomial(arr1, 0, degree);
        Polynomial p2     = new Polynomial(arr2, 0, degree);
        Polynomial result = new Polynomial(degree + degree - 1);

        p1.multiply(p2,result);

        Assertions.assertEquals(result.toString(),esperado.toString());

    }
}