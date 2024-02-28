package com.example.xls2sql.sql.factorys;


import com.example.xls2sql.sql.exceptions.TipoColunaSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoColunaSqlNumeric;

/**Classe do tipo factory que cria uma classe que implementa a interface TipoDadosSql.
 * @see TipoColunaSql
 * @author Halan Silva*/
public class TipoDadosSqlFactory {

    /**Método que a partir de uma String gera uma classe que implementa a interface TipoDadosSql.
     * @param celulaSegundaLinhaExcel Texto com o nome do tipo de dados sql para identificar qual criar.
     * @param colunaexcel Posição no eixo y da celula no arquivo xls/xlsx
     * @return retorna uma instância de um dos enums TipoDadosSqlNumeric, TipoDadosSQLString ou TipoColunaSqlDateTime.
     * @see TipoColunaSqlNumeric
     * @see TipoColunaSQLString
     * @see TipoColunaSqlDateTime */
    public TipoColunaSql generate(String celulaSegundaLinhaExcel, int colunaexcel){

        celulaSegundaLinhaExcel  = celulaSegundaLinhaExcel.replace(" ", "");

        TipoColunaSql tipoColunaSql = null;
        tipoColunaSql = this.generateSqlNumeric(celulaSegundaLinhaExcel);

        if (tipoColunaSql == null){

            tipoColunaSql = this.generateSqlString(celulaSegundaLinhaExcel);
            if (tipoColunaSql == null){
                tipoColunaSql = this.generateSqlDateTime(celulaSegundaLinhaExcel);
            }
        }

        if (tipoColunaSql == null){

            throw new TipoColunaSqlNaoEncontradoException(colunaexcel);
        }
        return tipoColunaSql;
    }

    /**Verifica se o texto é igual a alguma das constantes do enum TipoDadosSqlNumeric.
     * @param celulaSegundaLinhaExcel Texto a ser verificado.
     * @return retorna uma instância do enum se o texto for igual a uma das constantes ou retorna null se
     * não identificar nenhuma das constantes.
     * @see TipoColunaSqlNumeric */
    private TipoColunaSqlNumeric generateSqlNumeric(String celulaSegundaLinhaExcel){
        TipoColunaSqlNumeric tipoDadosSql = null;
        for (TipoColunaSqlNumeric dado : TipoColunaSqlNumeric.values()) {
            if (dado.name().equalsIgnoreCase(celulaSegundaLinhaExcel)) {
                tipoDadosSql = dado;
                break;
            }
        }
        return tipoDadosSql;
    }

    /**Verifica se o texto é igual a alguma das constantes do enum TipoDadosSqlString.
     * @param celulaSegundaLinhaExcel Texto a ser verificado.
     * @return retorna uma instância do enum se o texto for igual a uma das constantes ou retorna null se
     * não identificar nenhuma das constantes.
     * @see TipoColunaSQLString */
    private TipoColunaSQLString generateSqlString(String celulaSegundaLinhaExcel){
        TipoColunaSQLString tipoDadosSql = null;
        for (TipoColunaSQLString dado : TipoColunaSQLString.values()) {
            if (dado.name().equalsIgnoreCase(celulaSegundaLinhaExcel)) {
                tipoDadosSql = dado;
                break;
            }
        }
        return tipoDadosSql;
    }

    /**Verifica se o texto é igual a alguma das constantes do enum TipoColunaSqlDateTime.
     * @param celulaSegundaLinhaExcel Texto a ser verificado.
     * @return retorna uma instância do enum se o texto for igual a uma das constantes ou retorna null se
     * não identificar nenhuma das constantes.
     * @see TipoColunaSqlDateTime */
    private TipoColunaSqlDateTime generateSqlDateTime(String celulaSegundaLinhaExcel){
        TipoColunaSqlDateTime tipoDadosSql = null;
        for (TipoColunaSqlDateTime dado : TipoColunaSqlDateTime.values()) {
            if (dado.name().equalsIgnoreCase(celulaSegundaLinhaExcel)) {
                tipoDadosSql = dado;
                break;
            }
        }
        return tipoDadosSql;
    }
}
