package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlFactory;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;

import java.util.HashSet;
import java.util.Set;

public class TipoDados {



    private TipoDadosSql tipo;
    private int [] numeroElementos;
    int coluna;

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
        this.numeroElementos = new int[tipo.aceitaNumeroElementos()];


        if (numeroElementosString != null){
            int i = 0;

            while(this.numeroElementos.length >= i){

                this.numeroElementos[i] = 0;
                while ( numeroElementosString.contains(",")){

                    int indexVirgula = numeroElementosString.indexOf(",");
                    String primeiroNumero = numeroElementosString.substring(0, indexVirgula);
                    this.numeroElementos[i] = Integer.parseInt(primeiroNumero);
                    numeroElementosString = numeroElementosString.substring(indexVirgula + 1);

                    i++;
                }

                if(numeroElementosString !=null ){
                    this.numeroElementos[i] = Integer.parseInt(numeroElementosString);
                    numeroElementosString = null;
                }
                i++;
            }


        }



        this.coluna = colunaexcel;



    }



    public TipoDadosSql getTipo() {
        return tipo;
    }

    public String getNumeroElementosString() {
        return "(" + numeroElementos + ")";
    }

    public int getNumeroElementos() {
        if (contemNumeroElementos()){
            return this.numeroElementos[1];
        }
        return 0;
    }

    public int getNumeroElementos(int index){
        return this.numeroElementos[index];

    }


    public boolean contemNumeroElementos() {

        if (numeroElementos.length != 0){
            return true;
        }
        return false;
    }

    public int getColuna() {
        return coluna;
    }
}
