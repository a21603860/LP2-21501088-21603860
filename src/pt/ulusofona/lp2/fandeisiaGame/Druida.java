package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;
import java.util.Random;

public class Druida extends Creature {
    public Druida(int id, String tipo, String orientation, int teamID, int x, int y) {
        super(id, tipo, orientation ,teamID, x, y);
        range = 1;//alcance
        rangeMemory = range;
        custo = 4;
        tipoDeMovimento = "HorizontaleVertical";
    }

    @Override
    protected boolean canMove(ArrayList<GamePieces> creaturesAndHoles, int creatureRange, GameRules gameRules, ArrayList<Treasure> treasures) {

        if (gameRules.getTurns() % 2 == 0){
            creatureRange = 2;
        }else{
            creatureRange = 1;
        }

        for (int i = 1; i <= creatureRange; i++) {
            switch(orientacao){
                case "Norte":
                    if (freeSquare(x, y - i, creaturesAndHoles) || y - creatureRange < 0  ) {
                        return false;
                    }
                    break;
                case "Sul":
                    if (freeSquare(x, y + i, creaturesAndHoles) ||y + creatureRange > gameRules.getColumns() -1   ) {
                        return false;
                    }
                    break;
                case "Oeste":
                    if (freeSquare(x - i, y, creaturesAndHoles) || x - creatureRange < 0  ) {
                        return false;
                    }
                    break;
                case "Este":
                    if (freeSquare(x + i, y, creaturesAndHoles) || x + creatureRange > gameRules.getRows()-1  ) {
                        return false;
                    }
                    break;

            }

        }

        if (this.x % 2 == 0 && this.y % 2 ==0){
            Random random = new Random();
            int id= random.nextInt(999);
            id = id*-1;
            treasures.add(new Bronze(id, this.x, this.y));
        }

        return true;
    }
}
