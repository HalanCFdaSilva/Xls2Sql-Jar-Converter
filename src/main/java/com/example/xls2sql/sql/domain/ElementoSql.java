package com.example.xls2sql.sql.domain;

import com.example.xls2sql.coletor.domain.TipoDados;
import com.example.xls2sql.sql.CelulaElementoSqlService;
import com.example.xls2sql.sql.exceptions.CelulaComElementosNaoConversiveisException;
import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;

import java.util.ArrayList;

public class ElementoSql {

    private ArrayList<String> celula;
    private TipoDados tipoDados;
    private final int linha;
    private final int coluna;

    public ElementoSql(int linha,int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void adicionarCelula(ArrayList<String> listaCelulaAdicionar, Coluna coluna) {
        this.tipoDados = coluna.getTipo();
        this.setCelula(listaCelulaAdicionar);
    }

    public ArrayList<String> getCelula() {
        return celula;
    }

    public TipoDados getTipoDados() {
        return tipoDados;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setCelula(ArrayList<String> listaCelulaAdicionar) {



        if(this.getTipoDados() != null) {

            CelulaElementoSqlService service = new CelulaElementoSqlService(this.getTipoDados());

            listaCelulaAdicionar.set(0, service.formatarCelulaDateTime(listaCelulaAdicionar));

            if (service.checarCelulaAIncluir(listaCelulaAdicionar)) {

                this.celula = listaCelulaAdicionar;

            } else {
                throw new CelulaComElementosNaoConversiveisException(this.getLinha(), this.getColuna());

            }
        }else {
            throw new TipoDadoSqlNaoEncontradoException(this.getLinha(),this.getColuna());
        }




    }
}
