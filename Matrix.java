public class Matrix {
    private int size;
    private double[][] matrix;

    public Matrix(){}

    public Matrix(int size) {
        this.size = size;
        this.matrix = new double[this.size][this.size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    public Matrix(int size, double[][] entries) {
        this.size = size;
        this.matrix = entries;
    }

    public String toString() {
        String s = ("");
        for(int i=0; i<this.size; i++) {
            if (i>0) {s+=("\n");}
            for(int j=0; j<this.size; j++) {
                s+=("|" + this.matrix[i][j] + "|");
            }
        }
        return s;
    }

    public int getSize() {
        return this.size;
    }

    public double[][] getEntries() {
        return this.matrix;
    }
    
    public double returnEntry(int row, int col) {
        return this.matrix[row-1][col-1];
    }

    public Matrix multiply(Matrix other) {
        double[][] otherEntries = other.getEntries();
        double[][] productEntries = new double[this.size][this.size];
            for(int row=0; row<this.size; row++) {
                for(int col=0; col<this.size; col++) {
                    productEntries[row][col] = (this.matrix[row][0] * otherEntries[0][col]) + 
                    (this.matrix[row][1] * otherEntries[1][col]) + 
                    (this.matrix[row][2] * otherEntries[2][col]) + 
                    (this.matrix[row][3] * otherEntries[3][col]);
                }
            }
        
        return new Matrix(this.size, productEntries);
    }

    public Tuple matrixVectorProduct(Tuple tuple) {
        double[] tupleEntries = {tuple.getX(), tuple.getY(), tuple.getZ(), tuple.getW()};
        double[] productEntries = new double[4];
        double entry = 0;
        for(int i=0; i<this.size; i++) {
            for(int j=0; j<this.size; j++) {
                for(int k=0; k<this.size; k++) {
                    entry += this.matrix[i][k] * tupleEntries[k];
                }
                productEntries[i] = entry;
                entry = 0;
            }
        }
        return new Tuple(productEntries[0], productEntries[1], productEntries[2], tuple.getW());
    }

    public Matrix identity4() {
        double[][] identity = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return new Matrix(4, identity);
    }

    public boolean equals(Matrix dos) {
        double epsilon = 0.0001;
        if(this.size != dos.getSize()) {return false;}
        double[][] dosEntries = dos.getEntries();
        for(int i=0; i<this.size; i++) {
            for(int j=0; j<this.size; j++) {
                if (Math.abs(this.matrix[i][j] - dosEntries[i][j]) > epsilon) {return false;}
            }
        }
        return true;
    }

    public Matrix transpose() {
        double[][] transpose = new double[this.size][this.size];
        for(int i=0; i<this.size; i++) {
            for(int j=0; j<this.size; j++) {
                transpose[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(this.size, transpose);
    }

    public double twoDeterminant() {
        if(this.size != 2) {throw new IllegalArgumentException();}
        return ((this.matrix[0][0] * this.matrix[1][1]) - (this.matrix[0][1] * this.matrix[1][0]));
    }

    public Matrix subMatrix(int row, int col) {
        double[][] newArr = new double[this.size-1][this.size-1];
        int p = 0;
        for( int i = 0; i < this.size; ++i)
        {
            if ( i == row)
                continue;

            int q = 0;
            for( int j = 0; j < this.size; ++j)
            {
                if ( j == col)
                    continue;

                newArr[p][q] = this.matrix[i][j];
                ++q;
            }

            ++p;
        }
        return new Matrix(this.size-1, newArr);
    }

    public double minor(int row, int col) {
        Matrix sub = new Matrix(this.size-1);
        double x = 0;
        if(this.size == 3) {return this.subMatrix(row, col).twoDeterminant();}
        if(this.size == 4) {
            sub = this.subMatrix(row, col);
            x = sub.determinant();
            return x;
        }
        else throw new IllegalArgumentException();
    }

    public double cofactor(int row, int col) {
        double num = this.minor(row, col);
        if((row + col) % 2 != 0) {
            num = -num;
        }
        return num;
    }

    public double determinant() {
        double det = 0;
        if (this.size == 2) {return this.twoDeterminant();}
        if(this.size == 3) {
            for(int i=0; i<this.size; i++) {
                det += this.matrix[0][i]*this.cofactor(0, i);
            }
            return det;
        }
        if(this.size == 4) {
            double finaldet = 0;
            for(int i=0; i<4; i++) {
                det = 0;
                Matrix submatrix = this.subMatrix(0, i);
                for(int j=0; j<3; j++) {
                    det += submatrix.getEntries()[0][j]*submatrix.cofactor(0, j);
                }
                det *= this.matrix[0][i];
                if(i % 2 != 0) {
                    det = -det;
                }
                finaldet += det;
            }
            return finaldet;
        }
        else throw new IllegalArgumentException();
    }

    public boolean isInvertible() {
        if (this.determinant() != 0) return true;
        return false;
    }

    public Matrix inverse() {
        double[][] iEntries = new double[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                iEntries[j][i] = this.cofactor(i, j)/this.determinant();
                //System.out.println(this.cofactor(i, j) + " " + this.determinant());
            }
        }
        return new Matrix(this.size, iEntries);
    }
}
