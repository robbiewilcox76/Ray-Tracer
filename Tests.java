import java.util.Scanner;

public class Tests {
    public static void main(String[] args) {
        double[] tuple1 = new double[3];
        double[] tuple2 = new double[3];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the first color as 'r, g, b'.");
        for(int i=0; i<3; i++) {
            tuple1[i] = scanner.nextDouble();
        }
        System.out.println("Enter the coordinates of the second color as 'r, g, b'.");
        for(int i=0; i<3; i++) {
            tuple2[i] = scanner.nextDouble();
        }
        ColorT uno = new ColorT(tuple1[0], tuple1[1], tuple1[2]);
        ColorT dos = new ColorT(tuple2[0], tuple2[1], tuple2[2]);
        //double scalar = scanner.nextDouble();
        //System.out.println("the sum of color1 and color2 is : " + addColors(uno, dos));
        //System.out.println("color1 - color2 = " + subColors(uno, dos));
        System.out.println("The new color is :" + blendColors(uno, dos));
    }
    
    //adds a vector to a point    
    public static Tuple addVtoP(Tuple Point, Tuple Vector) {
        return new Tuple(Vector.getX()+Point.getX(),   
        Vector.getY()+Point.getY(), Vector.getZ()+Point.getZ(), 1);
    }

    //subtracts a vector from a point
    public static Tuple subVfromP(Tuple Point, Tuple Vector) {
        return new Tuple(Point.getX()-Vector.getX(),   
        Point.getY()-Vector.getY(), Point.getZ()-Vector.getZ(), 1);
    }

    //returns a vector from Point1 to Point2
    public static Tuple subPts(Tuple Point1, Tuple Point2) {
        return new Tuple(Point1.getX()-Point2.getX(),   
        Point1.getY()-Point2.getY(), Point1.getZ()-Point2.getZ(), 0);
    }

    //subtracts two vectors
    public static Tuple subVectors(Tuple Vector1, Tuple Vector2) {
        return new Tuple(Vector1.getX()-Vector2.getX(),
        Vector1.getY()-Vector2.getY(), Vector1.getZ()-Vector2.getZ(), 0);
    }

    //negates a vector
    public static Tuple negate(Tuple vector) {
        if(vector.getW() != 0) {throw new IllegalArgumentException();}
        return new Tuple(-vector.getX(), -vector.getY(), -vector.getZ(), vector.getW());
    }

    //scalar multiplication
    public static Tuple scalarMultiplication(Tuple vector, double scalar) {
        return new Tuple(scalar * vector.getX(), scalar * vector.getY(), scalar * vector.getZ(), vector.getW());
    }

    //I am fully aware that scalar division doesn't really make all that much sense but the
    //book I'm following had me implement these tests
    public static Tuple division(Tuple vector, double scalar) {
        return new Tuple(vector.getX()/scalar, vector.getY()/scalar, vector.getZ()/scalar, vector.getW());
    }

    //normalizes a vector to a unit vector
    public static Tuple normalizeV(Tuple vector) {
        if (vector.getW()!=0) {throw new IllegalArgumentException();}
        double length = vector.getV().getMagnitude();
        return new Tuple(vector.getX()/length, vector.getY()/length, vector.getZ()/length, vector.getW()/length);
    }

    //dot product of two vectors in R3
    public static double dotProduct(Tuple vector1, Tuple vector2) {
        if (vector1.getW()!=0 && vector2.getW()!=0) {throw new IllegalArgumentException();}
        return (vector1.getX()*vector2.getX()) + (vector1.getY()*vector2.getY()) + (vector1.getZ()*vector2.getZ());
    }

    //cross procduct of two vectors in R3
    public static Tuple crossProduct(Tuple vector1, Tuple vector2) {
        if (vector1.getW()!=0 && vector2.getW()!=0) {throw new IllegalArgumentException();}
        return new Tuple((vector1.getY()*vector2.getZ())-(vector1.getZ()*vector2.getY()),
        ((vector1.getZ()*vector2.getX())-(vector1.getX()*vector2.getZ())), 
        ((vector1.getX()*vector2.getY())-(vector1.getY()*vector2.getX())), 0);
    }

    public static ColorT addColors(ColorT color1, ColorT color2) {
        return new ColorT(color1.getRed()+color2.getRed(), color1.getGreen()+color2.getGreen(), color1.getBlue()+color2.getBlue());
    }

    public static ColorT subColors(ColorT color1, ColorT color2) {
        return new ColorT(color1.getRed()-color2.getRed(), color1.getGreen()-color2.getGreen(), color1.getBlue()-color2.getBlue());
    }

    public static ColorT colorScalarMultiplication(ColorT color, double scalar) {
        return new ColorT(color.getRed()*scalar, color.getGreen()*scalar, color.getBlue()*scalar);
    }

    public static ColorT blendColors(ColorT color1, ColorT color2) {
        return new ColorT(color1.getRed()*color2.getRed(), color1.getGreen()*color2.getGreen(), color1.getBlue()*color2.getBlue());
    }
}
