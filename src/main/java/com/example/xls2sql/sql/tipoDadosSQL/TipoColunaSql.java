package com.example.xls2sql.sql.tipoDadosSQL;

import java.util.ArrayList;
/**Esta interface faz a integração entre os diversos enums de tipo de dados sql.
 * @author  Halan Silva
 * @see TipoColunaSQLString
 * @see TipoColunaSqlNumeric
 * @see TipoColunaSqlDateTime */
public interface TipoColunaSql {

    /**Método utilizado para dizer se um tipo de coluna sql aceita ou não um limite de tamanhos para as suas células
     * dita pelo usuário.
     * @return retorna true se o tipo de coluna aceitar um limite escolhido pelo usuário e false se não aceitar.*/
    boolean aceitaNumeroElementos();

    /**Método que faz a checagem se um conteúdo a ser posto na linha está dentro das regras do tipo de coluna.
     * @param listaCelula  Arraylist com o conteúdo a ser verificado
     * @param numeroElementos limite de tamanho do conteúdo dito pelo usuário.
     * @see com.example.xls2sql.sql.domain.CelulaLinhaSql
     * @return Retorna true se o conteúdo do arraylist estiver dentro das regras e false se estiver fora das regras.*/
    boolean verificarCelula(ArrayList<String> listaCelula, double numeroElementos);



}

