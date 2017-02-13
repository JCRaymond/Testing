
public class Position {
    public int x;
    public int y;

    public Position(){

    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position add(Position other){
        return new Position(this.x+other.x, this.y+other.y);
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
