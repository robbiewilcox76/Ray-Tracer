import java.awt.Color;

public class ColorM {
    private double red;
    private double green;
    private double blue;

    //default constructor instantiates to white
    public ColorM() {
        this.red = 1;
        this.green = 1;
        this.blue = 1;
    }

    public ColorM(Color c) {
        this.red = c.getRed();
        this.blue = c.getBlue();
        this.green = c.getGreen();
    }

    public ColorM(double red, double green, double blue) {
        this.red = red;
        this.green = green; 
        this.blue = blue;
    }

    public double getRed() {
        return this.red;
    }

    public double getGreen() {
        return this.green;
    }

    public double getBlue() {
        return this.blue;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public void getBlue(double blue) {
        this.blue = blue;
    }

    public String toString() {
        return ("ColorM with RGB values of (" + this.red + ", " + this.green + ", " + this.blue + ")");
    }
    public ColorM blendColors(ColorM color2) {
        return new ColorM(this.red*color2.getRed(), this.green*color2.getGreen(), this.blue*color2.getBlue());
    }

    public Color tojavaColor() {
        int red, green, blue;
        if(this.red>1) {red = 255;}
        else {red = (int)(this.getRed()*255);}
        if(this.green>1) {green = 255;}
        else {green = (int)(this.getGreen()*255);}
        if(this.blue>1) {blue = 255;}
        else {blue = (int)(this.getBlue()*255);}
        return new Color(red, green, blue);
    }

    public ColorM colorScalarMultiplication(double scalar) {
        return new ColorM(this.red*scalar, this.green*scalar, this.blue*scalar);
    }

    public ColorM addColors(ColorM color2) {
        return new ColorM(this.red+color2.getRed(), this.green+color2.getGreen(), this.blue+color2.getBlue());
    }

    public ColorM subColors(ColorM color2) {
        return new ColorM(this.red-color2.getRed(), this.green-color2.getGreen(), this.blue-color2.getBlue());
    }
}

