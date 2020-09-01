package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;
import java.util.List;

public class Gigante extends Creature {
    String image;
    String type = "Gigante";
    String description;
    int cost;
    int i;
    boolean freezeTemp;
    boolean freezePerm = false;
    boolean temGig;
    Creature[][] mapGig;


    public Gigante(int id, String type, int teamId, int x, int y, String orientation) {
        super(id, type, teamId, x, y, orientation);
        image = "giant.png";
        description = "Pode-se mover três posições na horizontal e na vertical, podendo passar por cima de 2 buracos ou personagens consecutivos(a menos que sejam gigantes)";
        cost = 5;
        mov = 3;
    }

    @Override
    public String getType() {
        return "Gigante";
    }


    @Override
    public Object[] changePosition(int rows, int columns, Creature[][] map, List<Hole> holes, Creature[][] mapGig, ArrayList<Creature> creatures) {
        this.mapGig = mapGig;
        if (mov != 0) {
            superBreak = false;
            temGig = false;
            switch (orientation) {
                case "Este":
                    for (Hole h : holes) {
                        if (this.x + mov == h.getX() && this.y == h.getY()) {
                            orientation = "Sul";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (x >= columns - mov) {
                            orientation = "Sul";
                        } else {

                            if (map[x + mov][y] == null) {
                                for (i = 1; i < mov; i++) {
                                    if (mapGig[x + i][y] != null) {
                                        orientation = "Sul";
                                        temGig = true;
                                        break;
                                    }
                                }
                                if (!temGig) {
                                    map [x+mov][y] = map[x][y];
                                    mapGig [x+mov][y] = mapGig[x][y];
                                    map[x][y] = null;
                                    mapGig[x][y] = null;
                                    kmsTravelled += mov;
                                    x += mov;
                                }
                            } else {
                                orientation = "Sul";
                            }
                        }
                    }
                    break;
                case "Oeste":
                    for (Hole h : holes) {
                        if (this.x - mov == h.getX() && this.y == h.getY()) {
                            orientation = "Norte";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (x < mov) {
                            orientation = "Norte";

                        } else {
                            if (map[x - mov][y] == null) {
                                for (i = 1; i < mov; i++) {
                                    if (mapGig[x - i][y] != null) {
                                        orientation = "Norte";
                                        temGig = true;
                                        break;
                                    }
                                }
                                if (!temGig) {
                                    map [x-mov][y] = map[x][y];
                                    mapGig [x-mov][y] = mapGig[x][y];
                                    map[x][y] = null;
                                    mapGig[x][y] = null;
                                    kmsTravelled += mov;
                                    x -= mov;
                                }
                            } else {
                                orientation = "Norte";
                            }
                        }
                    }
                    break;
                case "Norte":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y - mov == h.getY()) {
                            orientation = "Este";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y < mov) {
                            orientation = "Este";
                        } else {
                            if (map[x][y - mov] == null) {
                                for (i = 1; i < mov; i++) {
                                    if (mapGig[x][y - i] != null) {
                                        orientation = "Este";
                                        temGig = true;
                                        break;
                                    }
                                }
                                if (!temGig) {
                                    map [x][y-mov] = map[x][y];
                                    mapGig [x][y-mov] = mapGig[x][y];
                                    map[x][y] = null;
                                    mapGig[x][y] = null;
                                    kmsTravelled += mov;
                                    y -= mov;
                                }
                            } else {
                                orientation = "Este";
                            }
                        }
                    }
                    break;
                case "Sul":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y + mov == h.getY()) {
                            orientation = "Oeste";
                            superBreak = true;
                            break;
                        }
                    }
                    if (!superBreak) {
                        if (y >= rows - mov) {
                            orientation = "Oeste";
                        } else {
                            if (map[x][y + mov] == null) {
                                for (i = 1; i < mov; i++) {
                                    if (mapGig[x][y + i] != null) {
                                        orientation = "Oeste";
                                        temGig = true;
                                        break;
                                    }
                                }
                                if (!temGig) {
                                    map [x][y+mov] = map[x][y];
                                    mapGig [x][y+mov] = mapGig[x][y];
                                    map[x][y] = null;
                                    mapGig[x][y] = null;
                                    kmsTravelled += mov;
                                    y += mov;
                                }
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
        if (spellName.equals("Congela4Ever")) {
            spellCost = 10;
            if ((currentTeam == 10 && plafondP - spellCost <= 0) || (currentTeam == 20 && plafondAI - spellCost <= 0)) {
                return false;
            }
            mov = 0;
            freezePerm = true;
            return true;
        }

        if (spellName.equals("Congela")) {
            spellCost = 3;
            if ((currentTeam == 10 && plafondP - spellCost <= 0) || (currentTeam == 20 && plafondAI - spellCost <= 0)) {
                return false;
            }
            if (!freezePerm) {
                freezeTemp = true;
            }
            mov = 0;
            return true;
        }
        if (spellName.equals("Descongela")) {
            spellCost = 8;
            if ((currentTeam == 10 && plafondP - spellCost <= 0) || (currentTeam == 20 && plafondAI - spellCost <= 0)) {
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
                    if (x >= columns - mov * 2) {
                        this.spellName = null;
                        return false;
                    } else {

                        if (map[x + mov * 2][y] == null) {
                            for (i = 1; i < mov*2; i++) {
                                if (mapGig[x+i][y] != null) {
                                    this.spellName = null;
                                    return false;
                                }
                            }
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
                        if (this.x - mov * 2 == h.getX() && this.y == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (x < mov * 2) {
                        this.spellName = null;
                        return false;

                    } else {
                        if (map[x - mov * 2][y] == null) {
                            for (i = 1; i < mov*2; i++) {
                                if (mapGig[x - i][y] != null) {
                                    this.spellName = null;
                                    return false;
                                }
                            }
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;
                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Norte":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y - mov * 2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y < mov * 2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y - mov * 2] == null) {
                            for (i = 1; i < mov*2; i++) {
                                if (mapGig[x][y - i] != null) {
                                    this.spellName = null;
                                    return false;
                                }
                            }
                            if (mov != 0) {
                                mov = mov * 2;
                            }
                            return true;

                        } else {
                            this.spellName = null;
                            return false;
                        }
                    }
                case "Sul":
                    for (Hole h : holes) {
                        if (this.x == h.getX() && this.y + mov * 2 == h.getY()) {
                            this.spellName = null;
                            return false;
                        }
                    }
                    if (y >= rows - mov * 2) {
                        this.spellName = null;
                        return false;
                    } else {
                        if (map[x][y + mov * 2] == null) {
                            for (i = 1; i < mov*2; i++) {
                                if (mapGig[x][y + i] != null) {
                                    this.spellName = null;
                                    return false;
                                }
                            }
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

        }return false;
    }



    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
