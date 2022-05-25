public class Transformation {
    
    public Transformation(){}

    public Matrix TranslationMatrix(double x, double y, double z) {
        double[][] entries = {{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    public Matrix scalingMatrix(double x, double y, double z) {
        double[][] entries = {{x, 0, 0, 0}, {0, y, 0, 0}, {0, 0, z, 0}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    public Matrix rotationAboutX(double angle) {
        angle = Math.toRadians(angle);
        double[][] entries = {{1, 0, 0, 0}, {0, Math.cos(angle), -Math.sin(angle), 0}, {0, Math.sin(angle), Math.cos(angle), 0}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    public Matrix rotationAboutY(double angle) {
        angle = Math.toRadians(angle);
        double[][] entries = {{Math.cos(angle), 0, Math.sin(angle), 0}, {0, 1, 0, 0}, {-Math.sin(angle), 0, Math.cos(angle), 0}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    public Matrix rotationAboutZ(double angle) {
        angle = Math.toRadians(angle);
        double[][] entries = {{Math.cos(angle), -Math.sin(angle), 0, 0}, {Math.sin(angle), Math.cos(angle), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    //example: xy means 'change in x in proportion to y'
    public Matrix shear(double xy, double xz, double yx, double yz, double zx, double zy) {
        double[][] entries = {{1, xy, xz, 0}, {yx, 1, yz, 0}, {zx, zy, 1, 0}, {0, 0, 0, 1}};
        return new Matrix(4, entries);
    }

    public Matrix view_transform(Tuple from, Tuple to, Tuple up) {
        if(from.getW() != 1 && to.getW() != 1 && up.getW() != 0) {throw new IllegalArgumentException();}
        Tuple forward = to.subPts(from).normalize();
        Tuple left = forward.crossProduct(up.normalize());
        Tuple true_up = left.crossProduct(forward);
        double[][] entries = {{left.getX(), left.getY(), left.getZ(), 0},
        {true_up.getX(), true_up.getY(), true_up.getZ(), 0}, {-forward.getX(), -forward.getY(), -forward.getZ(), 0}, {0, 0, 0, 1}};
        Matrix orientation = new Matrix(4, entries);
        Transformation trans = new Transformation();
        Matrix transt = trans.TranslationMatrix(-from.getX(), -from.getY(), -from.getZ());
        return orientation.multiply(transt);
    }
}
