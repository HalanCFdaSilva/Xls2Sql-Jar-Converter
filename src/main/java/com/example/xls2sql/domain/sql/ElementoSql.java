package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.CelulaExcelComTamanhoMaiorQueOPermitidoColunaException;

public class ElementoSql {

    private String celula;
    private TipoDados tipoDados;
    private final int linha;

    public ElementoSql(int linha) {
        this.linha = linha;
    }

    public void adicionarCelula(String celula, Coluna coluna) throws CelulaExcelComTamanhoMaiorQueOPermitidoColunaException {
        this.tipoDados = coluna.getTipo();

        if (tipoDados.contemNumeroElementos()) {
            if (celula.length() <= this.tipoDados.getNumeroElementos()) {

                this.celula = celula;
            }else{
                throw new CelulaExcelComTamanhoMaiorQueOPermitidoColunaException(linha, this.tipoDados.getColuna());
            }

        }else {

            this.celula = celula;
        }

        if(!this.tipoDados.getTipo().verificarCelula(this)){
            throw new CelulaComElementosNaoConversiveisException(this.linha, this.tipoDados.getColuna());
        }
    }

    public String getCelula() {
        return celula;
    }

    public TipoDados getTipoDados() {
        return tipoDados;
    }

    public int getLinha() {
        return linha;
    }

    public void setCelula(String celula) {
        if (tipoDados != null){
            if (tipoDados.contemNumeroElementos()) {
                if (celula.length() <= this.tipoDados.getNumeroElementos()) {

                    this.celula = celula;
                }else{
                    throw new CelulaExcelComTamanhoMaiorQueOPermitidoColunaException(linha, this.tipoDados.getColuna());
                }

            }else {

                this.celula = celula;
            }

            if(!this.tipoDados.getTipo().verificarCelula(this)){
                throw new CelulaComElementosNaoConversiveisException(this.linha, this.tipoDados.getColuna());
            }
        }
    }
}
