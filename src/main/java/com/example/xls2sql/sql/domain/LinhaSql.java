package com.example.xls2sql.sql.domain;

import java.util.ArrayList;

/**Classe que agrega todas as CelulaLinhaSql de uma linha do xls/xlsx
 * guardando assim todos os dados de uma linha do sql.
 * @author Halan Silva
 * @see CelulaLinhaSql*/
public class LinhaSql {

    /**Variável que guarda a posição no eixo y da LinhaSql no arquivo xls/xlsx.*/
    private final int linhaXls;

    /**Arraylist que guarda as CelulaLinhaSql.*/
    private final ArrayList<CelulaLinhaSql>  celulasLinha;

    /**Método que gera uma instância da classe já iniciando a variável.
     * @param linhaXls  posição no eixo y da linha no arquivo xls/xlsx.*/
    public LinhaSql(int linhaXls) {
        this.linhaXls = linhaXls;

        this.celulasLinha = new ArrayList<>();
    }


    /** Este método adiciona mais uma CelulaLinhaSql a variável.
     * @param celulaLinhaSql  Recebe a instância da classe {@link CelulaLinhaSql CelulaLinhaSql} que deseja inserir
     * a variável.*/
    public void adicionar(CelulaLinhaSql celulaLinhaSql){
        this.celulasLinha.add(celulaLinhaSql);
    }

    /**Retorna a instância da variável celulasLinha.*/
    public ArrayList<CelulaLinhaSql> getCelulasLinha() {
        return celulasLinha;
    }

    public void incluirNull(ArrayList<Coluna> colunas){


        if (colunas.size() > this.celulasLinha.size()){
            for (int i = 0; i <= colunas.size();i++){

                CelulaLinhaSql celulaAnterior = this.celulasLinha.get(0);
                int posicaoColuna = colunas.get(0).getTipoDados().posicaoColunaXls();
                for (CelulaLinhaSql celula : this.celulasLinha){

                    if (celulaAnterior.getColuna() < posicaoColuna && celula.getColuna() >posicaoColuna){
                        CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(this.linhaXls,posicaoColuna);
                        ArrayList<String> stringNull= new ArrayList<>();
                        stringNull.add("null");
                        celulaLinhaSql.adicionarCelula(stringNull,colunas.get(i).getTipoDados());
                        this.adicionar(celulaLinhaSql);
                    }
                }
            }


        }

    }
}
