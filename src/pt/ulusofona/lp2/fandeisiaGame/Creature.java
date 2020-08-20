package pt.ulusofona.lp2.fandeisiaGame;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Creature implements Serializable, Comparable {


    protected int id;
    public String type;
    protected int teamId;
    protected int totalPoints;
    protected boolean superBreak;
    protected String spellName;
    protected int spellCost = 0;
    protected boolean reduceR = true;
    protected int golds = 0;
    protected int silvers = 0;
    protected int bronzes = 0;
    protected boolean rangex2;
    protected int spellsNr;
    protected int kmsTravelled;
    protected int mov;
    //int columns, rows;


    @Override
    public String toString() {
        return id + " | " + type + " | " + teamId + " | " + (golds+silvers+bronzes) + " @ (" + x + ", " + y + ") " + orientation;
    }
    protected int x;
    protected int y;
    protected String orientation;
    protected String imagem;


    public Creature(int id, String type, int teamId, int x, int y, String orientation) {
        this.id = id;
        this.type = type;
        this.teamId = teamId;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void resetSpellCost() {
        spellCost = 0;
    }

    public int getBronzes() {
        return bronzes;
    }

    public int getGolds() {
        return golds;
    }

    public int getSilvers() {
        return silvers;
    }

    public void setBronzes() {
        bronzes ++;
    }
    public void setGolds() {
        golds ++;
    }
    public void setSilvers() {
        silvers ++;
    }


    public int getKmsTravelled() {
        return kmsTravelled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setPosition(int x, int y, String orientacao) {
        this.x = x;
        this.y = y;
        this.orientation = orientacao;
    }

    public int getId() {
        return id;
    }

    public String getSpellName() {
        if (spellName == null){
            return null;
        }
        return spellName;
    }

    public void addSpellNr(){
        spellsNr++;
    }

    public int getSpellsNr() {
        return spellsNr;
    }

    public int getSpellCost() {
        return spellCost;
    }

    public String getImagePNG() {
        if (teamId == 10) {
            if (orientation.equals("Norte")) {
                imagem = "crazy_emoji_white_UP.png";
            }
            if (orientation.equals("Este")) {
                imagem = "crazy_emoji_white_RIGHT.png";
            }
            if (orientation.equals("Sul")) {
                imagem = "crazy_emoji_white_DOWN.png";
            }
            if (orientation.equals("Oeste")) {
                imagem = "crazy_emoji_white_Left.png";
            }
            if (orientation.equals("Noroeste")) {
                imagem = "setabNW.png";
            }
            if (orientation.equals("Nordeste")) {
                imagem = "setawhitene.png";
            }
            if (orientation.equals("Sudeste")) {
                imagem = "setawhitese.png";
            }
            if (orientation.equals("Sudoeste")) {
                imagem = "setawhitesw.png";
            }
        }
        if (teamId == 20) {
            if (orientation.equals("Norte")) {
                imagem = "crazy_emoji_black_UP.png";
            }
            if (orientation.equals("Este")) {
                imagem = "crazy_emoji_black_RIGHT.png";
            }
            if (orientation.equals("Sul")) {
                imagem = "crazy_emoji_black_DOWN.png";
            }
            if (orientation.equals("Oeste")) {
                imagem = "crazy_emoji_black_Left.png";
            }
            if (orientation.equals("Noroeste")) {
                imagem = "setaNW.png";
            }
            if (orientation.equals("Nordeste")) {
                imagem = "setaNE.png";
            }
            if (orientation.equals("Sudoeste")) {
                imagem = "setaSW.png";
            }
            if (orientation.equals("Sudeste")) {
                imagem = "setaSE.png";
            }


        }
        return imagem;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getTotalTreasures() { return golds + silvers + bronzes;}


    public void setSpellCost(int spellCost) {
        this.spellCost = spellCost;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getTotalPoints() {
        totalPoints = bronzes + silvers * 2 + golds * 3;
        return totalPoints;
    }


    public abstract String getType();

    public void setTotalPoints(int i) {
        totalPoints = i;
    }


    public abstract Object[] changePosition(int rows, int columns, Creature[][] map, List<Hole> holes, Creature[][] mapGig, ArrayList<Creature> creatures);


    public abstract boolean alteraAlcance(List<Hole> holes, Creature[][] map, String spellName, int rows, int columns);


    public abstract boolean congela(String spellName, int currentTeam, int plafondP, int plafondAI);

}