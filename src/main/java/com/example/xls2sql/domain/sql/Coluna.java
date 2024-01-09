package com.example.xls2sql.domain.sql;

import com.example.xls2sql.sql.exceptions.TextoColunaVaziaException;

public class Coluna {


    private String nome;

     private TipoDados tipo;

    public String getNome() {
        return nome;
    }

    public TipoDados getTipo() {
        return tipo;
    }

    public void adicionar(String textoCelulaExcel, int colunaExcel){

        if (textoCelulaExcel.length() != 0 && textoCelulaExcel.contains("[")){
            int numeroInicioColchetes = textoCelulaExcel.indexOf("[");
            int numeroFimColchetes = textoCelulaExcel.indexOf("]");
            String textoSemTipoDado = textoCelulaExcel.substring(0,numeroInicioColchetes)+textoCelulaExcel.substring(numeroFimColchetes+1);
            tipo = new TipoDados(textoCelulaExcel.substring(numeroInicioColchetes + 1,numeroFimColchetes),colunaExcel);

            if (textoSemTipoDado.contains("(")){
                int numeroInicioParenteses = textoSemTipoDado.indexOf("(");
                this.nome = textoSemTipoDado.substring(0,numeroInicioParenteses);

            }else{
                this.nome = textoSemTipoDado;
            }
        }else {
            if (textoCelulaExcel.length() == 0){
                throw new TextoColunaVaziaException(colunaExcel);
            }

        }



    }





}
