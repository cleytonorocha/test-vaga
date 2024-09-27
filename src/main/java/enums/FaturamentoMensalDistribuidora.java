package enums;

public enum FaturamentoMensalDistribuidora {
    SAO_PAULO("SP", 67836.43),
    RIO_DE_JANEIRO("RJ", 36678.66),
    MINAS_GERAIS("MG", 29229.88),
    ESPIRITO_SANTO("ES", 27165.48),
    OUTROS("Outros", 19849.53);

    private final String sigla;
    private final double faturamento;

    FaturamentoMensalDistribuidora(String sigla, double faturamento) {
        this.sigla = sigla;
        this.faturamento = faturamento;
    }

    public String getSigla() {
        return sigla;
    }

    public double getFaturamento() {
        return faturamento;
    }
}
