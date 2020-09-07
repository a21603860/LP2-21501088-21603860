package pt.ulusofona.lp2.fandeisiaGame;

public class ResultadoStreams {

    private String orientacao;
    private int nrCriaturas;
    private String tipo;
    private int tesouros;


    public ResultadoStreams(String orientacao, long nrCriaturas) {
        this.orientacao = orientacao;
        this.nrCriaturas = ((int) nrCriaturas);
    }

    public String getOrientacao() {
        return orientacao;
    }

    public int getNrCriaturas() {
        return nrCriaturas;
    }

    public int getTesouros() {
        return tesouros;
    }

    public String getTipo() {
        return tipo;
    }

    public ResultadoStreams(long nrCriaturas, String tipo, int tesouros) {
        this.tipo = tipo;
        this.nrCriaturas = ((int) nrCriaturas);
        this.tesouros = tesouros;

        if (this.nrCriaturas == 0){
            this.tesouros = -1;
        }
    }

}
