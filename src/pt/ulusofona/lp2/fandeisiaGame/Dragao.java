package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

public class Dragao extends Creature {
    public Dragao(int id, String tipo, String orientation, int teamID, int x, int y) {
        super(id, tipo,orientation, teamID, x, y );
        range = 3;
        rangeMemory = range;
        custo = 9;
        pulaBuraco = true;
        pulaSobre = true;
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
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if(freeSquare(x, y - i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sul":
                    if ( y + creatureRange > gameRules.getColumns()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x, y + i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Este":
                    if (  x + creatureRange > gameRules.getRows()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x + i, y, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Oeste":
                    if ( x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x - i, y, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Nordeste":
                    if (   y - creatureRange < 0 || x + creatureRange > gameRules.getRows()-1){
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if ( freeSquare(x + i, y - i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sudeste":
                    if (  y + creatureRange > gameRules.getColumns()-1|| x + creatureRange >  gameRules.getRows()-1) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x + i, y + i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Sudoeste":
                    if (  y + creatureRange > gameRules.getColumns()-1|| x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x - i, y + i, creaturesAndHoles)){
                        return false;
                    }
                    break;
                case "Noroeste":
                    if (  y - creatureRange < 0 || x - creatureRange < 0) {
                        return false;
                    }
                    if(canJump(i,creatureRange,jumpsCounter)){
                        continue;
                    }
                    if (freeSquare(x - i, y - i, creaturesAndHoles)){
                        return false;
                    }
                    break;
            }


        }

        return true;
    }
    //n pode saltar por cima de um outro dragao
    protected Boolean canJump(int i,int creatureRange,int jumpsCounter){
        if (jumpsCounter < 2 && pulaSobre && i < creatureRange || jumpsCounter < 2 && i < creatureRange && pulaBuraco) {
            jumpsCounter++;
            return true;
        }
        return false;
    }
}
