import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class CanvasTests {
    public static void main(String[] args) {
        Sphere glass_sphere = new Sphere();
        glass_sphere.getMaterial().setTransparency(1.0);
        glass_sphere.getMaterial().setRefract(1.5);
        Transformation t = new Transformation();
        Sphere glass_sphere2 = new Sphere();
        glass_sphere2.getMaterial().setTransparency(1.0);
        glass_sphere2.getMaterial().setRefract(1.5);
        Sphere glass_sphere3 = new Sphere();
        glass_sphere3.getMaterial().setTransparency(1.0);
        glass_sphere3.getMaterial().setRefract(1.5);

        double s = Math.sqrt(2)/2;

        World world = new World();
        Ray r = new Ray(new Tuple(0, 0, -3, 1), new Tuple(0, -s, s, 0));
        Plane plane = new Plane();
        plane.setTransformation(t.TranslationMatrix(0, -1, 0));
        plane.getMaterial().setReflective(0.5);
        plane.getMaterial().setTransparency(0.5);
        plane.getMaterial().setRefract(1.5);
        Object floor = new Object(plane);
        world.addObject(floor);

        Sphere sphere = new Sphere();
        sphere.getMaterial().setColor(new ColorM(1, 0, 0));
        sphere.getMaterial().setAmbient(0.5);
        sphere.setTransformation(t.TranslationMatrix(0, -3.5, -0.5));
        Object ball = new Object(sphere);
        world.addObject(ball);
        Intersections xs = new Intersections(new Intersection(Math.sqrt(2), floor));
        Comps comp = new Comps(xs.getIntersections().get(0), r, xs);
        System.out.println(world.shade_hit(comp, 5));

    }
}


