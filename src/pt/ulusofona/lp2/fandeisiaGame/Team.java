package pt.ulusofona.lp2.fandeisiaGame;

public class Team {
    private int id;
    private int moedas;
    private int pontos;
    private boolean estadoAtivo;

    public Team(int id) {
        this.id = id;
        moedas = 50;
        pontos = 0;
        estadoAtivo = false;
    }

    public void pagaValorDaCriatura(int custo) {
        moedas -= custo;
    }

    public boolean ultrapassouPlafond() {
        return moedas < 0 ? true : false;
    }

    public int getMoedas() {
        return moedas;
    }

    public int pontuacao() {
        return pontos;
    }

    public void setEstadoAtivo() {
        estadoAtivo = true;
    }

    public void setEstadoInativo() {
        estadoAtivo = false;
    }

    public boolean getEstado() {
        return estadoAtivo;
    }

    public int getId() {
        return id;
    }

    public void setPontuacao(int pontos) {
        this.pontos += pontos;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public void adicionaMoedas(int moedas) {
        this.moedas += moedas;
    }

    public boolean podePagarFeitico(int precoFeitico) {
        return moedas - precoFeitico < 0 ? false : true;
    }

    public void pagaFeitico(int precoFeitico) {
        moedas = moedas - precoFeitico;
    }


}
