import java.util.LinkedList;
import java.util.Collections;

public class World {
    private LinkedList<Object> objects;
    private Light light_source;

    public World() {
        this.light_source = new Light(new Tuple(-10, 10, -10, 1), new ColorM(1, 1, 1));
        this.objects = new LinkedList();
        Sphere ss1 = new Sphere();
        ss1.getMaterial().setColor(new ColorM(0.8, 1.0, 0.6));
        ss1.getMaterial().setDiffuse(0.7);
        ss1.getMaterial().setSpecular(0.2);
        Sphere ss2 = new Sphere();
        Transformation t = new Transformation();
        Matrix m = t.scalingMatrix(0.5, 0.5, 0.5);
        ss2.setTransformation(m);
        Object s1 = new Object(ss1);
        Object s2 = new Object(ss2);
        this.addObject(s1, s2);
    }

    public World(Object... objectA) {
        objects = new LinkedList();
        for(int i=0; i<objectA.length; i++) {
            this.objects.add(i, objectA[i]);
        }
        this.light_source = new Light(new Tuple(-10, 10, -10, 1), new ColorM(1, 1, 1));
    }

    public World(Light light_source, Object... objectA) {
        this.light_source = light_source;
        objects = new LinkedList();
        for(int i=0; i<objectA.length; i++) {
            this.objects.push(objectA[i]);
        }
    }

    public void setLight(Light newL) {
        this.light_source = newL;
    }

    public void addObject(Object... objectsToAdd) {
        for(int i=0; i<objectsToAdd.length; i++) {
            this.objects.add(i, objectsToAdd[i]);
        }
    }

    public void emptyWorld() {
        this.objects.removeFirst();
        this.objects.removeLast();
    }

    public LinkedList<Object> getObjects() {
        return this.objects;
    }

    public Light getLight() {
        return this.light_source;
    }

    public String toString() {
        String s = new String("\nSummary of this world:\n\nThe light source: " + this.light_source + "\n\nObjects in the world: ");
        for(int i=0; i<this.objects.size(); i++) {
            s += ("\n" + objects.get(i));
        }
        s += ("\n");
        return s;
    }

    public Intersections Intersects_world(Ray r) {
        Intersections finalList = new Intersections();
        Intersection uno;
        for(int i=0; i<this.objects.size(); i++) {
            for(int j=0; j<this.objects.get(i).intersect(r).size(); j++) {
            uno = new Intersection(this.objects.get(i).intersect(r).get(j), this.objects.get(i));
            finalList.addOne(uno);}
        }
        finalList.sortByT();
        return finalList;
    }

    public ColorM shade_hit(Comps comps, int remaining) {
        double reflectance;
        Object t = comps.getObject();
        ColorM surface = t.lighting(this.light_source, comps.getOverPt(), comps.getEyeVector(), comps.getNormal(), this.is_shadowed(comps.getOverPt()));
        ColorM reflected = this.reflected_color(comps, remaining);
        ColorM refracted = this.refracted_color(comps, remaining);

        if(comps.getObject().getMaterial().getReflective() > 0 && comps.getObject().getMaterial().getTransparency() > 0) {
            reflectance = comps.schlick();
            return surface.addColors(reflected.colorScalarMultiplication(reflectance)).addColors(refracted.colorScalarMultiplication(1-reflectance));
        }
        return surface.addColors(reflected).addColors(refracted);
    }

    public ColorM color_at(Ray r, int remaining) {
        Intersections x = this.Intersects_world(r);
        Intersection hit = x.hit();
        if(hit.gett() == -1) {
            return new ColorM(0, 0, 0);
        }
        Comps comps = new Comps(hit, r);
        return this.shade_hit(comps, remaining);  
    }

    public boolean is_shadowed(Tuple point) {
        if(point.getW() != 1) {throw new IllegalArgumentException();}
        Tuple v = this.light_source.getPosition().subPts(point);
        Double distance = v.getMagnitude();
        Tuple direction = v.normalize();
        Ray r = new Ray(point, direction);
        Intersections ints = this.Intersects_world(r);
        Intersection hit = ints.hit();
        if(hit.gett() != -1 && hit.gett() < distance) {return true;}
        return false;
    }

    public ColorM reflected_color(Comps comps, int remaining) {
        if(remaining <= 0) {return new ColorM(0, 0, 0);}
        if(comps.getIntersection().getObject().getMaterial().getReflective() == 0) return new ColorM(0, 0, 0);
        Ray reflect_ray = new Ray(comps.getOverPt(), comps.getReflectv());
        ColorM color = this.color_at(reflect_ray, remaining-1);
        return color.colorScalarMultiplication(comps.getIntersection().getObject().getMaterial().getReflective());
    }

    public ColorM refracted_color(Comps comps, int remaining) {
        if(comps.getObject().getMaterial().getTransparency() == 0 || remaining == 0) {return new ColorM(0, 0, 0);}
        double ratio = comps.getn1() / comps.getn2();
        double cos_i = comps.getEyeVector().dotProduct(comps.getNormal());
        double sin2_t = Math.pow(ratio, 2)*(1-Math.pow(cos_i, 2));
        if(sin2_t > 1) {return new ColorM(0, 0, 0);}
        double cos_t = Math.sqrt(1.0-sin2_t);
        Tuple direction = comps.getNormal().scalarMultiplication(ratio*cos_i-cos_t).subVectors(comps.getEyeVector().scalarMultiplication(ratio));
        Ray refract_ray = new Ray(comps.under_pt(), direction);
        ColorM color = this.color_at(refract_ray, remaining-1).colorScalarMultiplication(comps.getObject().getMaterial().getTransparency());
        return color;
    }
}
