package pt.ulusofona.lp2.fandeisiaGame;

public class GameRules {
    private int turns;



    private boolean aIActive;
    private   int columns;
    private   int rows;
    private int turnsWithoutTreasures;

    public GameRules(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        turnsWithoutTreasures=0;
        turns=0;
    }

    public int getTurns() {
        return turns;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getTurnsWithoutTreasures() {
        return turnsWithoutTreasures;
    }

    public   void addTurn() {
        turns++;
    }

    public void addTurnsWithoutTreasures() {
        turnsWithoutTreasures++;
    }
    public boolean isaIActive() {
        return aIActive;
    }

    public void setaIActive(boolean aIActive) {
        this.aIActive = aIActive;
    }

    public void resetTurnsWithoutTreasures() {
        turnsWithoutTreasures=0;
    }
}
