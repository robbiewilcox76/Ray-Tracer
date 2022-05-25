import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class WorldTests {
    public static void main(String[] args) {
        Transformation t = new Transformation();
        World world = new World();
        world.emptyWorld();

        /*Sphere floors = new Sphere();
        Matrix floortrans = t.scalingMatrix(10, 0.01, 10);
        floors.setTransformation(floortrans);
        Material floormat = new Material();
        floormat.setColor(new ColorM(1, 0.9, 0.9));
        floormat.setSpecular(0);
        floors.setMaterial(floormat);
        Object floor1 = new Object(floors);
        world.addObject(floor1); 

        Sphere wallLs = new Sphere();
        Material mats = new Material();
        Matrix wallLtrans = t.TranslationMatrix(0, 0, 5);
        wallLtrans = wallLtrans.multiply(t.rotationAboutY(-45));
        wallLtrans = wallLtrans.multiply(t.rotationAboutX(90));
        wallLtrans = wallLtrans.multiply(t.scalingMatrix(10, 0.01, 10));
        wallLs.setTransformation(wallLtrans);
        mats.setColor(new ColorM(1, 0.9, 0.9));
        wallLs.setMaterial(mats);
        Object wallL = new Object(wallLs);
        world.addObject(wallL); 

        Sphere wallRs = new Sphere();
        Matrix wallRtrans = t.TranslationMatrix(0, 0, 5).multiply(t.rotationAboutY(45)).multiply(t.rotationAboutX(90)).multiply(t.scalingMatrix(10, 0.01, 10));
        Material matt = new Material();
        wallRs.setTransformation(wallRtrans);
        matt.setColor(new ColorM(1, 0.9, 0.9));
        wallRs.setMaterial(matt);
        Object wallR = new Object(wallRs);
        world.addObject(wallR); */
        
        Sphere middleS = new Sphere();
        Matrix st = t.TranslationMatrix(-0.5, 1, 0.5);
        middleS.setTransformation(st);
        Material midMat = new Material();
        midMat.setDiffuse(0.7);
        midMat.setSpecular(0.3);
        midMat.setPattern(new Pattern(new ColorM(1, 0, 0), new ColorM(0, 0, 1), "ring"));
        middleS.setMaterial(midMat);
        Object middle = new Object(middleS);
        world.addObject(middle);

        Sphere sGreens = new Sphere();
        Matrix greenTrans = t.TranslationMatrix(1.5, 0.5, -0.5).multiply(t.scalingMatrix(0.5, 0.5, 0.5));
        sGreens.setTransformation(greenTrans);
        Material green = new Material();
        //green.setTransparency(0.9);
        green.setColor(new ColorM(0.5, 1, 0.1));
        green.setDiffuse(0.7);
        green.setSpecular(0.3);
        sGreens.setMaterial(green);
        Object sGreen = new Object(sGreens);
        world.addObject(sGreen); 

        Sphere lefts = new Sphere();
        Matrix lTrans = t.TranslationMatrix(-1.5, 0.33, -0.75).multiply(t.scalingMatrix(0.33, 0.33, 0.33));
        lefts.setTransformation(lTrans);
        Material x = new Material();
        x.setColor(new ColorM(1, 0.8, 1));
        x.setDiffuse(0.7);
        x.setSpecular(0.3);
        lefts.setMaterial(x);
        Object left = new Object(lefts);
        world.addObject(left); 

        Plane plane = new Plane();
        Object xyz = new Object(plane);
        world.addObject(xyz);
        Pattern pattern = new Pattern(new ColorM(1, 1, 1), new ColorM(0.5, 0.5, 0.5), "checker");
        xyz.getMaterial().setReflective(1);
        xyz.getPlane().getMaterial().setPattern(pattern);

        Light source = new Light(new Tuple(-10, 10, -10, 1), new ColorM(1, 1, 1));
        world.setLight(source);
        Camera camera = new Camera(100, 50, 60);
        camera.setTransform(t.view_transform(new Tuple(0, 1.5, -5, 1), new Tuple(0, 1, 0, 1), new Tuple(0, 1, 0, 0)));

        camera.render(world);
    }
}
