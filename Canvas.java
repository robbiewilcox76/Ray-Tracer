import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;

import javax.imageio.ImageIO;

public class Canvas {
    private int width;
    private int height;
    private int RGB;
    private BufferedImage pic;
    private File file;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.RGB = 0;
        this.file = new File("image.PNG");
        this.pic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                pic.setRGB(x, y, this.RGB);
            }
        }
        try {
            ImageIO.write(pic, "PNG", file);
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
            }

    }

    public Canvas(int width, int height, int RGB) {
        this.width = width;
        this.height = height;
        this.RGB = RGB;
        this.file = new File("image.PNG");
        this.pic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                pic.setRGB(x, y, this.RGB);
            }
        }
        try {
            ImageIO.write(pic, "PNG", file);
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
            }
        }

    public int getwidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRGB() {
        return this.RGB;
    }

    public void setRGB(Color color) {
        this.RGB = color.getRGB();
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                pic.setRGB(x, y, this.RGB);
            }
        }
        try {
            ImageIO.write(pic, "PNG", file);
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
            }
        }

    public void writePixel(int x, int y, Color color) {
        this.RGB = color.getRGB();
        pic.setRGB(x, y, this.RGB);
        try {
            ImageIO.write(pic, "PNG", file);
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(1);
        }
    } 
    /*public String toPPM() {
        String PPM = ("P3\n" + this.width + " " + this.height) + "\n255";
        for()
        return PPM;
    }*/
}
