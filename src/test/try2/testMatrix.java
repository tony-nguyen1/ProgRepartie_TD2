package test.try2;

import main.try2.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testMatrix {
    Matrix matrix1, matrix2, expectedResult, result;

    @Test
    public void test_prdtMatricielle_squarred_matrix_2x2() {
        matrix1 = Matrix.random(2, 2);
        matrix2 = Matrix.random(2, 2);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_squarred_matrix_4x4() {
        matrix1 = Matrix.random(4, 4);
        matrix2 = Matrix.random(4, 4);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_p_bigger_and_even() {
        matrix1 = Matrix.random(4, 4);
        matrix2 = Matrix.random(4, 6);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_p_bigger_and_odd() {
        matrix1 = Matrix.random(4, 4);
        matrix2 = Matrix.random(4, 7);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_p_smaller_and_even() {
        matrix1 = Matrix.random(4, 4);
        matrix2 = Matrix.random(4, 2);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_p_smaller_and_odd() {
        matrix1 = Matrix.random(4, 4);
        matrix2 = Matrix.random(4, 3);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_m_bigger_and_even() {
        matrix1 = Matrix.random(6, 4);
        matrix2 = Matrix.random(4, 4);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_m_bigger_and_odd() {
        matrix1 = Matrix.random(5, 4);
        matrix2 = Matrix.random(4, 4);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        matrix1.show();
        System.out.println();
        matrix2.show();
        System.out.println();
        result.show();
        System.out.println();
        expectedResult.show();

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_m_smaller_and_even() {
        matrix1 = Matrix.random(2, 4);
        matrix2 = Matrix.random(4, 4);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test_prdtMatricielle_m_smaller_and_odd() {
        matrix1 = Matrix.random(3, 4);
        matrix2 = Matrix.random(4, 4);

        result = matrix1.prdtMatricielle(matrix2);
        expectedResult = matrix1.times(matrix2);

        matrix1.show();
        System.out.println();
        matrix2.show();
        System.out.println();
        result.show();
        System.out.println();
        expectedResult.show();

        assertEquals(expectedResult, result);
    }
}
