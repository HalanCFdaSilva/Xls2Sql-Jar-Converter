package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColuna;

public class ElementoSql {

    private String celula;
    private TipoDados tipoDados;
    private Integer linha;

    public ElementoSql(Integer linha) {
        this.linha = linha;
    }

    public void adicionarCelula(String celula, Coluna coluna) throws CelulaExcelComTamanhoMaiorQueOPermitidoColuna {
        this.tipoDados = coluna.getTipo();

        if (tipoDados.contemNumeroElementos()) {
            if (celula.length() <= this.tipoDados.getNumeroElementosInteger()) {

                this.celula = celula;
            }else{
                throw new CelulaExcelComTamanhoMaiorQueOPermitidoColuna(linha, coluna.getNome());
            }

        }else {

            this.celula = celula;
        }
    }

    public String getCelula() {
        return celula;
    }

    public TipoDados getTipoDados() {
        return tipoDados;
    }
}
