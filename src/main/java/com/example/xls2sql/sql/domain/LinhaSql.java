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


    /**
     * Método utilizado para preencher as celulas vazias das colunas, as quais não terão dados inseridos no sql.
     *
     * @param colunas Arraylist com todas as colunas sql presentes no arquivo xls/xlsx para comparar se na posição da coluna
     *                possui alguma celula correspondente.
     */
    public void incluirNull(ArrayList<Coluna> colunas){


        if (colunas.size() > this.celulasLinha.size()){
            for (int i = 0; i < colunas.size();i++){

                CelulaLinhaSql celulaAnterior = this.celulasLinha.get(0);
                int posicaoColuna = colunas.get(i).getTipoDados().posicaoColunaXls();
                boolean celulaNull = false;
                boolean primeiraInteracaoCelula = true;
                for (CelulaLinhaSql celula : this.celulasLinha){

                    if (i+1 == colunas.size()|| primeiraInteracaoCelula){
                        if (celula == celulasLinha.get(celulasLinha.size()-1) && celula.getColuna() < posicaoColuna){
                            celulaNull = true;
                        }
                        if (celula.getColuna() > posicaoColuna && primeiraInteracaoCelula ){
                            celulaNull = true;
                        }
                        primeiraInteracaoCelula = false;
                    }else{
                        if (celulaAnterior.getColuna() < posicaoColuna && celula.getColuna() >posicaoColuna){
                            celulaNull = true;
                        }
                    }

                    celulaAnterior = celula;
                }

                if (celulaNull){

                    CelulaLinhaSql celulaLinhaSql = new CelulaLinhaSql(this.linhaXls,posicaoColuna);
                    ArrayList<String> stringNull= new ArrayList<>();
                    stringNull.add("null");
                    celulaLinhaSql.adicionarCelula(stringNull,colunas.get(i).getTipoDados());
                    this.celulasLinha.add(i,celulaLinhaSql);
                }

            }

        }




    }

    /**Método que retorna uma celulaLinhaSql a partir da sua posição no eixo x do arquivo xls/xlsx.
     * @param posicaoColuna posição no eixo x no arquivo xls/xlsx da celula a ser encontrada.
     * @return Retorna uma instância da classe CelulaLinhaSql referente a celula vinculada
     * a coluna passada naquela linha.
     * @see CelulaLinhaSql*/

    public CelulaLinhaSql getCelula(int posicaoColuna){
        CelulaLinhaSql celulaLinhaSqlARetornar = null;
        for (CelulaLinhaSql celulaLinhaSql : this.getCelulasLinha()){
            if (celulaLinhaSql.getColuna() == posicaoColuna) {
                celulaLinhaSqlARetornar = celulaLinhaSql;
                break;
            }
        }
        return celulaLinhaSqlARetornar;

    }
}
