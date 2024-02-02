package com.example.xls2sql.sql.exceptions;

public class CelulaExcelComTamanhoMaiorQueOPermitidoColunaException extends RuntimeException {

    public CelulaExcelComTamanhoMaiorQueOPermitidoColunaException(Integer linha, Integer coluna) {
        super("A celula do excel na linha " + linha + " e coluna " + coluna +
                "está maior que o numero De elementos permitido" );
    }
}
