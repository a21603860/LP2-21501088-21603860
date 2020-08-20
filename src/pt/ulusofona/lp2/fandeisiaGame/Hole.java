package pt.ulusofona.lp2.fandeisiaGame;

import java.io.Serializable;

public class Hole implements Serializable {
    int x, y, id;
    Hole(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    @Override
    public String toString() {
        return  id + "|" + x + "|" + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
