package unam.ciencias.computoconcurrente;

import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixUtilsTest {
    MatrixUtils matrixUtils;

    @Test
    void findMinimum() throws InterruptedException{
        matrixUtils = new MatrixUtils();
        int[][] matrix = {
                {4, 29, -6, 0},
                {15, 6, 0, 4},
                {25, 41, -10, 4},
                {0, 0, -1, 39},
        };

        assertEquals(-10, matrixUtils.findMinimum(matrix));
    }

    @Test
    void findMinimumConcurrent() throws InterruptedException{
        matrixUtils = new MatrixUtils(2);
        int[][] matrix = {
                {4, 29, -6, 0},
                {15, 6, 0, 4},
                {25, 41, -10, 4},
                {0, 0, -1, 39},
        };

        assertEquals(-10, matrixUtils.findMinimum(matrix));
    }

    @Test
    void findMinimumBigMatrix() throws InterruptedException{
        matrixUtils = new MatrixUtils();
        int rows = 5000, columns = 1000;
        int[][] matrix = new int[rows][columns];
        int minimum = fillMatrixAndReturnMinimumValue(matrix);

        assertEquals(minimum, matrixUtils.findMinimum(matrix));
    }

    @Test
    void findMinimumConcurrentBigMatrix() throws InterruptedException{
        matrixUtils = new MatrixUtils(4);
        int rows = 5000, columns = 1000;
        int[][] matrix = new int[rows][columns];
        int minimum = fillMatrixAndReturnMinimumValue(matrix);

        assertEquals(minimum, matrixUtils.findMinimum(matrix));
    }

    private int fillMatrixAndReturnMinimumValue(int[][] result) {
        int min = 2147483647;
        Random random =  new Random();
        for(int[] row : result) {
            for(int c = 0; c<row.length; c++) {
                row[c] = random.nextInt();
                min = row[c] < min ? row[c] : min;
            }
        }

        return min;
    }
}