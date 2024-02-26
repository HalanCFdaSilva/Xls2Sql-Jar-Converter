package com.example.xls2sql.sql.factorys;


import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSQLString;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlDateTime;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;

/**Classe do tipo factory que cria uma classe que implementa a interface TipoDadosSql.
 * @see TipoDadosSql
 * @author Halan Silva*/
public class TipoDadosSqlFactory {

    /**Método que a partir de uma String gera uma classe que implementa a interface TipoDadosSql.
     * @param celulaSegundaLinhaExcel Texto com o nome do tipo de dados sql para identificar qual criar.
     * @param colunaexcel Posição no eixo y da celula no arquivo xls/xlsx
     * @return retorna uma instância de um dos enums TipoDadosSqlNumeric, TipoDadosSQLString ou TipoDadosSqlDateTime.
     * @see TipoDadosSqlNumeric
     * @see TipoDadosSQLString
     * @see TipoDadosSqlDateTime*/
    public TipoDadosSql generate(String celulaSegundaLinhaExcel, int colunaexcel){

        celulaSegundaLinhaExcel  = celulaSegundaLinhaExcel.replace(" ", "");

        TipoDadosSql tipoDadosSql = null;
        tipoDadosSql = this.generateSqlNumeric(celulaSegundaLinhaExcel);

        if (tipoDadosSql == null){

            tipoDadosSql = this.generateSqlString(celulaSegundaLinhaExcel);
            if (tipoDadosSql == null){
                tipoDadosSql = this.generateSqlDateTime(celulaSegundaLinhaExcel);
            }
        }

        if (tipoDadosSql == null){

            throw new TipoDadoSqlNaoEncontradoException(colunaexcel);
        }
        return tipoDadosSql;
    }

    /**Verifica se o texto é igual a alguma das constantes do enum TipoDadosSqlNumeric.
     * @param celulaSegundaLinhaExcel Texto a ser verificado.
     * @return retorna uma instância do enum se o texto for igual a uma das constantes ou retorna null se
     * não identificar nenhuma das constantes.
     * @see TipoDadosSqlNumeric*/
    private TipoDadosSqlNumeric generateSqlNumeric(String celulaSegundaLinhaExcel){
        TipoDadosSqlNumeric tipoDadosSql = null;
        for (TipoDadosSqlNumeric dado : TipoDadosSqlNumeric.values()) {
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
     * @see TipoDadosSQLString*/
    private TipoDadosSQLString generateSqlString(String celulaSegundaLinhaExcel){
        TipoDadosSQLString tipoDadosSql = null;
        for (TipoDadosSQLString dado : TipoDadosSQLString.values()) {
            if (dado.name().equalsIgnoreCase(celulaSegundaLinhaExcel)) {
                tipoDadosSql = dado;
                break;
            }
        }
        return tipoDadosSql;
    }

    /**Verifica se o texto é igual a alguma das constantes do enum TipoDadosSqlDateTime.
     * @param celulaSegundaLinhaExcel Texto a ser verificado.
     * @return retorna uma instância do enum se o texto for igual a uma das constantes ou retorna null se
     * não identificar nenhuma das constantes.
     * @see TipoDadosSqlDateTime*/
    private TipoDadosSqlDateTime generateSqlDateTime(String celulaSegundaLinhaExcel){
        TipoDadosSqlDateTime tipoDadosSql = null;
        for (TipoDadosSqlDateTime dado : TipoDadosSqlDateTime.values()) {
            if (dado.name().equalsIgnoreCase(celulaSegundaLinhaExcel)) {
                tipoDadosSql = dado;
                break;
            }
        }
        return tipoDadosSql;
    }
}
