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
    private int nrFeiticos;
    protected String encantamento;


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
    protected int devolveIdDaCoordenada(int x, int y, ArrayList<ElementoDoJogo> obstaculos) {
        for (ElementoDoJogo obstaculo : obstaculos) {
            if (obstaculo.estaNestaPosicao(x, y)) {
                return obstaculo.getId();
            }
        }
        return 0;
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

    public boolean estaNestaPosicao(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean encantaCriatura(String spellName, ArrayList<ElementoDoJogo> obstaculos) {
        nrFeiticos++;
        if (encantamento == null || spellName.equals("Descongela")) {
            encantamento = "SemEncantamento";
        }

        if (encantamento.equals("Congela4Ever")) {
            return false;
        }

        int idPosicao = 0;
        if (spellName.equals("EmpurraParaNorte")) {
            idPosicao = devolveIdDaCoordenada(x, y - 1, obstaculos);
            if (y == 0 || idPosicao != 0) {
                return false;
            }
        }
        if (spellName.equals("EmpurraParaEste")) {
            idPosicao = devolveIdDaCoordenada(x + 1, y, obstaculos);
            if (x == limiteEste || idPosicao != 0) {
                return false;
            }
        }
        if (spellName.equals("EmpurraParaOeste")) {
            idPosicao = devolveIdDaCoordenada(x - 1, y, obstaculos);
            if (x == 0 || idPosicao != 0) {
                return false;
            }
        }
        if (spellName.equals("EmpurraParaSul")) {
            idPosicao = devolveIdDaCoordenada(x, y + 1, obstaculos);
            if (y == limiteSul || idPosicao != 0) {
                return false;
            }
        }
        if (spellName.equals("DuplicaAlcance")) {
            if (!validaMovimentos(obstaculos, alcance * 2)) {
                return false;
            }
        }
        if (spellName.equals("ReduzAlcance")) {
            if (!validaMovimentos(obstaculos, 1)) {
                return false;
            }
        }

        encantamento = spellName;
        return true;
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

        return type+".png";
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