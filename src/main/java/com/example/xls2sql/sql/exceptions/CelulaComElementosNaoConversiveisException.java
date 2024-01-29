package com.example.xls2sql.sql.exceptions;

public class CelulaComElementosNaoConversiveisException extends RuntimeException {
    public CelulaComElementosNaoConversiveisException(int linha, int coluna) {
        super("A celula do arquivo xls na linha " + linha + " e coluna " + coluna + " não é conversivel para o tipo da coluna");
    }
}
