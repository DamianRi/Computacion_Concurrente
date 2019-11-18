package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    @Test
    void mergeSort() {
        int longitud = new Random().nextInt(1000000);
        int[] array = new int[longitud];
        for(int i = 0; i<=array.length-1;i++){
            array[i]= new Random().nextInt(10000);
        }
        
        int [] array2 = new int [longitud];
        for(int i = 0; i<=array.length-1;i++){
            array2[i]= array[i];
        }

        Sorting paralelo = new Sorting();
        paralelo.mergeSort(array);

        Arrays.sort(array2);

        Assertions.assertEquals(Arrays.toString(array), Arrays.toString(array2));


    }
}