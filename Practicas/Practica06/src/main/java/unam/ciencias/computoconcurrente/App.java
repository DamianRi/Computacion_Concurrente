package unam.ciencias.computoconcurrente;

import java.util.Arrays;
import java.util.Random;

public class App {

    public static void main(String[] a) {
        

        int[] array = new int[10];
        for(int i = 0; i<=array.length-1;i++){
            array[i]= new Random().nextInt(100);
            
        }

        System.out.println(Arrays.toString(array));
        Sorting sort = new Sorting();
        sort.parallelMergeSort(array);

        
        System.out.println("Ordenado");

    
       
            System.out.println(Arrays.toString(array));

    
        
    }

}
