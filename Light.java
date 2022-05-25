import java.awt.Color;

public class Light {
    private Tuple position;
    private ColorM intensity;

    public Light(Tuple position, ColorM intensity) {
        if (position.getW() != 1) {throw new IllegalArgumentException();}
        this.position = position;
        this.intensity = intensity;
    }

    public Tuple getPosition() {
        return this.position;
    }

    public ColorM getIntensity() { 
        return this.intensity;
    }

    public String toString() {
        return ("\nPosition: " + this.position.toString() + "\nIntensity: " + this.intensity.toString());
    }
}
