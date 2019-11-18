package unam.ciencias.computoconcurrente;

import java.util.concurrent.CompletableFuture;

public class Polynomial {
    int[] coefficients; // possibly shared by several polynomials int first; // index of my constant coefficient
    int degree; // number of coefficients that are mine
    int first; // index of my constant coefficient

    public Polynomial(int d) {
        coefficients = new int[d];
        degree = d;
        first = 0;
    }

   public Polynomial(int[] myCoefficients, int myFirst, int myDegree) {
        coefficients = myCoefficients;
        first = myFirst;
        degree = myDegree;
    }

    public int get(int index) {
        return coefficients[first + index];
    }

    public void set(int index, int value) {
        coefficients[first + index] = value;
    }

    public int getDegree() {
        return degree;
    }

    public void update(int index, int value) {
        coefficients[first + index] += value;
    }

    public Polynomial[] split() {
        Polynomial[] result = new Polynomial[2];
        int newDegree = degree / 2;
        result[0] = new Polynomial(coefficients, first, newDegree);
        result[1] = new Polynomial(coefficients, first + newDegree, newDegree);
        return result;
    }

    public void add(Polynomial p, Polynomial result){

        if (p.getDegree() == 1) {
            result.set(p.first,(this.get(0) + p.get(0)));
            return;
        }
        if (this.getDegree() == 1) {
            result.set(p.first,(this.get(0) + p.get(0)));
            return;
        }
        Polynomial[] polinomios_1 = this.split(); 
        Polynomial[] polinomios_2 = p.split();
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            polinomios_1[0].add(polinomios_2[0],result);
        }); 
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            polinomios_1[1].add(polinomios_2[1],result); 
        });

        try {
           c1.get(); c2.get(); 
        } catch (Exception e) {
            System.out.println("Error");
        } 
    }


    public void multiply(Polynomial p, Polynomial result){

        if (this.getDegree() == 1) { 
            result.update(p.first + this.first,(this.get(0) * p.get(0)));
            return;
        }
        Polynomial[] polinomios_1 = this.split(); 
        Polynomial[] polinomios_2 = p.split();
     
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {

            polinomios_1[0].multiply(polinomios_2[0],result); 
        });
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            polinomios_1[0].multiply(polinomios_2[1],result);
        });
        CompletableFuture<Void> c3 = CompletableFuture.runAsync(() -> {
            polinomios_1[1].multiply(polinomios_2[0],result); 
        });
        CompletableFuture<Void> c4 = CompletableFuture.runAsync(() -> {
            polinomios_1[1].multiply(polinomios_2[1],result);
        });

        try {
           c1.get(); c2.get(); c3.get(); c4.get(); 
        } catch (Exception e) {
            System.out.println("Error");
        } 
    }

    public String toString(){
        String s = "";
        for (int i = first ;i<(first + degree) ;i++ ) {
            s += coefficients[i] + "x^" + i + " ";
            if ((i+1) < this.coefficients.length) {
                s += "+ ";
            }
        }
        return s;
    }



}
