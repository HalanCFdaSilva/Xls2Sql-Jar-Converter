package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.TipoDadosSql;

public class TipoDados {



    private TipoDadosSql tipo;
    private int numeroElementos;

    public TipoDados(String celulaSegundaLinhaExcel,int colunaexcel) {


        if (celulaSegundaLinhaExcel.contains("(")){

            int numeroInicioColchetes = celulaSegundaLinhaExcel.indexOf("(");
            String numeroElementosString = celulaSegundaLinhaExcel.substring(numeroInicioColchetes + 1,celulaSegundaLinhaExcel.indexOf(")"));

            this.numeroElementos = Integer.parseInt(numeroElementosString);
            celulaSegundaLinhaExcel = celulaSegundaLinhaExcel.substring(0,numeroInicioColchetes);


        }else {
            numeroElementos = 0;
        }

        tipo = TipoDadosSql.checarDados(celulaSegundaLinhaExcel,colunaexcel);



    }



    public TipoDadosSql getTipo() {
        return tipo;
    }

    public String getNumeroElementosString() {
        return "(" + numeroElementos + ")";
    }

    public Integer getNumeroElementosInteger() {
        return this.numeroElementos;
    }


    public boolean contemNumeroElementos() {

        if (numeroElementos != 0){
            return true;
        }
        return false;
    }
}
