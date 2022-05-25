public class Tuple {
    private double x;
    private double y;
    private double z;
    private double w;
    private Vector vector;
    private Point point;

    public Tuple(double w) {
        if(w!=0 && w!=1) {throw new IllegalArgumentException();}
        this.x = 0;
        this.y = 0; 
        this.z = 0;
        this.w = w;
        if(w == 0) {
            this.vector = new Vector(0, 0, 0);
            this.point = null;
        }
        else {
            this.point = new Point(0, 0, 0);
            this.vector = null;
        }
    }
    
    public Tuple(double x, double y, double z, double w) {
        if(w!=0 && w!=1) {throw new IllegalArgumentException();}
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        if(w == 0) {
            this.vector = new Vector(this.x, this.y, this.z);
            this.point = null;
        }
        else {
            this.point = new Point(this.x, this.y, this.z);
            this.vector = null;
        }
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public void setW(double w) {
        this.w = w;
    }

    public double getW() {
        return w;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public Vector getV() {
        return this.vector;
    }

    public Point getP() {
        return this.point;
    }



    public String toString() {
        if(w==0) return ("This is a vector with coordinates (" + x + ", " + y + ", " + z + "), w = " + w);
        else return ("This is a point with coordinates (" + x + ", " + y + ", " + z + "), w = " + w);
    }

    /* Checks if two tuples are equal with an error bound of epsilon, points 
    and vectors with the same coordinates not equal */
    public boolean equals(Tuple Tuple2) {
        if(this.w != Tuple2.w) return false;
        double epsilon = 0.0001;
        if (Math.abs(this.x-Tuple2.x) < epsilon && Math.abs(this.y-Tuple2.y) < epsilon && Math.abs(this.z-Tuple2.z) < epsilon) return true;
        return false;
    }

    public Tuple plus(Tuple Vector) {
        if(Vector.getW() != 0) {throw new IllegalArgumentException();}
        return new Tuple(Vector.getX()+this.x,   
        Vector.getY()+this.y, Vector.getZ()+this.z, 1);
    }

        public Tuple scalarMultiplication(double scalar) {
        double x = scalar*this.x;
        double y = scalar*this.y;
        double z = scalar*this.z;
        if(x==-0.0) {x=0;}
        if(y==-0.0) {y=0;}
        if(z==-0.0) {z=0;}
        return new Tuple(x, y, z, this.w);
    }

    public Tuple subPts(Tuple Point2) {
        return new Tuple(this.x-Point2.getX(),   
        this.y-Point2.getY(), this.z-Point2.getZ(), 0);
    }

    public double dotProduct(Tuple vector2) {
        if (this.w!=0 && vector2.getW()!=0) {throw new IllegalArgumentException();}
        return (this.x*vector2.getX()) + (this.y*vector2.getY()) + (this.z*vector2.getZ());
    }

    public double getMagnitude() {
        if(this.w != 0) {throw new IllegalArgumentException();}
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Tuple normalize() {
        return new Tuple(this.x/this.getMagnitude(), this.y/this.getMagnitude(), this.z/this.getMagnitude(), this.w);
    }

    public Tuple subVectors(Tuple Vector2) {
        return new Tuple(this.x-Vector2.getX(),
        this.y-Vector2.getY(), this.z-Vector2.getZ(), 0);
    }

    public Tuple reflectAboutNormal(Tuple normal) {
        if(this.w!=0 && normal.getW()!=0) {throw new IllegalArgumentException();}
        return this.subVectors(normal.scalarMultiplication(2).scalarMultiplication(this.dotProduct(normal)));
    }

    public Tuple crossProduct(Tuple vector2) {
        if (this.w!=0 && vector2.getW()!=0) {throw new IllegalArgumentException();}
        return new Tuple((this.y*vector2.getZ())-(this.z*vector2.getY()),
        ((this.z*vector2.getX())-(this.x*vector2.getZ())), 
        ((this.x*vector2.getY())-(this.y*vector2.getX())), 0);
    }
}
