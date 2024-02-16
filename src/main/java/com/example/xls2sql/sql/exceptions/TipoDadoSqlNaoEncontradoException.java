package com.example.xls2sql.sql.exceptions;

public class TipoDadoSqlNaoEncontradoException extends RuntimeException {

    public TipoDadoSqlNaoEncontradoException(int colunaExcel) {
        super("O tipo de dados especificado na coluna " + (colunaExcel+1) + " não foi encontrado.");
    }

    public TipoDadoSqlNaoEncontradoException(int linhaExcel, int colunaExcel) {
        super("Não foi possível encontrar o tipo de dado da celula na linha " + linhaExcel + " e coluna " + colunaExcel + ".");
    }
    
}
