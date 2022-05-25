public class checker_pattern {
    public checker_pattern(){}

    public ColorM checker_at(Tuple point, ColorM color1, ColorM color2) {
        if( (Math.floor(point.getX()) + Math.floor(point.getY()) + Math.floor(point.getZ())) % 2 == 0 ) return color1;
        return color2;
    }
}
