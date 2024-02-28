package com.example.xls2sql.sql.tipoDadosSQL;


import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlDateTimeService;

import java.util.ArrayList;
/**Classe do tipo enum que armazena todos os tipos de coluna do sql que são no formato DateTime.
 * @author Halan Silva
 * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql */
public enum TipoColunaSqlDateTime implements TipoColunaSql {
    /**Variável referente ao formato de coluna Date do sql.*/
    DATE,
    /**Variável referente ao formato de coluna DateTime do sql.*/
    DATETIME,
    /**Variável referente ao formato de coluna Time do sql.*/
    TIME,
    /**Variável referente ao formato de coluna Year do sql.*/
    YEAR,
    /**Variável referente ao formato de coluna TimeStamp do sql.*/
    TIMESTAMP;

    /**Método que diz se um determinado tipo de coluna aceita um limite imposto pelo usuário.
     * @return retorna false porque nenhum dos tipos aceita um limite imposto pelo usuário.*/
    @Override
    public boolean aceitaNumeroElementos() {
        return false;
    }

    /**Método que faz a checagem se um conteúdo a ser posto na linha está dentro das regras do tipo de coluna.
     * <p>Caso o conteúdo da célula seja uma única String escrito null ou somente um espaço ele irá entender que esse
     * conteúdo não será inserido e não será feito a checagem, retornando true.</p>
     * @param listaCelula  Arraylist com o conteúdo a ser verificado
     * @param numeroElementos limite de tamanho do conteúdo dito pelo usuário.
     * @see com.example.xls2sql.sql.domain.CelulaLinhaSql
     * @return Retorna true se o conteúdo do arraylist estiver dentro das regras e false se estiver fora das regras.*/
    @Override
    public boolean verificarCelula(ArrayList<String> listaCelula, double numeroElementos) {

        if (listaCelula.size() == 1 ){
            String celula = listaCelula.get(0);

            if (celula.equals("null") || celula.equals(" ")){
                return true;
            }

            TipoDadosSqlDateTimeService service = new TipoDadosSqlDateTimeService();

            return switch (this) {
                case DATE -> service.verificarCelulaDate(celula);
                case DATETIME -> service.verificarCelulaDateTime(celula);
                case TIMESTAMP -> service.verificarCelulaTimeStamp(celula);
                case YEAR -> service.verificarCelulaYear(celula);
                case TIME -> service.verificarCelulaTime(celula);
            };
        }

        return false;
    }
}
