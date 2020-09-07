package pt.ulusofona.lp2.fandeisiaGame;
public class InsufficientCoinsException extends Exception {
    int costLDR;
    int resistenceCost;

    public InsufficientCoinsException(int costLDR, int resistenceCost) {
        this.costLDR = costLDR;
        this.resistenceCost = resistenceCost;
    }

    public boolean teamRequiresMoreCoins(int teamId) {
        if (teamId == 10) {
            if(costLDR > 50){
                return true;
            }else{
                return false;
            }
        } else {
            if(resistenceCost > 50){
                return true;
            }else{
                return false;
            }
        }
    }

    public int getRequiredCoinsForTeam(int teamId) {
        if (teamId == 10) {
            return this.costLDR;
        } else {
            return this.resistenceCost;
        }
    }
}
