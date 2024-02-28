package com.example.xls2sql.sql.tipoDadosSQL;

import com.example.xls2sql.sql.tipoDadosSQL.tipoDadosSqlService.TipoDadosSqlNumericService;

import java.util.ArrayList;
/**Classe do tipo enum que armazena todos os tipos de coluna do sql que são no formato Numeric.
 * @author Halan Silva
 * @see com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql */
public enum TipoColunaSqlNumeric implements TipoColunaSql {
    /**Variável referente ao formato de coluna Decimal do sql.*/
    DECIMAL,
    /**Variável referente ao formato de coluna Bit do sql.*/
    BIT,
    /**Variável referente ao formato de coluna TinyInt do sql.*/
    TINYINT,
    /**Variável referente ao formato de coluna SmallInt do sql.*/
    SMALLINT,
    /**Variável referente ao formato de coluna MediumInt do sql.*/
    MEDIUMINT,
    /**Variável referente ao formato de coluna Int do sql.*/
    INT,
    /**Variável referente ao formato de coluna BigInt do sql.*/
    BIGINT;


    /**Método utilizado para dizer se um tipo de coluna sql aceita ou não um limite de tamanhos para as suas células
     * dita pelo usuário.
     * @return retorna true se o tipo de coluna aceitar um limite escolhido pelo usuário (os tipos Bit e Decimal),
     * e false se não aceitar(os tipos TinyInt, SmallInt, Int, MediumInt, BigInt).*/
    @Override
    public boolean aceitaNumeroElementos() {
        return switch (this) {
            case BIT, DECIMAL -> true;
            default -> false;
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

        if (listaCelula.size() == 1 ) {
            String celula = listaCelula.get(0);
            if (celula.equals("null") || celula.equals(" ")){
                return true;
            }
            TipoDadosSqlNumericService service = new TipoDadosSqlNumericService(numeroElementos);

            return switch (this) {
                case BIT -> service.verificarCelulaBit(celula);
                case TINYINT -> service.verificarCelulaTinyInt(celula);
                case SMALLINT -> service.verificarCelulaSmallInt(celula);
                case MEDIUMINT -> service.verificarCelulaMediumInt(celula);
                case BIGINT -> service.verificarCelulaBigInt(celula);
                case INT -> service.verificarCelulaInt(celula);
                case DECIMAL -> service.verificarCelulaDecimal(celula);
            };

        }

        return false;
    }
}
