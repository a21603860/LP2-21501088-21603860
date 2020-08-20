package pt.ulusofona.lp2.fandeisiaGame;


import java.util.ArrayList;
import java.util.List;

public class Dragao extends Creature {
    String image;
    String type = "Dragao";
    String description;
    int cost;
    boolean freezeTemp;
    boolean freezePerm = false;





    public Dragao(int id, String type, int teamId, int x, int y, String orientation) {
        super(id, type, teamId, x, y, orientation);
        image = "dragao.png";
        description = "Pode-se mover três posições na horizontal ,na vertical e também nas diagonais, é ainda capaz de voar, por isso pode saltar no máximo dois boracos e/ou criaturas consecutivos";
        cost = 9;
        mov = 3;
    }
    @Override
    public String getType() {
        return "Dragão";
    }

    @Override
    public Object[] changePosition(int rows, int columns, Creature[][] map, List<Hole> holes, Creature[][] mapGig, ArrayList<Creature> creatures) {
        if (mov != 0) {
            superBreak = false;
            switch (orientation) {

                case "Este":
                    for (Hole h : holes) {
                        if (this.x + mov == h.getX() && this.y == h.getY()) {
                            orientation = "Sudeste";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (x >= columns - mov) {
                            orientation = "Sudeste";
                        } else {
                            if (map[x + mov][y] == null) {
                                map [x+mov][y] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x += mov;
                            } else {
                                orientation = "Sudeste";
                            }
                        }
                    }
                    break;
                case "Oeste":
                    for (Hole h : holes) {
                        if (this.x - mov == h.getX() && this.y == h.getY()) {
                            orientation = "Noroeste";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (x < mov) {
                            orientation = "Noroeste";

                        } else {
                            if (map[x - mov][y] == null) {
                                map [x-mov][y] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x -= mov;
                            } else {
                                orientation = "Noroeste";
                            }
                        }
                    }
                    break;
                case "Norte":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y - mov == h.getY()) {
                            orientation = "Nordeste";
                            superBreak = true;
                        }
                    }
                    if (!superBreak) {
                        if (y < mov) {
                            orientation = "Nordeste";
                        } else {
                            if (map[x][y - mov] == null) {
                                map [x][y-mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                y -= mov;
                            } else {
                                orientation = "Nordeste";
                            }
                        }
                    }
                    break;
                case "Sul":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y + mov == h.getY()) {
                            orientation = "Sudoeste";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y >= rows - mov) {
                            orientation = "Sudoeste";
                        } else {
                            if (map[x][y + mov] == null) {
                                map [x][y+mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                y += mov;
                            } else {
                                orientation = "Sudoeste";
                            }
                        }
                    }
                    break;
                case "Noroeste":
                    for (Hole h : holes) {
                        if (this.x - mov == h.getX() && this.y - mov == h.getY()) {
                            orientation = "Norte";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y < mov || x < mov) {
                            orientation = "Norte";
                        } else {
                            if (map[x - mov][y - mov] == null) {
                                map [x-mov][y-mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x -= mov;
                                y -= mov;
                            } else {
                                orientation = "Norte";
                            }
                        }
                    }
                    break;
                case "Nordeste":
                    for (Hole h : holes) {
                        if (this.x + mov == h.getX() && this.y - mov == h.getY()) {
                            orientation = "Este";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y < mov || x >= columns - mov) {
                            orientation = "Este";
                        } else {
                            if (map[x + mov][y - mov] == null) {
                                map [x+mov][y-mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x += mov;
                                y -= mov;
                            } else {
                                orientation = "Este";
                            }
                        }
                    }
                    break;
                case "Sudeste":
                    for (Hole h : holes) {
                        if (this.x + mov == h.getX() && this.y + mov == h.getY()) {
                            orientation = "Sul";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y >= rows - mov || x >= columns - mov) {
                            orientation = "Sul";
                        } else {
                            if (map[x + mov][y + mov] == null) {
                                map [x+mov][y+mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x += mov;
                                y += mov;
                            } else {
                                orientation = "Sul";
                            }
                        }
                    }
                    break;
                case "Sudoeste":
                    for (Hole h : holes) {
                        if (this.x - mov == h.getX() && this.y + mov == h.getY()) {
                            orientation = "Sul";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (x < mov || y >= rows - mov) {
                            orientation = "Oeste";
                        } else {
                            if (map[x - mov][y + mov] == null) {
                                map [x-mov][y+mov] = map[x][y];
                                map[x][y] = null;
                                kmsTravelled += mov;
                                x -= mov;
                                y += mov;
                            } else {
                                orientation = "Oeste";
                            }
                        }
                    }
                    break;
            }
        }
        if (freezeTemp) {
            mov = 3;
        }
        if (mov != 0) {
            if (!reduceR || !rangex2) {
                mov = 3;
            }
        }
        return new Object[]{map, creatures};
    }




    @Override
    public boolean congela(String spellName, int currentTeam, int plafondP, int plafondAI) {
        freezeTemp = false;

        if (spellName.equals("Congela4Ever")){
            spellCost = 10;
            if ((currentTeam == 10 && plafondP - spellCost < 0) || (currentTeam == 20 && plafondAI - spellCost < 0)) {
                return false;
            }
            mov=0;
            freezePerm = true;
            return true;
        }

        if (spellName.equals("Congela")){
            spellCost = 3;
            if ((currentTeam == 10 && plafondP - spellCost < 0) || (currentTeam == 20 && plafondAI - spellCost < 0)) {
                return false;
            }
            if (!freezePerm){
                freezeTemp = true;
            }
            mov=0;
            return true;
        }
        if (spellName.equals("Descongela")) {
            spellCost = 8;
            if ((currentTeam == 10 && plafondP - spellCost < 0) || (currentTeam == 20 && plafondAI - spellCost < 0)) {
                return false;
            }
            mov = 3;
            freezePerm = false;
            return true;
        }


        return false;
    }



    @Override
    public boolean alteraAlcance(List<Hole> holes, Creature[][] map, String spellName, int rows, int columns) {
        if (spellName.equals("ReduzAlcance")) {
            switch (orientation) {
                case "Este":
                    for (Hole h : holes) {
                        if (this.x + 1 == h.getX() && this.y == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (x >= columns - 1) {
                        this.spellName = null;
                        return false;
                    } else {

                        if (map[x + 1][y] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Oeste":
                    for (Hole h : holes) {
                        if (this.x - 1 == h.getX() && this.y == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (x < 1) {
                        this.spellName = null;
                        return false;

                    } else {
                        if (map[x - 1][y] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Norte":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y - 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y - 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Sul":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y + 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y >= rows - 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y + 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Noroeste":
                    for (Hole h : holes) {
                        if (this.x - 1 == h.getX() && this.y - 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < 1 || x < 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x - 1][y - 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Nordeste":
                    for (Hole h : holes) {
                        if (this.x + 1 == h.getX() && this.y - 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < 1 || x >= columns - 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x + 1][y - 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }

                case "Sudeste":
                    for (Hole h : holes) {
                        if (this.x + 1 == h.getX() && this.y + 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y >= rows - 1 || x >= columns - 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x + 1][y + 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Sudoeste":
                    for (Hole h : holes) {
                        if (this.x - 1 == h.getX() && this.y + 1 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }

                    if (x < 1 || y >= rows - 1) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x - 1][y + 1] == null) {
                            if (mov != 0) {
                                mov = 1;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
            }
        }
        if (spellName.equals("DuplicaAlcance")) {
            switch (orientation) {
                case "Este":
                    for (Hole h : holes) {
                        if (this.x + mov * 2 == h.getX() && this.y == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (x >= columns - mov*2) {
                        this.spellName = null;
                        return false;
                    } else {

                        if (map[x + mov * 2][y] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Oeste":
                    for (Hole h : holes) {
                        if (this.x - mov*2 == h.getX() && this.y == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (x < mov*2) {
                        this.spellName = null;
                        return false;

                    } else {
                        if (map[x - mov*2][y] == null) {
                            if (mov != 0) {
                                mov = mov*2;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Norte":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y - mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y - mov*2] == null) {
                            if (mov != 0) {
                                mov = mov*2;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Sul":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y + mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y >= rows - mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y + mov * 2] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Noroeste":
                    for (Hole h : holes) {
                        if (this.x - mov*2 == h.getX() && this.y - mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y <= mov*2 || x <= mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x - mov*2][y - mov*2] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Nordeste":
                    for (Hole h : holes) {
                        if (this.x + mov*2 == h.getX() && this.y - mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < mov*2 || x >= columns - mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x + mov*2][y - mov*2] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }

                case "Sudeste":
                    for (Hole h : holes) {
                        if (this.x + mov*2 == h.getX() && this.y + mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y >= rows - mov*2 || x >= columns - mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x + mov*2][y + mov*2] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Sudoeste":
                    for (Hole h : holes) {
                        if (this.x - mov*2 == h.getX() && this.y + mov*2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }

                    if (x < mov*2 || y >= rows - mov*2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x - mov*2][y + mov*2] == null) {
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
            }
        }
        return false;
    }

    @Override
    public String getImagePNG() {
        if (teamId == 0){
            imagem = "bd.png";
        }
        if (teamId == 1){
            imagem = "rd.png";
        }
        return imagem;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
