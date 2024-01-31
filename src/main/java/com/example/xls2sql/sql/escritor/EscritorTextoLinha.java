package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.domain.sql.Coluna;
import com.example.xls2sql.domain.sql.ElementoSql;
import com.example.xls2sql.domain.sql.ElementosSql;

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

            textoElementos += "'" + dadoSql.getCelula() + "'";
            primeiraInteracaoLaco = false;
        }
        textoElementos += ");";
        return  textoElementos;
    }

}
