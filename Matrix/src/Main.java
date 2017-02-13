
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
        int size = 4;
        int start = 5;
        Matrix grid = genGridCOnnectionMatrix(size, size);
//        for (int i = 0; i < size*size; i++){
//            grid.set(0, i, 0D);
//            grid.set(i, 0, 0D);
//        }
        System.out.println(grid);
//        for (int i = 0; i < size*size; i++){
//            grid.set(i, start, 0D);
//        }
        Matrix move = grid.getMatRow(start);
        Matrix move2 = move.mul(grid);
        Matrix commonPaths = move2;

        System.out.println(commonPaths);

        for (int c=0; c<grid.getNumRows(); c++){
            Double val = commonPaths.get(start, c);
            if (val == 0D){
                continue;
            }

            for (int r=0; r<grid.getNumRows(); r++){
                if (grid.get(r, c) == 0D){
                    continue;
                }
                Double cval = commonPaths.get(start, r);
                if (cval == 0){
                    continue;
                }
                if (cval <= val){
                    grid.set(r, c, 0D);
                }
            }
        }

        System.out.println(grid);

//        Position dir = new Position(1,1);
//        Position curr = new Position(start/size, start%size);
//        for (int i=0; i<8; i++) {
//            Position checkPos = curr.add(dir);
//            dir = nextTerm(dir);
//            start = checkPos.x*size + checkPos.y;
//            move = grid.getMatRow(start);
//            move2 = move.mul(grid);
//            commonPaths = move2.eleMul(move);
//
//            if (start == 2);
//                System.out.println(commonPaths);
//
//            for (int c = 0; c < grid.getNumRows(); c++) {
//                Double val = commonPaths.get(start, c);
//                if (val == 0D) {
//                    continue;
//                }
//
//                for (int r = 0; r < grid.getNumRows(); r++) {
//                    if (grid.get(r, c) == 0D) {
//                        continue;
//                    }
//                    Double cval = commonPaths.get(start, r);
//                    if (cval == 0) {
//                        continue;
//                    }
//                    if (cval <= val) {
//                        grid.set(r, c, 0D);
//                    }
//                }
//            }
//        }
//
//        System.out.println(grid);
    }
}
