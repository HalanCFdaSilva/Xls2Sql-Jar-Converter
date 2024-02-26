package com.example.xls2sql.sql.factorys;

import com.example.xls2sql.sql.domain.TipoDados;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSql;
import com.example.xls2sql.sql.tipoDadosSQL.TipoDadosSqlNumeric;


/**Classe do tipo factory usada para gerar a Classe TipoDados.
 * @author Halan Silva
 * @see TipoDados*/
public abstract class TipoDadosFactory {

    /**<p>Método que gera um TipoDados a partir de uma String e um int.</p>
     * <p>Usa a classe TipoDadosSqlFactory para converter a String em um TipoDadosSql.
     * Caso ela não consiga converter a String vai soltar uma exceção do tipo TipoDadoSqlNaoEncontradoException. </p>
     * @param celulaSegundaLinhaExcel String que vai ser convertida em uma das classes que implementam a
     *                               interface TipoDadosSql e no numeroElementos.
     * @param colunaExcel posição no eixo x da celula no arquivo xls/xlsx.
     * @return {@link TipoDados TipoDados}
     * @see TipoDadosSqlFactory
     * */
    public static TipoDados generate(String celulaSegundaLinhaExcel,int colunaExcel){
        String numeroElementosString = null;

        if (celulaSegundaLinhaExcel.contains("(")){

            int indexAbreColchete = celulaSegundaLinhaExcel.indexOf("(");
            int indexFechaColchete = celulaSegundaLinhaExcel.indexOf(")");
            numeroElementosString = celulaSegundaLinhaExcel.substring(indexAbreColchete + 1,indexFechaColchete);
            celulaSegundaLinhaExcel = celulaSegundaLinhaExcel.substring(0,indexAbreColchete);
        }

        TipoDadosSqlFactory factoryTipoDadosSql = new TipoDadosSqlFactory();
        TipoDadosSql tipo = factoryTipoDadosSql.generate(celulaSegundaLinhaExcel,colunaExcel);

        double numeroElementos = 0;
        if (numeroElementosString != null && tipo.aceitaNumeroElementos()){
            if (tipo.equals(TipoDadosSqlNumeric.DECIMAL)&& numeroElementosString.contains(",")){
                numeroElementosString = numeroElementosString.replace(",",".");
            }
            numeroElementos = Double.parseDouble(numeroElementosString);

        }

        return new TipoDados(tipo, numeroElementos,colunaExcel);

    }
}
