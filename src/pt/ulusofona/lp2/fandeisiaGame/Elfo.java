package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

public class Elfo extends Creature {
    public Elfo(int id, String tipo, String orientacao, int teamID, int x, int y) {
        super(id, tipo,orientacao, teamID, x, y);
        custo = 5;
        range = 2;
        rangeMemory = range;
        pulaBuraco = true;
        tipoDeMovimento = "TodasAsDirecoes";
    }


    @Override
    protected boolean canMove(ArrayList<GamePieces> creaturesAndHoles, int creatureRange, GameRules gameRules, ArrayList<Treasure> treasures) {
        int jumpsCounter = 0;
        for (int i = 1; i <= creatureRange; i++) {

            switch(orientacao){
                case "Norte":
                    if ( y - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
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
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x, y + i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Este":
                    if (x + creatureRange > gameRules.getRows()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
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
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x - i, y,creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sudeste":
                    if (y + creatureRange > gameRules.getColumns() -1 || x + creatureRange > gameRules.getRows()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x + i, y + i,creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Nordeste":
                    if (y - creatureRange < 0 || x + creatureRange >gameRules.getRows()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x + i, y - i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sudoeste":
                    if (y + creatureRange > gameRules.getColumns() -1 || x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x - i, y + i,creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Noroeste":
                    if (y - creatureRange < 0 || x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter,creaturesAndHoles,x,y)){
                        continue;
                    }
                    if(freeSquare(x - i, y - i,creaturesAndHoles)){
                        return false;
                    }
                    break;
            }

        }

        return true;
    }
    protected Boolean canJump(int i, int creatureRange, int jumpsCounter, ArrayList<GamePieces> creaturesAndHoles, int x, int y){
        if (pulaBuraco == true && i < creatureRange || jumpsCounter < 1) {
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