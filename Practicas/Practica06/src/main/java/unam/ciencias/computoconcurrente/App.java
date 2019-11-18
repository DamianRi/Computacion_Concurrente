package unam.ciencias.computoconcurrente;

import java.util.Arrays;
import java.util.Random;

public class App {

    public static void main(String[] a) {
        
  
        int[] array = new int[10];
        for(int i = 0; i<=array.length-1;i++){
            array[i]= new Random().nextInt(10000);
        }
        int [] array2 = new int [10];
        for(int i = 0; i<=array.length-1;i++){
            array2[i]= array[i];
        }


        Sorting paralelo = new Sorting();
        paralelo.mergeSort(array);

        System.out.println(Arrays.toString(array2));
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array2));

        
    }

}
