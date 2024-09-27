package entidade;

public class FaturamentoDiario {
    public String dia;
    public Double valor;

    public FaturamentoDiario(String dia, Double valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public String getDia() {
        return dia;
    }

    public Double getValor() {
        return valor;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
