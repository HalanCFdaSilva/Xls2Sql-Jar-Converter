package com.example.xls2sql.sql.exceptions;

public class TipoDadoSqlNaoEncontradoException extends RuntimeException {

    public TipoDadoSqlNaoEncontradoException(int colunaExcel) {
        super("O tipo de dados especificado na coluna " + (colunaExcel+1) + " não foi encontrado.");
    }
}
