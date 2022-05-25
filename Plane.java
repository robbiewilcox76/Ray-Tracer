import java.util.LinkedList;

public class Plane {
    private final Tuple normal = new Tuple(0, 1, 0, 0);
    public Matrix transformation;
    public Material material;

    public Plane() {
        double[][] defaulte = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.transformation = new Matrix(4, defaulte);
        this.material = new Material();
    }

    public Plane(Material material) {
        double[][] defaulte = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.transformation = new Matrix(4, defaulte);
        this.material = material;
    }

    public Tuple local_normal_at() {
        return this.normal;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setTransformation(Matrix transform) {
        this.transformation = transform;
    }

    public Matrix getTransformation() {
        return this.transformation;
    }

    public LinkedList<Double> local_intersect(Ray r) {
        LinkedList<Double> pts = new LinkedList();
        if (Math.abs(r.getDirection().getY()) < 0.00001) {
            pts.push(-1.0);
            pts.push(-1.0);
            return pts;
        }
        double t = (r.getOrigin().getY()*(-1))/(r.getDirection().getY());
        pts.push(t);
        return pts;
    }
}
