package com.example.xls2sql.sql.domain;



/**Classe para representar todas as outras classses que agregam o tipoDados.
 * @see Coluna
 * @see CelulaLinhaSql
 * @author Halan Silva*/
public abstract class AcondicionadoresTipoDados {

    /**Uma instância da classe {@link TipoDados TipoDados}.*/
    private TipoDados tipoDados;



    /**Retorna a instância da variável tipoDados.*/
    public TipoDados getTipoDados() {
        return tipoDados;
    }

    /**Modifica a variável tipoDados*/
    public void setTipoDados(TipoDados tipoDados) {
        this.tipoDados = tipoDados;
    }
}
