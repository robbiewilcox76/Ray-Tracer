import java.util.LinkedList;

public class Comps {
    private Intersection intersection;  
    private Object object;
    private Tuple point;
    private Tuple eyeV;
    private Tuple normalV;
    private double t;
    private boolean inside;
    private Tuple over_point;
    private Tuple reflectv;
    private double n1, n2;
    private Tuple under_point;
    private final double epsilon = 0.00001;

    public Comps(Intersection intersection, Ray r) {
        this.intersection = intersection;
        this.object = intersection.getObject();
        this.t = intersection.gett();
        this.point = r.position(this.t);
        this.eyeV = r.getDirection().scalarMultiplication(-1);
        this.normalV = this.object.normal_at(point);
        if(this.normalV.dotProduct(this.eyeV)<0) {
            this.inside = true;
            this.normalV = this.normalV.scalarMultiplication(-1);
        }
        else this.inside = false;
        this.over_point = this.point.plus(this.normalV.scalarMultiplication(epsilon));
        this.reflectv = r.getDirection().reflectAboutNormal(normalV);
    }

    public Comps(Intersection intersection, Ray r, Intersections intersections) {
        this.intersection = intersection;
        this.object = intersection.getObject();
        this.t = intersection.gett();
        this.point = r.position(this.t);
        this.eyeV = r.getDirection().scalarMultiplication(-1);
        this.normalV = this.object.normal_at(point);
        if(this.normalV.dotProduct(this.eyeV)<0) {
            this.inside = true;
            this.normalV = this.normalV.scalarMultiplication(-1);
        }
        else this.inside = false;
        this.over_point = this.point.plus(this.normalV.scalarMultiplication(epsilon));
        this.reflectv = r.getDirection().reflectAboutNormal(normalV);

        LinkedList<Object> containers = new LinkedList<Object>();
        for(int i=0; i<intersections.getIntersections().size(); i++) {
            if(intersections.getIntersections().get(i).equals(intersection)) {
                if(containers.isEmpty()) {
                    this.n1 = 1.0;
                }
                else {
                    this.n1 = containers.getLast().getMaterial().getRefract();
                    }
                }
            if(containers.contains(intersections.getIntersections().get(i).getObject())) {
                containers.remove(intersections.getIntersections().get(i).getObject());
                }
            else {
                containers.addLast(intersections.getIntersections().get(i).getObject());
            }
            if(intersections.getIntersections().get(i).equals(intersection)) {
                if(containers.isEmpty()) {
                    this.n2 = 1.0;
                }
                else {
                    this.n2 = containers.getLast().getMaterial().getRefract();
                }
            }
        }
        this.under_point = point.plus(this.normalV.scalarMultiplication(-epsilon));
    }

    public double schlick() {
        double n, sin2_t, cos_t;
        double cos = this.eyeV.dotProduct(this.normalV);
        if(this.n1 > this.n2) {
            n = this.n1/this.n2;
            sin2_t = Math.pow(n, 2) * (1.0 - Math.pow(cos, 2));
            if(sin2_t > 1.0) return 1.0;
            cos_t = Math.sqrt(1.0 - sin2_t);
            cos = cos_t;
        }
        double r0 = Math.pow(((this.n1 - this.n2) / (this.n1 + this.n2)), 2);
        return r0 + (1-r0) * Math.pow((1-cos), 5);
    }

    public Intersection getIntersection() {
        return this.intersection;
    }
    public Object getObject() {
        return this.object;
    }

    public Tuple getPoint() {
        return this.point;
    }

    public Tuple getEyeVector() {
        return this.eyeV;
    }

    public Tuple getNormal() {
        return this.normalV;
    }

    public boolean isInside() {
        return this.inside;
    }

    public Tuple getOverPt() {
        return this.over_point;
    }

    public Tuple getReflectv() {
        return this.reflectv;
    }

    public double epsilon() {
        return this.epsilon;
    }

    public double getn1() {
        return this.n1;
    }

    public double getn2() {
        return this.n2;
    }

    public Tuple under_pt() {
        return this.under_point;
    }
}
