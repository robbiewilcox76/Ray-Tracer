public class RayTests {
    public static void main(String[] args) {
        Ray r = new Ray(new Tuple(0, 2, -5, 1), new Tuple(0, 0, 1, 0));
        Sphere x = new Sphere();
        Object s = new Object(x);
        System.out.println(x.intersects(r));
        Intersection i1 = new Intersection(x.intersects(r).get(0), s);
        Intersection i2 = new Intersection(x.intersects(r).get(1), s);
        Intersections i3 = new Intersections(i1, i2);
        System.out.println(i3.hit());
    }
}
