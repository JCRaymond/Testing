import java.lang.reflect.Array;

class InvalidSizeException extends Exception{
    public InvalidSizeException(String message){
        super(message);
    }
}

public class Matrix{
    private int r;
    private int c;
    private Double[][] data;

    public Matrix(int r, int c) throws InvalidSizeException {
        if (r < 1 || c < 1){
            throw new InvalidSizeException("Not a valid Matrix size!");
        }
        this.r = r;
        this.c = c;
        this.data = new Double[r][c];
    }

    public Matrix(Double[][] data){
        this.data = data;
        this.r = data.length;
        this.c = data[0].length;
    }

    public static Matrix zero(int r, int c) throws InvalidSizeException {
        Matrix zero = new Matrix(r, c);
        for (int col=0; col<c; col++){
            for (int row=0; row<r; row++){
                zero.set(row, col, 0D);
            }
        }
        return zero;
    }

    private Double[][] getData(){
        return data;
    }

    public int getNumRows(){
        return r;
    }

    public int getNumCols(){
        return c;
    }

    public Double get(int r, int c){
        return this.getData()[r][c];
    }

    public void set(int r, int c, Double val){
        this.getData()[r][c] = val;
    }

    public Matrix clone(){
        return new Matrix(this.getData());
    }

    public Matrix mul(Matrix other) throws InvalidSizeException {
        if (this.getNumRows() != other.getNumCols()){
            throw new InvalidSizeException("You can not multiply these two matrices together!");
        }
        int dim = this.getNumRows();

        Matrix ret = new Matrix(other.getNumRows(), this.getNumCols());
        for (int trow=0; trow<this.getNumCols(); trow++){
            for (int ocol=0; ocol<other.getNumRows(); ocol++){
                double sum = 0;
                for (int i=0; i<dim; i++){
                    sum += this.get(trow,i) * other.get(i, ocol);
                }
                ret.set(trow, ocol, sum);
            }
        }
        return ret;
    }

    public Matrix binMul(Matrix other) throws InvalidSizeException {
        if (this.getNumRows() != other.getNumCols()){
            throw new InvalidSizeException("You can not multiply these two matrices together!");
        }
        int dim = this.getNumRows();

        Matrix ret = new Matrix(other.getNumRows(), this.getNumCols());
        for (int trow=0; trow<this.getNumCols(); trow++){
            for (int ocol=0; ocol<other.getNumRows(); ocol++){
                double sum = 0;
                for (int i=0; i<dim; i++){
                    sum += this.get(trow,i) * other.get(i, ocol);
                }
                sum = sum==0 ? 0 : 1;
                ret.set(trow, ocol, sum);
            }
        }
        return ret;
    }

    public Matrix add(Matrix other) throws InvalidSizeException{
        if (this.getNumRows() != other.getNumCols() && this.getNumCols() != other.getNumCols()){
            throw new InvalidSizeException("You can not add these two matrices together!");
        }

        Matrix ret = new Matrix(this.getNumRows(), this.getNumCols());
        for (int c=0; c<ret.getNumCols(); c++){
            for (int r=0; r<ret.getNumRows(); r++){
                ret.set(r, c, this.get(r,c) * other.get(r,c));
            }
        }
        return ret;
    }

    public Matrix pow(int pow) throws Exception {
        if (this.getNumCols() != this.getNumRows()){
            throw new InvalidSizeException("The matrix is not square!");
        }
        if (pow < 1){
            throw new Exception("The power is not greater than 0!");
        }
        Matrix ret = this.clone();
        for (int i = 1; i < pow; i++){
            ret = ret.mul(this);
        }
        return ret;
    }

    public Matrix binPow(int pow) throws Exception{
        if (this.getNumCols() != this.getNumRows()){
            throw new InvalidSizeException("The matrix is not square!");
        }
        if (pow < 1){
            throw new Exception("The power is not greater than 0!");
        }
        Matrix ret = this.clone();
        for (int i = 1; i < pow; i++){
            ret = ret.binMul(this);
        }
        return ret;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r<this.getNumRows(); r++){
            sb.append(r).append(": ");
            for (int c = 0; c<this.getNumCols(); c++){
                double val = this.get(r, c);
                sb.append(val == 0 ? "   " : val).append(" ");
            }
            sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }
}
