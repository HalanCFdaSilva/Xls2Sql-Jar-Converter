package com.example.xls2sql.sql.tipoDadosSQL;


import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;

public class TipoDadosSqlFactory {



    public  TipoDadosSql generate(String celulaSegundaLinhaExcel, int colunaexcel){

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
