package com.example.xls2sql.sql.domain;

import com.example.xls2sql.sql.factorys.TipoDadosFactory;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;


/**Classe que guarda as regras de uma coluna do sql e que todas as celulas dessa coluna devem seguir.
 * @author Halan Silva
 * @see Coluna
 * @see CelulaLinhaSql*/
public record TipoDados(
        /*Esta variável representa o tipo de dados da coluna que pode ser do tipo String, numeric ou date. */
        TipoDadosSql tipoDadosSql,

        /* Esta variável representa o número máximo de elementos que um TipoDadosSql permite.
         Caso esta variável seja omitida na String será considerada 0, e dessa forma será usado o limite padrão do TipoDadosSql.*/
        double numeroElementos,

        /* Posição do tipoDados no eixo x do arquivo xls/xlsx, serve na hora de se orientar para comparar as células.*/
        int posicaoColunaXls) {


    /**<p>Método que checa se o usuário registrou um limite de tamanho para a célula.</p>
     * <p>O método verifica se o numero registrado é maior que 0,
     * porque se o cliente não por um limite ou por um numero negativo o Factory registra zero.</p>
     * @see TipoDadosFactory */
    public boolean contemNumeroElementos() {
        return numeroElementos != 0;
    }


}
