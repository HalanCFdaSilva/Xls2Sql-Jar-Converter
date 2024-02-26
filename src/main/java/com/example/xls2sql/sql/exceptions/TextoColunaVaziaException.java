package com.example.xls2sql.sql.exceptions;

/**Exceção dada quando uma das colunas da primeira linha do arquivo xls/xlsx está vazia.
 * <p>A primeira linha do sql é usada para pegar as colunas do sql e sem ela não da para inserir uma linha que a utilize</p>
 * @see java.lang.RuntimeException
 * @author Halan Silva*/
public class TextoColunaVaziaException extends RuntimeException{

    /**Método que chama a exception dando a localização exata da célula no arquivo xls/xlsx.
     * @param coluna  posição no eixo x da célula no arquivo xls/xlsx.*/
    public TextoColunaVaziaException(int coluna) {
        super("O texto na coluna " + coluna + " e linha 1 está vazia ");
    }
}
