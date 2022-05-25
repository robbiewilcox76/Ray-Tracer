public class Pattern {
    private ColorM color1;
    private ColorM color2;
    private Matrix transformation;
    private boolean isStripe, isGradient, isRing, isChecker;
    private Stripe_pattern x;
    private Gradient y;
    private Ring_pattern z;
    private checker_pattern w;

    public Pattern(){
        Matrix m = new Matrix();
        this.transformation = m.identity4();
    }

    public Pattern(ColorM color1, ColorM color2, String i) {
        this.color1 = color1;
        this.color2 = color2;
        Matrix m = new Matrix();
        this.transformation = m.identity4();
        if (i.equals("stripe")) {this.isStripe = true; this.x = new Stripe_pattern();}
        else if (i.equals("gradient")) {this.isGradient = true; this.y = new Gradient();}
        else if(i.equals("ring")) {this.isRing = true; this.z = new Ring_pattern();}
        else if(i.equals("checker")) {this.isChecker = true; this.w = new checker_pattern();}
        else throw new IllegalArgumentException();
    }

    public ColorM getColor1() {
        return this.color1;
    }

    public ColorM getColor2() {
        return this.color2;
    }

    public Matrix transformation() {
        return this.transformation;
    }

    public void setTransformation(Matrix m) {
        this.transformation = m;
    }

    public ColorM pattern_at_object(Object object, Tuple point) {
        Tuple object_point = object.getTransformation().inverse().matrixVectorProduct(point);    
        Tuple pattern_point = this.transformation.inverse().matrixVectorProduct(object_point);
        if(this.isStripe) return this.x.stripe_at(pattern_point, this.color1, this.color2);
        else if(this.isGradient) return this.y.pattern_at(pattern_point, this.color1, this.color2);
        else if(this.isRing) return this.z.ring_at(pattern_point, this.color1, this.color2);
        else {return this.w.checker_at(pattern_point, this.color1, this.color2);}
    }
}
