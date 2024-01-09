package com.example.xls2sql.sql.exceptions;

public class TextoColunaVaziaException extends RuntimeException{

    public TextoColunaVaziaException(int coluna) {
        super("O texto na coluna " + coluna + " e linha 1 está vazia ");
    }
}
