package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

public class Gigante extends Creature {
    public Gigante(int id, String tipo, String orientation, int teamID, int x, int y) {
        super(id, tipo,orientation, teamID, x, y);
        custo = 5;
        range = 3;
        rangeMemory = range;
        pulaSobre = true;
        pulaBuraco = true;
        tipoDeMovimento = "HorizontaleVertical";

    }


    @Override
    protected boolean canMove(ArrayList<GamePieces> creaturesAndHoles, int creatureRange, GameRules gameRules, ArrayList<Treasure> treasures) {
        int jumpsCounter = 0;
        for (int i = 1; i <= creatureRange; i++) {

            switch(orientacao){
                case "Norte":
                    if (y - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles)){
                        continue;
                    }
                    if(canJumpGiant(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x, y - i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sul":
                    if (y + creatureRange > gameRules.getColumns() -1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles)){
                        continue;
                    }
                    if(canJumpGiant(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x, y + i,  creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Este":
                    if (x + creatureRange > gameRules.getRows() -1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles)){
                        continue;
                    }
                    if(canJumpGiant(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x + i, y, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Oeste":
                    if (x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles)){
                        continue;
                    }
                    if(canJumpGiant(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x - i, y, creaturesAndHoles)){
                        return false;
                    }
                    break;
            }

        }

        return true;

    }

    private boolean canJumpGiant(int i, int creatureRange, int jumpsCounter, ArrayList<GamePieces> creaturesAndHoles, int x, int y) {
        if (pulaSobre && i < creatureRange && jumpsCounter < 2) {
            Boolean cantJump = false;
            for (GamePieces c : creaturesAndHoles) {
                if(c.getId() < 0){
                    continue;
                }
                Creature creature = (Creature) c;
                if (creature.getTipo().equals("Giant") && creature.getX()== x && creature.getY() == y   ) {
                    cantJump = true;
                }
            }
            if ( cantJump == true){
                return false;
            }
            jumpsCounter++;
        }
        return true;
    }

    private boolean canJump(int i, int creatureRange, int jumpsCounter, ArrayList<GamePieces> creaturesAndHoles)   {
        if (pulaBuraco && i < creatureRange && jumpsCounter < 2) {
            jumpsCounter++;
            return true;
        }
        for(GamePieces h : creaturesAndHoles){
            if(h.getX() == x && h.getY() == y){
                if( h.getId() < 0){
                    jumpsCounter++;
                    return true;
                }
            }
        }
        return false;
    }


}
