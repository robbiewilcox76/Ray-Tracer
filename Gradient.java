public class Gradient {
    public Gradient() {}

    public ColorM pattern_at(Tuple point, ColorM color1, ColorM color2) {
        ColorM distance = color2.subColors(color1);
        double fraction = point.getX()-Math.floor(point.getX());
        return color1.addColors(distance.colorScalarMultiplication(fraction));
    }
}
