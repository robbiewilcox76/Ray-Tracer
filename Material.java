public class Material {
    private double ambient;
    private double diffuse;
    private double specular;
    private double shininess;
    private ColorM color;
    private Pattern pattern;
    private boolean hasPattern;
    private double reflective;
    private double refractive_index;
    private double transparency;

    public Material() {
        this.ambient = 0.1;
        this.diffuse = 0.9;
        this.specular = 0.9;
        this.shininess = 200.0;
        this.color = new ColorM(1, 1, 1);
        this.hasPattern = false;
        this.reflective = 0.0;
        this.refractive_index = 1.0;
        this.transparency = 0.0;
    }

    public Material(double ambient, double diffuse, double specular, double shininess, 
    ColorM color, Pattern pattern, double reflective, double refractive_index, double transparency) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
        this.color = color;
        this.hasPattern = false;
        this.reflective = reflective;
        this.refractive_index = refractive_index;
        this.transparency = transparency;
    }

    public void setColor(ColorM color) {
        this.color = color;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
        this.hasPattern = true;
    }

    public void setReflective(double reflective) {
        this.reflective = reflective;
    }

    public void setRefract(double refractive_index) {
        this.refractive_index = refractive_index;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public double getAmbient() {
        return this.ambient;
    }

    public double getDiffuse() {
        return this.diffuse;
    }

    public double getSpecular() {
        return this.specular;
    }

    public double getShininess() {
        return this.shininess;
    }

    public ColorM getColor() {
        return this.color;
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public boolean hasPattern() {
        return this.hasPattern;
    }

    public double getReflective() {
        return this.reflective;
    }

    public double getRefract() {
        return this.refractive_index;
    }

    public double getTransparency() {
        return this.transparency;
    }
}
