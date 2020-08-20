package pt.ulusofona.lp2.fandeisiaGame;

public class Treasure extends GameElements   {
    protected int pontos;
    protected String tipo;

    public Treasure(int id, int x, int y) {
        super(id, x, y);
    }

    public boolean posicaoDoTesouro(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getPontos() {
        return pontos;
    }

    public String getTipo() {
        return tipo;
    }
}