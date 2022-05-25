import java.util.Scanner;
import java.io.File;

public class MatrixTests {
    public static void main(String[] args) {
        Tuple p = new Tuple(1, 0, 1, 1);
        Transformation t = new Transformation();
        Matrix A = t.rotationAboutX(90);
        Matrix B = t.scalingMatrix(5, 5, 5);
        Matrix C = t.TranslationMatrix(10, 5, 7);

        Matrix D = C.multiply(B.multiply(A));
        System.out.println(D.matrixVectorProduct(p));
    }
}
