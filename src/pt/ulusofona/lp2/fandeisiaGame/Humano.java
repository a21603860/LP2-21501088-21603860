package pt.ulusofona.lp2.fandeisiaGame;


import java.util.ArrayList;

public class Humano extends Creature {
    public Humano(int id, String tipo, String orientation, int teamID, int x, int y) {
        super(id, tipo,orientation, teamID, x, y);
        custo = 3;
        range = 2;
        rangeMemory = range;
        tipoDeMovimento = "HorizontaleVertical";
    }

    @Override
    protected boolean canMove(ArrayList<GamePieces> creaturesAndHoles, int creatureRange, GameRules gameRules, ArrayList<Treasure> treasures) {
        for (int i = 1; i <= creatureRange; i++) {
            switch(orientacao){
                case "Norte":
                    if (freeSquare(x, y - i, creaturesAndHoles) || y - creatureRange < 0  ) {
                        return false;
                    }
                    break;
                case "Sul":
                    if (freeSquare(x, y + i, creaturesAndHoles) ||y + creatureRange >  gameRules.getColumns() -1 ) {
                        return false;
                    }
                    break;
                case "Oeste":
                    if ( freeSquare(x - i, y, creaturesAndHoles) || x - creatureRange < 0 ) {
                        return false;
                    }
                    break;
                case "Este":
                    if (freeSquare(x + i, y, creaturesAndHoles) || x + creatureRange >  gameRules.getRows() -1 ) {
                        return false;
                    }
                    break;

            }

        }
        return true;
    }
}
