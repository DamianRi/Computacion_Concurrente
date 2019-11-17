package unam.ciencias.computoconcurrente;

public class App {

    public static void main(String[] a) {

        int degree = 4;
        int arr1 [] = {0,2,0,3};
        int arr2 [] = {5,0,0,4};
        
        Polynomial p1     = new Polynomial(arr1, 0, degree);
        Polynomial p2     = new Polynomial(arr2, 0, degree);
        Polynomial result = new Polynomial(degree + degree - 1);
        Polynomial result2 = new Polynomial(degree );
        p1.multiplica(p2,result);
        p2.suma(p1,result2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("Multiplicación: ");
       	System.out.println(result);
       	System.out.println("Suma: ");
        System.out.println(result2);
           
    }
}
