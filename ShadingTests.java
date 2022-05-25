import java.awt.Color;

public class ShadingTests {
    public static void main(String[] args) {
        Sphere s = new Sphere();
        /*System.out.println(s.getMaterial().getAmbient() + " " + s.getMaterial().getDiffuse() + " " + 
        s.getMaterial().getSpecular() + " " + s.getMaterial().getShininess());*/
        Tuple eyeV = new Tuple(0, 0, -1, 0);
        Tuple normalV = new Tuple(0, 0, -1, 0);
        Light light = new Light(new Tuple(0, 0, 10, 1), new ColorM(1, 1, 1));
        Tuple position = new Tuple(0, 0, 0, 1);
        ColorM tester = lighting(s.getMaterial(), light, position, eyeV, normalV);
        System.out.println(tester);
    }

    public static ColorM lighting(Material material, Light light, Tuple point, Tuple eVector, Tuple nVector) {
        if(point.getW() != 1 || eVector.getW() != 0 || nVector.getW() != 0) {throw new IllegalArgumentException();}
        ColorM ambient, diffuse, specular, effective_color;
        Tuple reflectv, lightv;
        double reflect_dot_eye, factor, Light_dot_normal;
        effective_color = material.getColor().blendColors(light.getIntensity());
        lightv = light.getPosition().subPts(point).normalize();
        ambient = effective_color.colorScalarMultiplication(material.getAmbient());
        Light_dot_normal = lightv.dotProduct(nVector);
        if(Light_dot_normal < 0) {
            diffuse = new ColorM(0, 0, 0);
            specular = new ColorM(0, 0, 0);
        } else {
            diffuse = effective_color.colorScalarMultiplication(material.getDiffuse()).colorScalarMultiplication(Light_dot_normal);
            reflectv = lightv.scalarMultiplication(-1).reflectAboutNormal(nVector);
            reflect_dot_eye = reflectv.dotProduct(eVector);
            if(reflect_dot_eye <= 0) {specular = new ColorM(0, 0, 0);}
            else {
                factor = Math.pow(reflect_dot_eye, material.getShininess());
                specular = light.getIntensity().colorScalarMultiplication(material.getSpecular()*factor);
            }
        }
        return ambient.addColors(diffuse.addColors(specular));
    }
}
