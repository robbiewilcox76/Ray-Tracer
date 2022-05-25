import java.util.LinkedList;
import java.util.concurrent.RunnableScheduledFuture;

public class Object {
    private Sphere sphere;
    private boolean isSphere, isPlane;
    private Plane plane;
    private Material material;
    private Matrix transformation;

    public Object(Sphere sphere) {
        this.sphere = sphere;
        this.material = sphere.getMaterial();
        this.transformation = this.sphere.getTransformation();
        this.isSphere = true;
    }

    public Object(Plane plane) {
        this.transformation = plane.getTransformation();
        this.plane = plane;
        this.material = plane.getMaterial();
        this.isPlane = true;
    }

    public Object() {
        this.material = new Material();
        double[][] defaulte = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.transformation = new Matrix(4, defaulte);
    }

    public void setTransformation(Matrix transform) {
        this.transformation = transform;
        if(this.sphere!=null) {this.getSphere().setTransformation(transform);}
        else this.getPlane().setTransformation(transform);
    }

    public void setMaterial(Material material) {
        this.material = material;
        if(this.sphere!=null) {this.getSphere().setMaterial(material);}
    }

    public Sphere getSphere() {
        if(!this.isSphere) {throw new IllegalArgumentException();}
        return this.sphere;
    }

    public Plane getPlane() {
        if(!this.isPlane) {throw new IllegalArgumentException();}
        return this.plane;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Matrix getTransformation() {
        return this.transformation;
    }

    public LinkedList<Double> intersect(Ray r) {
        //System.out.println(r);
        Ray local_ray = r.transform(this.transformation.inverse());
        if(this.isSphere) {
            return this.getSphere().local_intersect(local_ray);
        }
        return this.getPlane().local_intersect(local_ray);
    }

    public Tuple normal_at(Tuple point) {
        Tuple local_normal;
        if(point.getW()!=1) {throw new IllegalArgumentException();}
        Tuple local_point = (this.transformation.inverse()).matrixVectorProduct(point);
        if(this.isSphere){
        local_normal = this.getSphere().local_normal_at(local_point);}
        else{local_normal = this.getPlane().local_normal_at();}
        Tuple world_normal = ((this.transformation.inverse()).transpose()).matrixVectorProduct(local_normal);
        world_normal.setW(0);
        return world_normal.normalize();
    }

    public ColorM lighting(Light light, Tuple point, Tuple eVector, Tuple nVector, boolean in_shadow) {
        if(point.getW() != 1 || eVector.getW() != 0 || nVector.getW() != 0) {throw new IllegalArgumentException();}
        ColorM ambient, diffuse, specular, effective_color, Color;
        Tuple reflectv, lightv;
        double reflect_dot_eye, factor, Light_dot_normal;
        Material material = this.material;
        if(material.hasPattern()){Color = material.getPattern().pattern_at_object(this, point);}
        else {Color = material.getColor();}
        effective_color = Color.blendColors(light.getIntensity());
        lightv = light.getPosition().subPts(point).normalize();
        ambient = effective_color.colorScalarMultiplication(material.getAmbient());
        Light_dot_normal = lightv.dotProduct(nVector);
        //System.out.println(Light_dot_normal);
        if(Light_dot_normal < 0) {
            //System.out.println("triggered");
            diffuse = new ColorM(0, 0, 0);
            specular = new ColorM(0, 0, 0);
        } else {
            diffuse = effective_color.colorScalarMultiplication(material.getDiffuse()).colorScalarMultiplication(Light_dot_normal);
            reflectv = lightv.scalarMultiplication(-1).reflectAboutNormal(nVector);
            reflect_dot_eye = reflectv.dotProduct(eVector);
            //System.out.println("r " + reflectv + " " + reflect_dot_eye);
            if(reflect_dot_eye <= 0) {specular = new ColorM(0, 0, 0);}
                //System.out.println("hi");}
            else {
                //System.out.println("hello");
                factor = Math.pow(reflect_dot_eye, material.getShininess());
                specular = light.getIntensity().colorScalarMultiplication(material.getSpecular()*factor);
            }
        }
        if(in_shadow) {specular = new ColorM(0, 0, 0); diffuse = new ColorM(0, 0, 0);}
        //System.out.println(specular + "\n" + diffuse + "\n" + ambient);
        return ambient.addColors(diffuse.addColors(specular));
    }
}
