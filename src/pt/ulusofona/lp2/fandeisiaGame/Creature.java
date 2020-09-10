package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;


public abstract class Creature extends GamePieces implements Comparable<Creature> {
    private String tipo;
    protected String orientacao;
    protected  String tipoDeMovimento;

    private int teamID;

    private int pontos;
    protected int custo;
    private String image;

    private String enchantment;
    protected int range;
    protected int rangeMemory;
    protected boolean pulaBuraco;
    protected boolean pulaSobre;
    private int gold;
    private int silver;
    private int feiticos;
    private int bronze;
    private int passos;



    public Creature() {

    }

    public Creature(int id, String tipo, String orientacao, int teamID, int x, int y) {
        super(id, x, y);
        this.tipo = tipo;
        this.teamID = teamID;
        this.enchantment = null;
        this.orientacao = orientacao;
        this.image = tipo + ".png";
        this.passos = 0;
        this.feiticos = 0;
    }
    @Override
    public int compareTo(Creature creature) {
        int id = this.id;
        int creatureId = creature.getId();
        if (id > creatureId) {
            return 1;
        } else if (id < creatureId) {
            return -1;
        }
        return 0;
    }


    public int getRacio() {

        if (getTesouros() == 0){
            return 0;
        }
        return passos/getTesouros();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagePNG() {
        return image;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getFeiticos() {
        return feiticos;
    }

    public void setFeiticos(int feiticos) {
        this.feiticos = feiticos;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getPassos() {
        return passos;
    }

    public void setPassos(int passos) {
        this.passos = passos;
    }

    protected abstract boolean canMove(ArrayList<GamePieces> obstaculos, int alcanceCriatura, GameRules gameRules, ArrayList<Treasure> treasures);



    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }
    public int getTesouros() {
        return bronze + gold + silver;
    }


    public int getTeamID() {
        return teamID;
    }



    @Override
    public String toString() {
        if (tipo.equals("Dwarf")) {
            if (enchantment == null) {
                enchantment = "Nenhum";
            }
            return id + " | " + tipo + " | " + teamID + " | " + enchantment + " | " + (gold + silver + bronze) + " @ " + "(" + x + ", " + y + ") " + orientacao;

        } else {
            return id + " | " + tipo + " | " + teamID + " | " + (gold + silver + bronze) + " @ " + "(" + x + ", " + y + ") " + orientacao;
        }

    }


    protected void move(ArrayList<GamePieces> creaturesAndHoles, GameRules gameRules, ArrayList<Treasure> treasures) {
        if (!enchantment.equals("Nenhum")) {
            if (enchantment.equals("Congela") || enchantment.equals("Congela4Ever")) {
                return;
            }
        }


        if (canMove(creaturesAndHoles, range,gameRules, treasures)) {
            switch(orientacao){
                case "Norte":
                    passos += range;
                    y -= range;
                    break;
                case "Sul":
                    passos += range;
                    y += range;
                    break;
                case "Este":
                    passos += range;
                    x += range;
                    break;
                case "Oeste":
                    passos += range;
                    x -= range;
                    break;
                case "Noroeste":
                    passos += range;
                    x -= range;
                    y -= range;
                    break;
                case "Sudeste":
                    passos += range;
                    x += range;
                    y += range;
                    break;
                case "Sudoeste":
                    x -= range;
                    y += range;
                    passos += range;
                    break;
                case "Nordeste":
                    passos += range;
                    x += range;
                    y -= range;
                    break;
            }


        } else {



            switch (orientacao) {

                case "Nordeste":
                    orientacao = "Este";
                    break;
                case "Norte":
                    if (tipoDeMovimento.equals("TodasAsDirecoes")){
                        orientacao = "Nordeste";
                    }else {
                        orientacao = "Este";
                    }
                    break;
                case "Este":
                    if (tipoDeMovimento.equals("TodasAsDirecoes")){
                        orientacao = "Sudeste";
                    }else {
                        orientacao = "Sul";
                    }
                    break;
                case "Sul":
                    if (tipoDeMovimento.equals("TodasAsDirecoes")){
                        orientacao = "Sudoeste";
                    }else {
                        orientacao = "Oeste";
                    }
                    break;
                case "Sudeste":
                    orientacao = "Sul";
                    break;
                case "Sudoeste":
                    orientacao = "Oeste";
                    break;
                case "Noroeste":
                    orientacao = "Norte";
                    break;
                case "Oeste":
                    if (tipoDeMovimento.equals("TodasAsDirecoes")){
                        orientacao = "Noroeste";
                    }else {
                        orientacao = "Norte";
                    }
                    break;
            }
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(String enchantment) {
        this.enchantment = enchantment;
    }

    protected Boolean freeSquare(int x, int y, ArrayList<GamePieces> creaturesAndHoles) {
        for (GamePieces c : creaturesAndHoles) {
            if (c.freeSquare(x, y)) {
                return true;
            }
        }
        return false;
    }


    protected void clearSpells() {
        if (!enchantment.equals("Freezes4Ever")) {
            enchantment = "null";
        }
        range = rangeMemory;
    }

    protected void bewitchCreature() {
        if (enchantment!= null) {
            return ;
        }
        switch(enchantment){
            case "EmpurraParaNorte":
                passos++;//quantos passos a criatura deu
                y--;
                break;
            case "EmpurraParaSul":
                passos++;
                y++;
                break;
            case "EmpurraParaOeste":
                passos++;
                x--;
                break;
            case "EmpurraParaEste":
                passos++;
                x++;
                break;
            case "ReduzAlcance":
                passos = 1;
                break;
            case "Descongelar":
                enchantment ="None";
                break;
            case "DuplicaAlcance":
                range = range + range;
                break;
        }

    }
    public void setTreasurePoints(int points) {
        if (points == 1) {
            bronze++;
        }
        if (points == 2) {
            silver++;
        }
        if (points == 3) {
            gold++;
        }


        this.pontos += points;

    }




    public boolean enchant(String spell, ArrayList<GamePieces> creaturesAndHoles, GameRules gameRules, ArrayList<Treasure> treasures) {
        feiticos++;
        if (enchantment.equals("Freezes4Ever")) {
            return false;
        }
        if ( spell.equals("Descongelar")) {
            enchantment = null;
        }

        switch(spell){
            case "EmpurraParaNorte":
                if (freeSquare(x, y - 1, creaturesAndHoles) || y == 0   ) {
                    return false;
                }
            case "EmpurraParaSul":
                if (freeSquare(x, y + 1, creaturesAndHoles) || y == gameRules.getColumns() -1)   {
                    return false;
                }
            case "EmpurraParaOeste":
                if (freeSquare(x - 1, y, creaturesAndHoles) || x == 0)   {
                    return false;
                }
            case "EmpurraParaEste":
                if (freeSquare(x + 1, y, creaturesAndHoles) || x == gameRules.getRows() -1 ) {
                    return false;
                }
            case "ReduzAlcance":
                if (!canMove(creaturesAndHoles, 1,gameRules, treasures)) {
                    return false;
                }
            case "DuplicaAlcance":
                if (!canMove(creaturesAndHoles, range * 2,gameRules, treasures)) {
                    return false;
                }
        }
        enchantment = spell;
        return true;
    }





}