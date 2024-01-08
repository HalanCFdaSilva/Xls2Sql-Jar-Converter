package com.example.xls2sql.sql;

import com.example.xls2sql.sql.exceptions.TipoDadoSqlNaoEncontradoException;

import static org.apache.logging.log4j.Level.values;

public enum TipoDadosSql {
    INT,
    VARCHAR,
    DECIMAL,
    DATETIME,
    BLOB,
    FLOAT;

    public static TipoDadosSql checarDados(String tipoDadoSql, int colunaExcel) {
        TipoDadosSql tipo = null;
        for (TipoDadosSql dado : values()) {
            if (dado.name().equalsIgnoreCase(tipoDadoSql)) {
                tipo = dado;
                break;
            }
        }

        if(tipo == null){
            throw new TipoDadoSqlNaoEncontradoException(colunaExcel);
        }
        return tipo;
    }
}

