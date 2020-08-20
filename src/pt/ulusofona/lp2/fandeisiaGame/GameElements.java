package pt.ulusofona.lp2.fandeisiaGame;

public class GameElements {
    protected int id;
    protected int x, y;

    public GameElements() {
    }

    public GameElements(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public boolean estaNestaPosicao(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}