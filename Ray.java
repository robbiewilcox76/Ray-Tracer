public class Ray {
    private Tuple origin;
    private Tuple direction;

    public Ray(Tuple origin, Tuple direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray() {
        this.origin = new Tuple(0, 0, 0, 1);
        this.direction = new Tuple(0, 0, 0, 0);
    }

    public Tuple getOrigin() {
        return this.origin;
    }

    public Tuple getDirection() {
        return this.direction;
    }

    public String toString() {
        return("Ray with origin (" + this.origin.getX() + ", " + this.origin.getY() + ", " + this.origin.getZ() + "), and direction ("  
        + this.direction.getX() + ", " + this.direction.getY() + ", " + this.direction.getZ() + ").");
    }

    public Tuple position(double t) {
        return this.origin.plus(this.direction.scalarMultiplication(t));
    }

    public Ray transform(Matrix m) {
        return new Ray(m.matrixVectorProduct(origin), m.matrixVectorProduct(direction));
    }

    public void normalizeD() {
        double mag = Math.sqrt((this.direction.getX()*this.direction.getX())+ 
        (this.direction.getY()*this.direction.getY())+
        (this.direction.getZ()*this.direction.getZ()));
        this.direction = new Tuple(this.direction.getX()/mag, this.direction.getY()/mag, this.direction.getZ()/mag, 0);
        //System.out.println(this);
    }
}

