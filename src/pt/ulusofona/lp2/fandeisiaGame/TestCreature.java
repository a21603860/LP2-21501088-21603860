package pt.ulusofona.lp2.fandeisiaGame;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCreature {

    @Test
    public void testeAlteracaoDaorientacao(){
        Druida game = new Druida(1,"druida","norte",10,2,1);

        game.orientacao = "sul";

        assertEquals("sul", game.orientacao);
    }



}
