package com.example.xls2sql.sql.escritor;

import com.example.xls2sql.sql.domain.Coluna;
import com.example.xls2sql.sql.domain.ElementoSql;
import com.example.xls2sql.sql.domain.ElementosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlDateTime;

import java.util.ArrayList;


public class EscritorTextoLinha {

    ArrayList<String > textoAEscrever;

    public EscritorTextoLinha() {
        this.textoAEscrever = new ArrayList<>();
    }

    public void textoColunaIncluirLinhas(ArrayList<Coluna> colunas, String nomeTabela){
        textoAEscrever.add("INSERT INTO " + nomeTabela + "(");

        String textoColuna = "";
        boolean primeiraInteracaoLaco = true;
        for (Coluna coluna : colunas) {
            if (!primeiraInteracaoLaco) {
                textoColuna += ",";
            }
            textoColuna += coluna.getNome();
            primeiraInteracaoLaco = false;
        }
        this.textoAEscrever.add(textoColuna);


    }

    public void textoElementosLinha(ElementosSql elementosSql){


        this.textoAEscrever.add(")\nVALUES(");
        boolean primeiraInteracaoLaco = true;
        for (ElementoSql elementoSql : elementosSql.getElementosTabela()) {
            if (!primeiraInteracaoLaco) {
                this.textoAEscrever.add(",");
            }

            if (elementoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSqlNumeric")){
                this.textoAEscrever.add(elementoSql.getCelula().get(0));
            }

            if (elementoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSQLString")){
                ArrayList<String> textoCelula = this.escreverElementoString(elementoSql);
                for (String celula : textoCelula){
                    this.textoAEscrever.add(celula);
                }

            }

            if (elementoSql.getTipoDados().getTipo().getClass().getSimpleName().equals("TipoDadosSqlDateTime")){
                this.textoAEscrever.add(this.escreverElementoDateTime(elementoSql));
            }
            primeiraInteracaoLaco = false;
        }
        this.textoAEscrever.add(");");
    }

    private String escreverElementoDateTime(ElementoSql elementoSql) {
        String textoAEscrever;
        switch ((TipoDadosSqlDateTime)elementoSql.getTipoDados().getTipo()){

            case DATETIME,TIMESTAMP: {
                String date;
                if (elementoSql.getCelula().get(0).contains("/")){
                    date = elementoSql.getCelula().get(0).replace("/","-");
                }else {
                    date = elementoSql.getCelula().get(0);
                }
                textoAEscrever = "'"+ date + "'";
            }
            case DATE: {
                String date;
                if (elementoSql.getCelula().get(0).contains("/")){
                    date = elementoSql.getCelula().get(0).replace("/","-");
                }else {
                    date = elementoSql.getCelula().get(0);
                }
                textoAEscrever = "TO_DATE('"+ date + "', 'yyyy-mm-dd')";
                return textoAEscrever;
            }
            default: {
                textoAEscrever = "'" + elementoSql.getCelula().get(0) + "'";
                return textoAEscrever;
            }
        }
    }

    private ArrayList<String> escreverElementoString(ElementoSql elementoSql){
        ArrayList<String> texto = new ArrayList<>();
        switch ((TipoDadosSQLString)elementoSql.getTipoDados().getTipo()){
            case LONGTEXT : {
                texto.add("'");

                for (String celula : elementoSql.getCelula()){
                    texto.add(celula);
                }

                texto.add("'");
                return texto;

            }

            default: {
                texto.add("'" + elementoSql.getCelula().get(0) + "'");
                return texto;
            }

        }
    }

    public ArrayList<String> getTextoAEscrever() {
        return textoAEscrever;
    }
}
