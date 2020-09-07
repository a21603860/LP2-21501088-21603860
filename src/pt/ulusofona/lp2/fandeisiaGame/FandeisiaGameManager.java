package pt.ulusofona.lp2.fandeisiaGame;

import java.io.*;
import java.util.*;

public class FandeisiaGameManager {
    protected ArrayList<Creature> creatures;
    protected ArrayList<Treasure> treasures;
    protected ArrayList<Hole> holes;


    protected Team ldr = new LDR(10);
    protected Team resistence = new Resistence(20);
    private ArrayList<GamePieces> creaturesAndHoles;
    protected GameRules gameRules;

    public String[][] getSpellTypes() {
        String retorno[][] = new String[9][3];
        retorno[0][0] = "EmpurraParaNorte";
        retorno[0][1] = "Move a criatura 1 unidade para Norte.";
        retorno[0][2] = "1";

        retorno[1][0] = "EmpurraParaEste";
        retorno[1][1] = "Move a criatura 1 unidade para Este.";
        retorno[1][2] = "1";

        retorno[2][0] = "EmpurraParaSul";
        retorno[2][1] = "Move a criatura 1 unidade para Sul.";
        retorno[2][2] = "1";

        retorno[3][0] = "EmpurraParaOeste";
        retorno[3][1] = "Move a criatura 1 unidade para Oeste.";
        retorno[3][2] = "1";

        retorno[4][0] = "ReduzAlcance";
        retorno[4][1] = "Reduz o alcance da criatura para:MIN (alcance original, 1)";
        retorno[4][2] = "2";

        retorno[5][0] = "DuplicaAlcance";
        retorno[5][1] = "Aumenta o alcance da criatura para o dobro";
        retorno[5][2] = "3";

        retorno[6][0] = "Congela";
        retorno[6][1] = "A criatura alvo não se move neste turno.";
        retorno[6][2] = "3";

        retorno[7][0] = "Congela4Ever";
        retorno[7][1] = "A criatura alvo não se move mais até ao final do jogo.";
        retorno[7][2] = "10";

        retorno[8][0] = "Descongela";
        retorno[8][1] = "Inverte a aplicação de um Feitiço Congela4Ever.";
        retorno[8][2] = "8";
        return retorno;
    }

    public boolean saveGame(File ficheiro) {

        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheiro));
            out.writeObject(creatures);
            out.writeObject(treasures);
            out.writeObject(ldr);
            out.writeObject(resistence);
            out.writeObject(gameRules);

            out.flush();
            out.close();


        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean loadGame(File file) {
        try{
            FileInputStream fi = new FileInputStream(new File(String.valueOf(file)));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            creatures = (ArrayList<Creature>) oi.readObject();
            treasures= (ArrayList<Treasure>) oi.readObject();
            ldr= (Team) oi.readObject();
            resistence= (Team) oi.readObject();
            gameRules= (GameRules) oi.readObject();


            oi.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public void processTurn() {

        Collections.sort(creatures);
        gameRules.addTurn();
        gameRules.addTurnsWithoutTreasures();

        for (Creature creature : creatures) {
            creature.bewitchCreature();
        }

        for (Creature creature : creatures) {
            creature.move(creaturesAndHoles,gameRules, treasures);
        }
        for (Creature creature : creatures) {
            creature.clearSpells();
        }

        treatsPointsTreasures();

        creaturesAndHoles = new ArrayList<>();
        for (Hole hole : holes) {
            creaturesAndHoles.add(hole);
        }
        for (Creature creature : creatures) {
            creaturesAndHoles.add(creature);
        }

        if (!gameIsOver()) {
            if (ldr.isActive()) {
                ldr.setActive(false);
                resistence.setActive(true);
            } else {
                ldr.setActive(true);
                resistence.setActive(false);
            }
        }
    }



    public void toggleAI(boolean active) {
        gameRules.setaIActive(active);
    }




    private void treatsPointsTreasures() {
        int resistenceTreasures = 0;
        int ldrTreasures = 0;


        for (Creature creature : creatures) {
            for (Treasure treasure : treasures) {
                if (treasure.thereIsTreasure(creature.getX(), creature.getY())) {
                    creature.setTreasurePoints(treasure.getValue());

                    if (creature.getTeamID() == 20) {
                        resistence.setPoints(treasure.getValue());
                        resistenceTreasures++;

                    }
                    if (creature.getTeamID() == 10) {
                        ldr.setPoints(treasure.getValue());
                        ldrTreasures++;
                    }
                    gameRules.resetTurnsWithoutTreasures();
                    treasures.remove(treasure);
                    break;
                }
            }

        }

        if (resistenceTreasures > 0) {
            resistence.addCoins(2);
        } else {
            resistence.addCoins(1);
        }

        if (ldrTreasures > 0) {
            ldr.addCoins(2);
        } else {
            ldr.addCoins(1);
        }


    }


    public boolean enchant(int x, int y, String spell) {
        if (spell == null) {
            return false;
        }
        String spells[][] = getSpellTypes();
        int spellValue = 0;


        for (int i = 0; i < spells.length; i++) {
            if (spells[i][0].equals(spell)) {
                spellValue = Integer.parseInt(spells[i][2]);
            }
        }


        if (ldr.isActive()) {
            if (ldr.getCoins() - spellValue >= 0) {
                for (Creature creature : creatures) {
                    if (creature.freeSquare(x, y)) {
                        if (creature.enchant(spell, creaturesAndHoles,gameRules, treasures)) {
                            ldr.paySpell(spellValue);
                            return true;
                        }
                    }
                }
            }
        }

        if (resistence.isActive()) {
            if (resistence.getCoins() - spellValue >= 0) {
                for (Creature creature : creatures) {
                    if (creature.freeSquare(x, y)) {
                        if (creature.enchant(spell, creaturesAndHoles,gameRules, treasures)) {
                            resistence.paySpell(spellValue);
                            return true;
                        }
                    }
                }
            }
        }



        return false;
    }

    public String[][] getCreatureTypes() {
        String creatureTypes[][] = new String[6][4];
        creatureTypes[0][0] = "Druida";
        creatureTypes[0][1] = "Druida.png";
        creatureTypes[0][2] = "Move-se na horizontal e na vertical";
        creatureTypes[0][3] = "4";

        creatureTypes[1][0] = "Anão";
        creatureTypes[1][1] = "Anão.png";
        creatureTypes[1][2] = "Move-se uma posição na horizontal e na vertical";
        creatureTypes[1][3] = "1";

        creatureTypes[2][0] = "Humano";
        creatureTypes[2][1] = "Humano.png";
        creatureTypes[2][2] = "Move-se duas posições na horizontal e na vertical";
        creatureTypes[2][3] = "3";

        creatureTypes[3][0] = "Dragão";
        creatureTypes[3][1] = "Dragão.png";
        creatureTypes[3][2] = "Move-se três posições na horizontal ,na vertical e diagonais, é ainda capaz de voar, por isso pode saltar no máximo dois boracos e/ou criaturas consecutivos";
        creatureTypes[3][3] = "9";

        creatureTypes[5][0] = "Gigante";
        creatureTypes[5][1] = "Gigante.png";
        creatureTypes[5][2] = "Move-se três posições na horizontal ou vertical, pode passar por cima de 2 buracos ou personagens consecutivos(a menos que sejam gigantes)";
        creatureTypes[5][3] = "5";

        creatureTypes[4][0] = "Elfo";
        creatureTypes[4][1] = "Elfo.png";
        creatureTypes[4][2] = "Move-se duas posições na horizontal ,na vertical e diagonais, ele é muito ágil por isso pode saltar por cima de um buraco";
        creatureTypes[4][3] = "5";

        return creatureTypes;
    }

    public Map createComputerArmy() {
        Map<String, Integer> computerArmy = new HashMap<String, Integer>();
        Random random = new Random();

        while (computerArmy.size() ==0){
            int quantity = random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Anão", quantity);
            }
            quantity= random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Dragão", quantity);
            }
            quantity= random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Druida", quantity);
            }
            quantity= random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Elfo", quantity);
            }
            quantity= random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Humano", quantity);
            }
            quantity= random.nextInt(2);
            if (quantity > 0) {
                computerArmy.put("Gigante", quantity);
            }

        }


        return computerArmy;
    }

    public List<String> getAuthors() {
        List<String> authors = new ArrayList<>();
        return authors;
    }

    public Map<String, List<String>> getStatistics() {
        Stream stream = new Stream();
        Map<String, List<String>> mapa = new HashMap<>();
        mapa.put("as3MaisCarregadas", stream.tresMaisCarregadas(creatures));
        mapa.put("as3MaisViajadas", stream.tresMaisViajadas(creatures));
        mapa.put("as5MaisRicas", stream.cincoMaisRicas(creatures));
        mapa.put("tiposDeCriaturaESeusTesouros", stream.tiposDeCriaturaESeusTesouros(creatures));
        mapa.put("tiposComTesouros", stream.tiposComTesouros(creatures));
        mapa.put("osAlvosFavoritos", stream.alvosFavoritos(creatures));
        mapa.put("viradosPara", stream.viradosPara(creatures));
        mapa.put("asMaisEficientes", stream.asMaisEficientes(creatures));
        return mapa;
    }



    public void startGame(String[] content, int rows, int columns) throws InsufficientCoinsException {
        gameRules = new GameRules(rows,columns);
        treasures = new ArrayList<>();
        creatures = new ArrayList<>();
        holes = new ArrayList<>();

        ldr = new LDR(10);
        resistence = new Resistence(20);

        for (String contentLine : content) {
            String contentSplit[] = contentLine.split(",");
            String id = contentSplit[0].replace("id:", "").trim();
            String type = contentSplit[1].replace("type:", "").trim();
            String x ="";
            String y ="";
            if (Integer.parseInt(id) > 0) {
                String teamId = contentSplit[2].replace("teamId:", "").trim();
                String orientation = contentSplit[5].replace("orientation:", "").trim();
                x = contentSplit[3].replace("x:", "").trim();
                y = contentSplit[4].replace("y:", "").trim();

                switch (type){
                    case "Elfo":
                        creatures.add(new Elfo(Integer.parseInt(id), type,orientation, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)  ));
                        break;
                    case "Anão":
                        creatures.add(new Anao(Integer.parseInt(id), type,orientation,  Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "Humano":
                        creatures.add(new Humano(Integer.parseInt(id), type,orientation,  Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "Dragão":
                        creatures.add(new Dragao(Integer.parseInt(id), type,orientation,  Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "Druida":
                        creatures.add(new Druida(Integer.parseInt(id), type,orientation,  Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "Gigante":
                        creatures.add(new Gigante(Integer.parseInt(id), type,orientation,  Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                }

            } else {
                y = contentSplit[3].replace("y:", "").trim();
                x = contentSplit[2].replace("x:", "").trim();
                switch (type){
                    case "hole":
                        holes.add(new Hole(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "gold":
                        treasures.add(new Gold(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "bronze":
                        treasures.add(new Bronze(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                    case "silver":
                        treasures.add(new Silver(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                        break;
                }

            }
        }

        for (Creature creature : creatures) {
            if (creature.getTeamID() == 20) {
                resistence.payCreature(creature.getCusto());
            } else {
                ldr.payCreature(creature.getCusto());
            }
        }

        if (!resistence.passedPlafond() || !ldr.passedPlafond() || !resistence.passedPlafond() && !ldr.passedPlafond()) {
            int costLDR =0;
            int resistenceCost =0;
            for (Creature creature : creatures) {
                if(creature.getTeamID() == 10){
                    costLDR += creature.getCusto();
                }else{
                    resistenceCost += creature.getCusto();
                }
            }
            throw new InsufficientCoinsException(costLDR, resistenceCost);
        }

        creaturesAndHoles = new ArrayList<>();
        for (Hole hole : holes) {
            creaturesAndHoles.add(hole);
        }
        for (Creature creature : creatures) {
            creaturesAndHoles.add(creature);
        }

    }

    public boolean gameIsOver() {

        int diff =  resistence.getPoints() - ldr.getPoints();
        if(diff < 0){
            diff = diff * -1;
        }
        int possiblePoints = 0;
        for (Treasure treasure : treasures) {
            possiblePoints += treasure.getValue();
        }


        if (treasures.size() == 0 || gameRules.getTurnsWithoutTreasures() >= 15||diff > possiblePoints) {
            return true;
        }


        return false;
    }




    public void setInitialTeam(int teamID) {
        if (teamID == 20) {
            resistence.setActive(true);
            ldr.setActive(false);

        } else {
            resistence.setActive(false);
            ldr.setActive(true);

        }
    }

    public int getCurrentTeamId() {
        if (resistence.isActive()) {
            return resistence.getId();
        }else{
            return ldr.getId();
        }
    }


    public int getCoinTotal(int teamID) {
        if (teamID == 10) {
            return ldr.getCoins();
        } else {
            return resistence.getCoins();
        }
    }

    public String whoIsLordEder() {
        return "Ederzito António Macedo Lopes";
    }

    public int getElementId(int x, int y) {
        for (Hole hole : holes) {
            if (hole.freeSquare(x, y)) {
                return hole.getId();
            }
        }
        for (Creature creature : creatures) {
            if (creature.freeSquare(x, y)) {
                return creature.getId();
            }
        }
        for (Treasure treasure : treasures) {
            if (treasure.freeSquare(x, y)) {
                return treasure.getId();
            }
        }

        return 0;
    }

    public List<Creature> getCreatures() {
        Collections.sort(creatures);
        return creatures;
    }

    public String getSpell(int x, int y) {
        for (Creature c : creatures) {
            if (c.freeSquare(x, y)) {
                return c.getEnchantment();
            }
        }
        return null;
    }

    public int getCurrentScore(int teamID) {
        if(teamID == 10){
            return  ldr.getPoints();
        }else{
            return resistence.getPoints();
        }
    }

    public List<String> getResults() {
        List<String> resultList = new ArrayList<>();
        resultList.add("Welcome to FANDEISIA");
        if (ldr.getPoints() > resistence.getPoints()) {
            resultList.add("Resultado: Vitória da equipa LDR");
            resultList.add("LDR: " + ldr.getPoints());
            resultList.add("RESISTENCIA: " + resistence.getPoints());
        } else if (ldr.getPoints() < resistence.getPoints()) {
            resultList.add("Resultado: Vitória da equipa RESISTENCIA");
            resultList.add("RESISTENCIA: " + resistence.getPoints());
            resultList.add("LDR: " + ldr.getPoints());
        } else {
            resultList.add("Resultado: EMPATE");
            resultList.add("LDR: " + ldr.getPoints());
            resultList.add("RESISTENCIA: " + resistence.getPoints());
        }
        resultList.add("Nr. de Turnos jogados: " + gameRules.getTurns());
        resultList.add("-----");
        Collections.sort(creatures);
        for (Creature creature : creatures) {
            resultList.add(creature.getId() + " : " + creature.getTipo() + " : " + creature.getGold() + " : " + creature.getSilver() + " : " + creature.getBronze() + " : " + creature.getPontos());
        }
        return resultList;
    }
}
