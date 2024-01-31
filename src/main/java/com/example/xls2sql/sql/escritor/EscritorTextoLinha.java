package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlDateTime;

import java.util.ArrayList;


public class EscritorTextoLinha {


    public String textoColunaIncluirLinhas(ArrayList<Coluna> colunas, String nomeTabela){
        String textoColuna = "INSERT INTO " + nomeTabela + "(";

        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : colunas) {
            if (!primeiraInteracaoLaco) {
                textoColuna += ",";
            }
            textoColuna += coluna.getNome();
            primeiraInteracaoLaco = false;
        }
        return textoColuna;


    }

    public String textoElementosLinha(ElementosSql elementosSql){

        String textoElementos = ")\nVALUES(";
        boolean primeiraInteracaoLaco = true;
        for (ElementoSql dadoSql : elementosSql.getElementosTabela()) {
            if (!primeiraInteracaoLaco) {
                textoElementos += ",";
            }

            if (dadoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSqlNumeric")){
                textoElementos += dadoSql.getCelula();
            }

            if (dadoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSQLString")){
                textoElementos += "'" + dadoSql.getCelula() + "'";
            }

            if (dadoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSqlDateTime")){
                textoElementos += this.escreverElementoDateTime(dadoSql);
            }
            primeiraInteracaoLaco = false;
        }
        textoElementos += ");";
        return  textoElementos;
    }

    private String escreverElementoDateTime(ElementoSql elementoSql) {
        String textoAEscrever;
        switch ((TipoDadosSqlDateTime)elementoSql.getTipoDados().getTipo()){

            case DATETIME,TIMESTAMP: {
                String date;
                if (elementoSql.getCelula().contains("/")){
                    date = elementoSql.getCelula().replace("/","-");
                }else {
                    date = elementoSql.getCelula();
                }
                textoAEscrever = "'"+ date + "'";
            }
            case DATE: {
                String date = elementoSql.getCelula().replace("/","-");
                textoAEscrever = "TO_DATE('"+ date + "', 'yyyy-mm-dd')";
                return textoAEscrever;
            }
            default: {
                textoAEscrever = "'" + elementoSql.getCelula() + "'";
                return textoAEscrever;
            }
        }
    }

}
