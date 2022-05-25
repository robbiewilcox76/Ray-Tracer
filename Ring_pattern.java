public class Ring_pattern {
    public Ring_pattern(){}

    public ColorM ring_at(Tuple point, ColorM color1, ColorM color2) {
        //System.out.println(Math.pow(point.getX(), 2) + Math.pow(point.getZ(), 2));
        if(Math.floor(Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getZ(), 2))) % 2 == 0) return color1;
        return color2;
    }
}
