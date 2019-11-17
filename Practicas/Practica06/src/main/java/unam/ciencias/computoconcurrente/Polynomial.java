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

    // Suma recurdiavamente dos polinomios.
    public void suma (Polynomial p, Polynomial result){
        // Caso base.
        if (p.getDegree() == 1) {
            result.set(p.first,(this.get(0) + p.get(0)));
            return;
        }
        // Llamada recursiva.
        // Dividimos los dos polinomios.
        Polynomial[] polinomios_1 = this.split(); 
        Polynomial[] polinomios_2 = p.split();
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            // Sumamos el lado izquierdo.
            polinomios_1[0].suma(polinomios_2[0],result);
        }); 
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            // Sumamos el lado derecho.
            polinomios_1[1].suma(polinomios_2[1],result); 
        });

        try {
           c1.get(); c2.get(); 
        } catch (Exception e) {
            System.out.println("Falló :(");
        } 
    }

    public void multiplica (Polynomial p, Polynomial result){
        // Caso base.
        if (this.getDegree() == 1) { // No sé si tendrían que ser los dos, hmmm.
            // System.out.println(p.first + " " + this.first);
            result.update(p.first + this.first,(this.get(0) * p.get(0)));
            return;
        }
     
        Polynomial[] polinomios_1 = this.split(); 
        Polynomial[] polinomios_2 = p.split();
     
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            //P0(x) * Q0(x)
            polinomios_1[0].multiplica(polinomios_2[0],result); 
        });
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> {
            // P0(x) * Q1(x) 
            polinomios_1[0].multiplica(polinomios_2[1],result);
        });
        CompletableFuture<Void> c3 = CompletableFuture.runAsync(() -> {
            // P1(x) * Q0(x)
            polinomios_1[1].multiplica(polinomios_2[0],result); 
        });
        CompletableFuture<Void> c4 = CompletableFuture.runAsync(() -> {
            // P1(x) * Q1(x)
            polinomios_1[1].multiplica(polinomios_2[1],result);
        });

        try {
           c1.get(); c2.get(); c3.get(); c4.get(); 
        } catch (Exception e) {
            System.out.println("Falló :(");
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
