import java.util.LinkedList;

public class Sphere {
    private Tuple origin;
    private double radius;
    private Material material;
    private Matrix transformation;

    public Sphere() {
        this.origin = new Tuple(0, 0, 0, 1);
        this.radius = 1;
        double[][] defaulte = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.transformation = new Matrix(4, defaulte);
        this.material = new Material();
    }

    public Sphere(Material material) {
        this.origin = new Tuple(0, 0, 0, 1);
        this.radius = 1;
        double[][] defaulte = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.transformation = new Matrix(4, defaulte);
        this.material = material;
    }

    public LinkedList<Double> local_intersect(Ray ray) {
        double oD = ray.getDirection().getMagnitude();
        ray.normalizeD();
        LinkedList<Double> pts = new LinkedList();
        double a = ray.getDirection().dotProduct(ray.getDirection());
        Tuple v = (ray.getOrigin().subPts(this.origin));
        double b = 2*ray.getDirection().dotProduct(v);
        double c = v.dotProduct(v) - (1);
        double d = b*b - 4 * a * c;
        if(d>=0) {
            pts.push(((-b-Math.sqrt(d)) / 2*a)*(ray.getDirection().getMagnitude()/oD));
            pts.push(((-b+Math.sqrt(d)) / 2*a)*(ray.getDirection().getMagnitude()/oD));
            return pts;
        }
        pts.push(-1.0);
        pts.push(-1.0);
        return pts;
    }      

    public String toString() {
        return ("Sphere with origin at (" + this.origin.getX() + ", " + this.origin.getY() + ", " + this.origin.getZ() + "), and a radius of " + this.radius + ".");
    }

    public void setTransformation(Matrix transform) {
        this.transformation = transform;
    }

    public Matrix getTransformation() {
        return this.transformation;
    }

    public Tuple local_normal_at(Tuple point) {
        //***** old code i didnt want to lose
        //Tuple object_point = this.transformation.inverse().matrixVectorProduct(point);
        //Tuple object_normal = object_point.subPts(this.origin);
        //object_normal.setW(0);
        //Tuple world_normal = this.transformation.inverse().transpose().matrixVectorProduct(object_normal);
        //world_normal.setW(0);******/
        point.setW(0);
        return point;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    /*public ColorM lighting(Light light, Tuple point, Tuple eVector, Tuple nVector, boolean in_shadow) {
        ColorM diffuse, specular;
        Tuple reflectv;
        double reflect_dot_eye, factor;
        ColorM effective_color = this.material.getColor().blendColors(light.getIntensity());
        Tuple Lightv = light.getPosition().subPts(point).normalize();
        Lightv.setW(0);
        ColorM ambient = effective_color.colorScalarMultiplication(this.material.getAmbient());
        double light_dot_normal = Lightv.dotProduct(nVector);
        if(light_dot_normal < 0) {
            diffuse = new ColorM(0, 0, 0);
            specular = new ColorM(0, 0, 0);
        }
        else {
            diffuse = effective_color.colorScalarMultiplication(this.material.getDiffuse()).colorScalarMultiplication(light_dot_normal);
            reflectv = Lightv.scalarMultiplication(-1).reflectAboutNormal(nVector);
            reflectv.setW(0);
            reflect_dot_eye = reflectv.dotProduct(eVector);
            if(reflect_dot_eye<=0) {
                specular = new ColorM(0, 0, 0);
            }
            else{
                factor = Math.pow(reflect_dot_eye, material.getShininess());
                specular = light.getIntensity().colorScalarMultiplication(material.getSpecular()).colorScalarMultiplication(factor);
            }
        }
        return ambient.addColors(diffuse.addColors(specular));
    } */
}
