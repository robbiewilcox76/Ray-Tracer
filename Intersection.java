import java.util.LinkedList;

public class Intersection {
    private double t;
    private Object object;

    public Intersection(double t, Object object) {
        this.t = t;
        this.object = object;
    }

    public Intersection() {
    }

    public double gett() {
        return this.t;
    }

    public Object getObject() {
        return this.object;
    }

    public String toString() {
        return("Intersection with an object at t = " + this.t);
    }

    public boolean equals(Intersection other) {
        if(this.t == other.gett() && this.object == other.getObject()) return true;
        return false;
    }
}
