package pt.ulusofona.lp2.fandeisiaGame;

public class Team {
    private int id;
    private int coins;
    private boolean active;
    private int points;

    public Team(int id) {
        this.id = id;
        points = 0;
        coins = 50;
        active = false;
    }


    public boolean passedPlafond() {
        if(coins > 0){
            return true;
        }
        return  false;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public int getId() {
        return id;
    }
    public void paySpell(int spellValue) {
        coins -= spellValue;
    }
    public void addCoins(int coins) {
        this.coins += coins;
    }
    public int getPoints() {
        return points;
    }
    public void payCreature(int creatueValue) {
        coins -= creatueValue;
    }
    public void setPoints(int points) {
        this.points += points;
    }





}
