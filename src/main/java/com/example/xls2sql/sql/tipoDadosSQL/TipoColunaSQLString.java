package com.example.xls2sql.sql.tipoDadosSQL;


import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlStringService;
import java.util.ArrayList;

/**Classe do tipo enum que armazena todos os tipos de coluna do sql que são no formato String.
 * @author Halan Silva
 * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql */
public enum TipoColunaSQLString implements TipoColunaSql {

    /**Variável referente ao formato de coluna Char do sql.*/
    CHAR,
    /**Variável referente ao formato de coluna VarChar do sql.*/
    VARCHAR,
    /**Variável referente ao formato de coluna TinyText do sql.*/
    TINYTEXT,
    /**Variável referente ao formato de coluna Text do sql.*/
    TEXT,
    /**Variável referente ao formato de coluna MediumText do sql.*/
    MEDIUMTEXT,
    /**Variável referente ao formato de coluna LongText do sql.*/
    LONGTEXT,
    /**Variável referente ao formato de coluna Blob do sql.*/
    BLOB,
    /**Variável referente ao formato de coluna MediumBlob do sql.*/
    MEDIUMBLOB,
    /**Variável referente ao formato de coluna LongBlob do sql.*/
    LONGBLOB;



    /**Método utilizado para dizer se um tipo de coluna sql aceita ou não um limite de tamanhos para as suas células
     * dita pelo usuário.
     * @return retorna true se o tipo de coluna aceitar um limite escolhido pelo usuário
     * (os tipos Char, VarChar, Text, Blob),
     * e false se não aceitar(os tipos TinyText, MediumText, LongText, MediumBlob, LongBlob).*/
    @Override
    public boolean aceitaNumeroElementos() {
        return switch (this) {
            case TINYTEXT, MEDIUMTEXT, LONGTEXT, MEDIUMBLOB, LONGBLOB -> false;
            default -> true;
        };
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


        if (this.equals(LONGTEXT) || listaCelula.size() == 1){
            String celula = listaCelula.get(0);
            if (celula.equals("null") || celula.equals(" ")){
                return true;
            }

            TipoDadosSqlStringService  service = new TipoDadosSqlStringService();

            return switch (this) {
                case CHAR -> service.verificarCelulaChar(celula, numeroElementos);
                case VARCHAR -> service.verificarCelulaVarChar(celula, numeroElementos);
                case TINYTEXT -> service.verificarCelulaTinyText(celula);
                case TEXT -> service.verificarCelulaText(celula, numeroElementos);
                case MEDIUMTEXT -> service.verificarCelulaMediumText(celula);
                case LONGTEXT -> service.verificarCelulaLongText(listaCelula);
                case BLOB -> service.verificarCelulaBlob(celula, numeroElementos);
                case MEDIUMBLOB -> service.verificarCelulaMediumBlob(celula);
                case LONGBLOB -> service.verificarCelulaLongBlob(celula);
            };
        }

        return false;

    }


}
