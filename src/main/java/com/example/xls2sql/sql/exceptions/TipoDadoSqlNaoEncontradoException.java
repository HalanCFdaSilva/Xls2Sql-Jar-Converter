package com.example.xls2sql.sql.exceptions;

public class TipoDadoSqlNaoEncontradoException extends RuntimeException {

    public TipoDadoSqlNaoEncontradoException(int colunaExcel) {
        super("O tipo de dados especificado na linha 2  e coluna " + colunaExcel + " não foi encontrado.");
    }
}
