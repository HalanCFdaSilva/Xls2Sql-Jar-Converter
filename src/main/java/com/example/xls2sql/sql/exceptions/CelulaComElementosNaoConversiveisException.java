package com.example.xls2sql.sql.exceptions;

/**Exception que é chamada quando uma das células do arquivo xls está fora das regras do sql.
 * @author Halan Silva
 * @see java.lang.RuntimeException */
public class CelulaComElementosNaoConversiveisException extends RuntimeException {

    /**Método chamador da exception, escreve um texto que fala do erro dando a posição no arquivo
     * xls/xlsx onde se encontra a célula.
     * @param coluna Posição no eixo x do arquivo xls/xlsx onde se encontra a célula.
     * @param linha Posição no eixo y do arquivo xls/xlsx onde se encontra a célula.*/
    public CelulaComElementosNaoConversiveisException(int linha, int coluna) {
        super("A celula do arquivo xls na linha " + linha + " e coluna " + coluna + " não é conversivel para o tipo da coluna");
    }
}
