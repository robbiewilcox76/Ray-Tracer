public class Point {
    private double x;
    private double y;
    private double z;
    private final double w = 1;
    
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return ("This is a point with coordinates (" + x + ", " + y + ", " + z + ")");
    }

    public double getW() {
        return this.w;
    } 
}
