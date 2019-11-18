package unam.ciencias.computoconcurrente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    @Test
    void mergeSort() {
    
        int[] array = new int[10000];
        for(int i = 0; i<=array.length-1;i++){
            array[i]= new Random().nextInt(10000);
        }
        int [] array2 = array;

        Sorting paralelo = new Sorting();
        paralelo.mergeSort(array);
        Arrays.sort(array2);

        Assertions.assertEquals(Arrays.toString(array), Arrays.toString(array2));


    }
}