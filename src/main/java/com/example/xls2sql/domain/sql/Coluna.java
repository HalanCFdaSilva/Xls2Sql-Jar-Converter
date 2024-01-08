package com.example.xls2sql.domain.sql;

public class Coluna {


    private String nome;

     private TipoDados tipo;

    public String getNome() {
        return nome;
    }

    public TipoDados getTipo() {
        return tipo;
    }

    public void adicionar(String textoCelulaExcel){


        if (textoCelulaExcel.contains("(")||textoCelulaExcel.contains("[")){
            if (textoCelulaExcel.contains("(")){
                int numeroFimNomeColuna = textoCelulaExcel.indexOf("(");
                this.nome = textoCelulaExcel.substring(0,numeroFimNomeColuna);

            }
            if (textoCelulaExcel.contains("[")){
                int numeroFimNomeColuna = textoCelulaExcel.indexOf("[");
                this.nome = nome.substring(0,numeroFimNomeColuna);

            }
        }else{
            this.nome = textoCelulaExcel;
        }



    }


  public void pegarTipo(String celulaSegundaLinhaExcel, int colunaExcel){
      tipo = new TipoDados(celulaSegundaLinhaExcel,colunaExcel);

  }


}
