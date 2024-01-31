package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlFactory;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;

import java.util.HashSet;
import java.util.Set;

public class TipoDados {



    private final TipoDadosSql tipo;
    private double numeroElementos;
    private final int coluna;

    public TipoDados(String celulaSegundaLinhaExcel,int colunaexcel) {

        String numeroElementosString = null;

        if (celulaSegundaLinhaExcel.contains("(")){

            int indexAbreColchete = celulaSegundaLinhaExcel.indexOf("(");
            int indexFechaColchete = celulaSegundaLinhaExcel.indexOf(")");
            numeroElementosString = celulaSegundaLinhaExcel.substring(indexAbreColchete + 1,indexFechaColchete);
            celulaSegundaLinhaExcel = celulaSegundaLinhaExcel.substring(0,indexAbreColchete);
        }

        TipoDadosSqlFactory factoryTipoDadosSql = new TipoDadosSqlFactory();
        tipo = factoryTipoDadosSql.generate(celulaSegundaLinhaExcel,colunaexcel);

        if (numeroElementosString != null && this.tipo.aceitaNumeroElementos()){
            this.numeroElementos = Double.parseDouble(numeroElementosString);

        }else if (numeroElementosString == null){
            this.numeroElementos = 0;
        }

        this.coluna = colunaexcel;



    }



    public TipoDadosSql getTipo() {
        return tipo;
    }

    public String getNumeroElementosString() {
        String texto = Double.toString(numeroElementos);
        int posicaoVirgula = texto.indexOf(".");
        String textoPosvirgula = texto.substring(posicaoVirgula + 1);
        if (textoPosvirgula.matches("^0$")){
            String textoAntesVirgula = texto.substring(0,posicaoVirgula);
            return "(" + textoAntesVirgula + ")";
        }else {
            return "(" + texto + ")";
        }

    }

    public double getNumeroElementos() {
        return this.numeroElementos;
    }

    public boolean contemNumeroElementos() {
        if (numeroElementos != 0){
            return true;
        }
        return false;
    }

    public int getColuna() {
        return coluna;
    }
}
