public class Stripe_pattern {
    public Stripe_pattern() {}

    public ColorM stripe_at(Tuple point, ColorM color1, ColorM color2) {
        if(point.getW() != 1) {throw new IllegalArgumentException();}
        if((Math.floor(point.getX()) % 2) == 0) {return color1;}
        return color2;
    }
}
