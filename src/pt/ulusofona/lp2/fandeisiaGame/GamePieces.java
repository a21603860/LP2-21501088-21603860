package pt.ulusofona.lp2.fandeisiaGame;

public class GamePieces {
    protected int id;
    protected int x, y;

    public GamePieces() {
    }

    public GamePieces(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public boolean freeSquare(int x, int y) {
        if(this.x == x && this.y == y){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
