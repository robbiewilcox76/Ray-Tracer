public class Vector {
    private double x;
    private double y;
    private double z;
    private final double w = 0;
    
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return ("This is a vector with coordinates (" + x + ", " + y + ", " + z + ")");
    }

    public double getW() {
        return this.w;
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
}
