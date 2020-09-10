package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Stream {

    public List<String> alvosFavoritos(ArrayList<Creature> creatures) {
        List<String> alvosFavoritos = new ArrayList<>();
        creatures.stream()
                .sorted((s1, s2) -> s2.getFeiticos() - s1.getFeiticos())
                .limit(3)
                .forEach((c) -> alvosFavoritos.add(c.getId() + ":" + c.getTeamID() + ":" + c.getFeiticos()));
        return alvosFavoritos;
    }



    public List<String> viradosPara(ArrayList<Creature> creatures) {

        List<ResultadoStreams> resultadoStreams = new ArrayList<>();

        Long norte= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Norte"))
                .collect(Collectors.counting());
        ResultadoStreams resultado = new ResultadoStreams("Norte", norte);
        resultadoStreams.add(resultado);

        Long sul= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Sul"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Sul", sul);
        resultadoStreams.add(resultado);

        Long este= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Este"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Este", este);
        resultadoStreams.add(resultado);

        Long oeste= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Oeste"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Oeste", oeste);
        resultadoStreams.add(resultado);

        Long nordeste= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Nordeste"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Nordeste", nordeste);
        resultadoStreams.add(resultado);

        Long sudeste= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Sudeste"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Sudeste", sudeste);
        resultadoStreams.add(resultado);

        Long noroeste= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Noroeste"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Noroeste", noroeste);
        resultadoStreams.add(resultado);

        Long sudoeste= creatures.stream()
                .filter((c)->c.getOrientacao().equals("Sudoeste"))
                .collect(Collectors.counting());
        resultado = new ResultadoStreams("Sudoeste", sudoeste);
        resultadoStreams.add(resultado);

        List<String> viradosPara = new ArrayList<>();
        resultadoStreams.stream()
                .sorted((s1, s2) -> s2.getNrCriaturas() - s1.getNrCriaturas())
                .forEach((c) -> viradosPara.add(c.getOrientacao() + ":" + c.getNrCriaturas() ));

        return viradosPara;

    }

    public List<String> asMaisEficientes(ArrayList<Creature> creatures) {
        List<String> maisEficientes = new ArrayList<>();
        creatures.stream()
                .sorted((s1, s2) -> s2.getRacio() - s1.getRacio())
                .limit(3)
                .forEach((c) -> maisEficientes.add(c.getId() + ":" + c.getTesouros() + ":" + c.getPassos()));

        return maisEficientes;
    }


    public List<String> tresMaisCarregadas(ArrayList<Creature> creatures) {
        List<String> tresMaisCarregadas = new ArrayList<>();
        creatures.stream()
                .sorted((s1, s2) -> s2.getTesouros() - s1.getTesouros())
                .limit(3)
                .forEach((c) -> tresMaisCarregadas.add(c.getId() + ":" +  c.getTesouros()));
        return tresMaisCarregadas;
    }

    public List<String> tresMaisViajadas(ArrayList<Creature> creatures) {
        List<String> tresMaisViajadas = new ArrayList<>();
        creatures.stream()
                .sorted((s1, s2) -> s1.getPassos() - s2.getPassos())
                .limit(3)
                .forEach((c) -> tresMaisViajadas.add(c.getId() + ":" + c.getPassos()));
        return tresMaisViajadas;
    }

    public List<String> tiposDeCriaturaESeusTesouros(ArrayList<Creature> creatures) {
        List<ResultadoStreams> tiposdecriaturas = new ArrayList<>();

        int tesourosDruida= creatures.stream()
                .filter((c)->c.getTipo().equals("Druida"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long druidas = creatures.stream()
                .filter((c)->c.getTipo().equals("Druida"))
                .collect(Collectors.counting());

        ResultadoStreams resultado = new ResultadoStreams(druidas, "Druida", tesourosDruida);
        tiposdecriaturas.add(resultado);


        int tesourosDragao= creatures.stream()
                .filter((c)->c.getTipo().equals("Dragão"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long dragao = creatures.stream()
                .filter((c)->c.getTipo().equals("Dragão"))
                .collect(Collectors.counting());

        resultado = new ResultadoStreams(dragao, "Dragão", tesourosDragao);
        tiposdecriaturas.add(resultado);


        int tesourosElfo= creatures.stream()
                .filter((c)->c.getTipo().equals("Elfo"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long elfo = creatures.stream()
                .filter((c)->c.getTipo().equals("Elfo"))
                .collect(Collectors.counting());

        resultado = new ResultadoStreams(elfo, "Elfo", tesourosElfo);
        tiposdecriaturas.add(resultado);


        int tesourosGigante= creatures.stream()
                .filter((c)->c.getTipo().equals("Gigante"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long gigante = creatures.stream()
                .filter((c)->c.getTipo().equals("Gigante"))
                .collect(Collectors.counting());

        resultado = new ResultadoStreams(gigante, "Gigante", tesourosGigante);
        tiposdecriaturas.add(resultado);


        int tesourosHumano= creatures.stream()
                .filter((c)->c.getTipo().equals("Humano"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long humano = creatures.stream()
                .filter((c)->c.getTipo().equals("Humano"))
                .collect(Collectors.counting());

        resultado = new ResultadoStreams(humano, "Humano", tesourosHumano);
        tiposdecriaturas.add(resultado);


        int tesourosAnao= creatures.stream()
                .filter((c)->c.getTipo().equals("Anão"))
                .map((c) ->  c.getTesouros())
                .collect(Collectors.summingInt(Integer::intValue));

        long anao = creatures.stream()
                .filter((c)->c.getTipo().equals("Anão"))
                .collect(Collectors.counting());

        resultado = new ResultadoStreams(anao, "Anão", tesourosAnao);
        tiposdecriaturas.add(resultado);

        List<String>  criaturaseTesouros  =
                tiposdecriaturas.stream()
                        .sorted((s1, s2) -> s2.getTesouros() - s1.getTesouros())
                        .map((c) -> c.getTipo() + ":" + c.getNrCriaturas() + ":" + c.getTesouros())
                        .collect(toList());

        return criaturaseTesouros;
    }


    public List<String> cincoMaisRicas(ArrayList<Creature> creatures) {
        List<String> as5MaisRicas = new ArrayList<>();
        creatures.stream()
                .sorted((s1, s2) -> s2.getPontos() - s1.getPontos())
                .limit(5)
                .map((c) -> c.getId() + ":" + c.getPontos() + ":" + c.getTesouros())
                .collect(toList());


        if (as5MaisRicas.size() < 5) {
            as5MaisRicas =
                    creatures.stream()
                            .sorted((s1, s2) -> s1.getTesouros() - s2.getTesouros())
                            .sorted((s1, s2) -> s2.getPontos() - s1.getPontos())
                            .map((c) -> c.getId() + ":" + c.getPontos() + ":" + c.getTesouros())
                            .collect(toList());
        }


        return as5MaisRicas;
    }
    public List<String> tiposComTesouros( ArrayList<Creature> creatures) {

        return null;
    }
}
