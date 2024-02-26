package com.example.xls2sql.sql.exceptions;

import com.example.xls2sql.xls.LeitorXls;

/**Exceção que é dada quando não se é possível encontrar o Tipo de dados da coluna sql no texto da coluna,
 * ou quando uma das células da linha não tem esses dados.*/
public class TipoDadoSqlNaoEncontradoException extends RuntimeException {

    /**Método chamador da exceção que é utilizado quando a classe coluna não consegue achar o tipo de dados
     * da coluna sql pela String do xls/xlsx.
     * <p>O método dá a localização exata da célula no arquivo xls/xlsx onde houve a exceção.</p>
     * @param colunaExcel posição no eixo x da célula no arquivo xls/xlsx.
     * @see com.example.xls2sql.sql.domain.Coluna
     * @see com.example.xls2sql.sql.factorys.TipoDadosFactory*/
    public TipoDadoSqlNaoEncontradoException(int colunaExcel) {
        super("O tipo de dados especificado na coluna " + (colunaExcel+1) + " não foi encontrado.");
    }

    /**Método chamador da exceção que é utilizado quando se é chamado o método setCelula da classe
     * {@link com.example.xls2sql.sql.domain.CelulaLinhaSql CelulaLinhaSql} sem ela ter a classe TipoDados armazenada na
     * variável tipoDados herdada de AcondicionadoresTipoDados.
     * <p>O método dá a localização exata da célula no arquivo xls/xlsx onde houve a exceção.</p>
     * @param colunaExcel posição no eixo x da célula no arquivo xls/xlsx.
     * @param linhaExcel  posição no eixo y da célula no arquivo xls/xlsx.
     * @see com.example.xls2sql.sql.domain.CelulaLinhaSql
     * @see com.example.xls2sql.sql.domain.TipoDados
     * @see com.example.xls2sql.sql.domain.AcondicionadoresTipoDados*/
    public TipoDadoSqlNaoEncontradoException(int linhaExcel, int colunaExcel) {
        super("Não foi possível encontrar o tipo de dado da celula na linha " + linhaExcel + " e coluna " + colunaExcel + ".");
    }
    
}
