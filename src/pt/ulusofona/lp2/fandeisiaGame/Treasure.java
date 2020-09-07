package pt.ulusofona.lp2.fandeisiaGame;

public class Treasure extends GamePieces {
    protected int value;
    protected String type;

    public Treasure(int id, int x, int y) {
        super(id, x, y);
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public boolean thereIsTreasure(int x, int y) {
        if(this.x == x && this.y == y){
            return true;
        }
        return false;
    }


}