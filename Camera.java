import java.awt.Color;

public class Camera {
    private double hsize;
    private double vsize;
    private double field_of_view;
    private Matrix transform;
    private double pixel_size;
    private double c_hw, c_hh;

    public Camera(double hsize, double vsize, double field_of_view) {
        this.hsize = hsize;
        this.vsize = vsize;
        this.field_of_view = Math.toRadians(field_of_view);
        this.transform = new Matrix(4).identity4();
        double halfView = Math.tan(this.field_of_view/2);
        //System.out.println(halfView);
        double aspect = this.hsize/this.vsize;
        if(aspect>=1) {
            this.c_hw = halfView;
            this.c_hh = halfView/aspect;
        } else {
            this.c_hw = halfView*aspect;
            this.c_hh = halfView;
        }
        this.pixel_size = (this.c_hw*2)/this.hsize;
    }

    public void setTransform(Matrix transformation) {
        this.transform = transformation;
    }

    public String toString() {
        return ("\nCamera attributes\n\n" + "horizontal size: " + this.hsize + "\nvertical size: " + this.vsize
         + "\nfield of view: " + Math.toDegrees(this.field_of_view) + " degrees" + "\npixel size: " + this.pixel_size);
    }

    public Ray ray_for_pixel(double x, double y) {
        double xoffset = (x+0.5) * this.pixel_size;
        double yoffset = (y+0.5) * this.pixel_size;
        double worldx = this.c_hw - xoffset;
        double worldy = this.c_hh - yoffset;
        Tuple pixel = this.transform.inverse().matrixVectorProduct(new Tuple(worldx, worldy, -1, 1));
        Tuple origin = this.transform.inverse().matrixVectorProduct(new Tuple(0, 0, 0, 1));
        Tuple direction = pixel.subPts(origin).normalize();
        return new Ray(origin, direction);
    }

    public void render(World w) {
        Canvas canvas = new Canvas((int)this.hsize, (int)this.vsize);
        for(int y=0; y<this.vsize; y++) {
            for(int x=0; x<this.hsize; x++) {
                Ray ray = this.ray_for_pixel(x, y);
                ColorM colorm = w.color_at(ray, 5);
                Color javaC = colorm.tojavaColor();
                canvas.writePixel(x, y, javaC);
            }
        }
    }
}
