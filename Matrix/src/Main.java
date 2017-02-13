
public class Main {

    public static Position nextTerm(Position pos){
        int x = pos.x;
        int y = pos.y;
        Position newPos = new Position();
        newPos.x = x==0 ? x-y : (x*y>0 ? 0 : x);
        newPos.y = y==0 ? x-y : (x*y<0 ? 0 : y);
        return newPos;
    }

    public static Position prevTerm(Position pos){
        int x = pos.x;
        int y = pos.y;
        Position newPos = new Position();
        newPos.x = x==0 ? y-x : (x*y<0 ? 0 : x);
        newPos.y = y==0 ? y-x : (x*y>0 ? 0 : y);
        return newPos;
    }

    public static boolean isValidPos(Position pos, Position[] poss){
        for (Position p : poss){
            if (p.x == pos.x && p.y == pos.y){
                return true;
            }
        }
        return false;
    }

    public static Matrix genGridCOnnectionMatrix(int r, int c) throws InvalidSizeException {
        Matrix grid = Matrix.zero(r*c, r*c);
        Position[] poss = new Position[r*c];
        for (int i=0; i<r*c; i++){
            poss[i] = new Position(i/c, i%c);
        }
        for (Position pos : poss){
            Position dir = new Position(1,1);
            Position checkPos;
            for (int i=0; i<8; i++){
                checkPos = pos.add(dir);
                if (isValidPos(checkPos, poss)){
                    grid.set(pos.x*c+pos.y, checkPos.x*c+checkPos.y, 1D);
                }
                dir = nextTerm(dir);
            }
        }
        return grid;
    }

    public static Double[][] wrapArray(double[][] ary){
        Double[][] ret = new Double[ary.length][ary[0].length];
        for (int r=0; r<ret.length; r++){
            for (int c=0; c<ret[0].length; c++){
                ret[r][c] = ary[r][c];
            }
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        int size = 6;
        int start = 100;
        Matrix grid = genGridCOnnectionMatrix(size, size);

    }
}
