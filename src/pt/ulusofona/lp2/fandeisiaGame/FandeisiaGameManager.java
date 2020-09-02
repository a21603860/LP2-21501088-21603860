package pt.ulusofona.lp2.fandeisiaGame;

import java.util.*;

public class FandeisiaGameManager {

    private int maxTurns = 15;

    // variaveis do jogo
/*    ArrayList<Creature> creatures;
    private ArrayList<Treasure> treasures;
    private ArrayList<Hole> holes;*/
    List<String>resultados = new ArrayList<String>();

    int rows;
    int columns;
    private int currentTeam;
    private int turns = 0;
    private int totalTurns;
    private int remainingPoints;
    int teamIdLDR = 10;
    int teamIdRES = 20;
    private int plafonDPR = 50;
    private int plafondRES = 50;
    private int plafondP = 50;
    private int plafondAI = 50;
    private boolean gotTreasureAI = false;
    private boolean gotTreasureP = false;
    private boolean spellAplied = false;


    protected ArrayList<Treasure> treasures;
    protected ArrayList<Hole> holes;
    protected ArrayList<Creature> creatures;
    protected int turnosSemTesouro;
    protected Team ldr = new LDR(10);
    protected Team resistence = new Resistence(20);
    private ArrayList<ElementoDoJogo> obstaculos;

    private int turnsplayed;
    protected boolean iAAtiva;

    private Creature[][] map;
    private Creature[][] mapGig;


    public FandeisiaGameManager() {
    }



    public String[][] getCreatureTypes(){
        String[][] array2d = new String[5][4];
        array2d[0][0] = "Anão";
        array2d[0][1] = "Anão.png";
        array2d[0][2] = "Move-se uma posição na horizontal e na vertical";
        array2d[0][3] = "1";

        array2d[1][0] = "Dragão";
        array2d[1][1] = "Dragão.png";
        array2d[1][2] = "Move-se três posições na horizontal ,na vertical e diagonais, é ainda capaz de voar, por isso pode saltar no máximo dois boracos e/ou criaturas consecutivos";
        array2d[1][3] = "9";


        array2d[2][0] = "Elfo";
        array2d[2][1] = "Elfo.png";
        array2d[2][2] = "Move-se duas posições na horizontal ,na vertical e diagonais, ele é muito ágil por isso pode saltar por cima de um buraco";
        array2d[2][3] = "5";

        array2d[3][0] = "Humano";
        array2d[3][1] = "Humano.png";
        array2d[3][2] = "Move-se duas posições na horizontal e na vertical";
        array2d[3][3] = "3";

        array2d[4][0] = "Gigante";
        array2d[4][1] = "Gigante.png";
        array2d[4][2] = "Move-se três posições na horizontal ou vertical, pode passar por cima de 2 buracos ou personagens consecutivos(a menos que sejam gigantes)";
        array2d[4][3] = "5";

        return array2d;
    }

    public String[][] getSpellTypes() {
        String spellType[][] = new String[9][3];
        spellType[0][0] = "EmpurraParaNorte";
        spellType[0][1] = "Move a criatura 1 unidade para Norte.";
        spellType[0][2] = "1";
        spellType[1][0] = "EmpurraParaEste";
        spellType[1][1] = "Move a criatura 1 unidade para Este.";
        spellType[1][2] = "1";
        spellType[2][0] = "EmpurraParaSul";
        spellType[2][1] = "Move a criatura 1 unidade para Sul.";
        spellType[2][2] = "1";
        spellType[3][0] = "EmpurraParaOeste";
        spellType[3][1] = "Move a criatura 1 unidade para Oeste.";
        spellType[3][2] = "1";
        spellType[4][0] = "ReduzAlcance";
        spellType[4][1] = "Reduz o alcance da criatura para:MIN (alcance original, 1)";
        spellType[4][2] = "2";
        spellType[5][0] = "DuplicaAlcance";
        spellType[5][1] = "Aumenta o alcance da criatura para o dobro";
        spellType[5][2] = "3";
        spellType[6][0] = "Congela";
        spellType[6][1] = "A criatura alvo não se move neste turno.";
        spellType[6][2] = "3";
        spellType[7][0] = "Congela4Ever";
        spellType[7][1] = "A criatura alvo não se move mais até ao final do jogo.";
        spellType[7][2] = "10";
        spellType[8][0] = "Descongela";
        spellType[8][1] = "Inverte a aplicação de um Feitiço Congela4Ever.";
        spellType[8][2] = "8";


        return spellType;
    }

    public Map createComputerArmy() {
        Map<String, Integer> army = new HashMap<String, Integer>();
        Random random = new Random();
        boolean temExercito = false;
        do {
            int qtdAnoes = random.nextInt(3);
            if (qtdAnoes > 0) {
                army.put("Anão", qtdAnoes);
                temExercito = true;
            }

            int qtdDragoes = random.nextInt(3);
            if (qtdDragoes > 0) {
                army.put("Dragão", qtdDragoes);
                temExercito = true;
            }

            int qtdElfos = random.nextInt(3);
            if (qtdElfos > 0) {
                army.put("Elfo", qtdElfos);
                temExercito = true;
            }

            int qtdGigantes = random.nextInt(3);
            if (qtdGigantes > 0) {
                army.put("Gigante", qtdGigantes);
                temExercito = true;
            }

            int qtdHumanos = random.nextInt(3);
            if (qtdHumanos > 0) {
                army.put("Humano", qtdHumanos);
                temExercito = true;
            }
        } while (!temExercito);


        return army;
    }
    public int  startGame(String[]content, int rows, int columns){
        //tamanho do tabuleiro
        this.columns = columns -1;
        this.rows = rows -1;

        ldr = new LDR(10);
        resistence = new Resistence(20);

        turnsplayed = 0;
        turnosSemTesouro = 0;

        creatures = new ArrayList<>();
        treasures = new ArrayList<>();
        holes = new ArrayList<>();

        for (String linha : content) {
            String wfield[] = linha.split(",");
            String id = wfield[0].replace("id:", "").trim();
            String type = wfield[1].replace("type:", "").trim();

            if (Integer.parseInt(id) < 0) {

                String x = wfield[2].replace("x:", "").trim();
                String y = wfield[3].replace("y:", "").trim();
                if (type.equals("hole")) {
                    holes.add(new Hole(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                }
                 if (type.equals("bronze")) {
                    treasures.add(new Bronze(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                }
                if (type.equals("gold")) {
                    treasures.add(new Gold(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                }
                if (type.equals("silver")) {
                    treasures.add(new Silver(Integer.parseInt(id), Integer.parseInt(x), Integer.parseInt(y)));
                }
            } else {

                String teamId = wfield[2].replace("teamId:", "").trim();
                String x = wfield[3].replace("x:", "").trim();
                String y = wfield[4].replace("y:", "").trim();
                String orientation = wfield[5].replace("orientation:", "").trim();

                if (type.equals("Elfo")) {
                    creatures.add(new Elfo(Integer.parseInt(id), type, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y), orientation));
                }
                if (type.equals("Dragão")) {
                    creatures.add(new Dragao(Integer.parseInt(id), type, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y), orientation));
                }
                if (type.equals("Anão")) {
                    creatures.add(new Anao(Integer.parseInt(id), type, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y), orientation));
                }

                if (type.equals("Gigante")) {
                    creatures.add(new Gigante(Integer.parseInt(id), type, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y), orientation));
                }
                if (type.equals("Humano")) {
                    creatures.add(new Humano(Integer.parseInt(id), type, Integer.parseInt(teamId), Integer.parseInt(x), Integer.parseInt(y), orientation));
                }

            }
        }


   /*     atualizaPlafonds();
        if (resistencia.ultrapassouPlafond()) {
            return 3;
        }
        if (ldr.ultrapassouPlafond()) {
            return 2;
        }
        if (resistencia.ultrapassouPlafond() && ldr.ultrapassouPlafond()) {
            return 1;
        }

        atualizaElementosJogo();*/


        return 0;
    }





    public void setInitialTeam(int teamId){
        if (teamId==10){
           ldr.setEstadoAtivo();
           resistence.setEstadoInativo();
        }else{
            ldr.setEstadoInativo();
            resistence.setEstadoAtivo();
        }


    }

    private String geraFeiticoAleatorio() {
        String spells[][] = getSpellTypes();
        Random random = new Random();
        int idx = random.nextInt(spells.length);
        return spells[idx][0];
    }

    public boolean enchant(int x, int y, String spellName) {
        if (spellName == null) {
            return false;
        }
        if (spellName.trim().equals("")) {
            return false;
        }

        String spells[][] = getSpellTypes();
        int precoFeitico = 0;
        for (int i = 0; i < spells.length; i++) {
            if (spells[i][0].equals(spellName)) {
                precoFeitico = Integer.parseInt(spells[i][2]);
            }
        }

        if (precoFeitico == 0) {
            return false;
        }

        if (ldr.getEstado()) {
            if (ldr.podePagarFeitico(precoFeitico)) {
                for (Creature creature : creatures) {
                    if (creature.estaNestaPosicao(x, y)) {
                        if (creature.encantaCriatura(spellName.trim(), obstaculos)) {
                            ldr.pagaFeitico(precoFeitico);
                            return true;
                        }
                    }
                }
            }

        } else {
            if (resistence.podePagarFeitico(precoFeitico)) {
                for (Creature creature : creatures) {
                    if (creature.estaNestaPosicao(x, y)) {
                        if (creature.encantaCriatura(spellName.trim(), obstaculos)) {
                            resistence.pagaFeitico(precoFeitico);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    private void jogadasDoComputador() {
        Random random = new Random();
        int nrCriaturasEncantar = random.nextInt(creatures.size() / 2);
        for (int i = 0; i < nrCriaturasEncantar; i++) {
            int idCriaturaEncantar = random.nextInt(nrCriaturasEncantar) + 1;
            for (Creature creature : creatures) {
                if (creature.getId() == idCriaturaEncantar) {
                    boolean encanta = enchant(creature.getX(), creature.getY(), geraFeiticoAleatorio());
                }
            }
        }
    }

    public void processTurn(){

        if (resistence.getEstado() && iAAtiva){

        }
        turns++;
        turnosSemTesouro++;

        if (gameIsOver()!= false){
            if (currentTeam == teamIdLDR){
                currentTeam = teamIdRES;
            }
            else{
                currentTeam = teamIdLDR;
            }

        }

    }

    public List<Creature> getCreatures(){

        return creatures;
    }

    public boolean gameIsOver(){

        int pontucao = ldr.pontuacao() - resistence.pontuacao();
        int teasuresPontos=0;

        if (pontucao<0){
            pontucao = pontucao * -1;
        }

        for (Treasure treasure: treasures){
            teasuresPontos += treasure.getPontos();
        }

        if (turnosSemTesouro>=15 || treasures.size() <= 0 || pontucao > teasuresPontos){
            return true;
        }


        return false;
    }

    public List<String> getAuthors(){

        List<String> creditos = new ArrayList<>();
        creditos.add("Clinton Afonso" + "|" + "nº:" + "21603860");
        return creditos;
    }

    public List<String> getResults(){

        return resultados;
    }

    public int getElementId(int x, int y){

        for (Creature creature: creatures){
            if (creature.getX() == x && creature.getY() == y){
                return creature.getId();
            }

        }
        for (Treasure treasure: treasures){
            if (treasure.getX()==x && treasure.getY() == y){
                return treasure.getId();
            }
        }

        for (Hole hole: holes){
            if (hole.getX()==x && hole.getY() == y){
                return hole.getId();
            }
        }
        return 0;
    }

    public int getCurrentTeamId(){

        return 0;
    }

    public int getCurrentScore(int teamID){

        return 0;
    }

    public int getCoinTotal(int teamID){

        if (teamID==10){
            return ldr.getMoedas();
        }
            return resistence.getMoedas();
    }

    public String getSpell(int x, int y){

        return null;
    }

}

