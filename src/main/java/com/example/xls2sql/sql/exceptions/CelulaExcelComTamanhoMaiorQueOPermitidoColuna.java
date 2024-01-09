package com.example.xls2sql.sql.exceptions;

public class CelulaExcelComTamanhoMaiorQueOPermitidoColuna extends RuntimeException {

    public CelulaExcelComTamanhoMaiorQueOPermitidoColuna(Integer linha, String nomeColuna) {
        super("A celula do excel na linha " + linha + " e coluna " + nomeColuna +
                "está maior que o numero De elementos permitido" );
    }
}
