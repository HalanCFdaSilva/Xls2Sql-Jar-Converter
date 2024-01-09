package com.example.xls2sql.xls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class AnalisadorEnderecoTest {

    @Test
    public void verificarEnderecoComExtensaoErrada(){
        String enderecoDocumento ="C:\\SQLtest\\SQL_03.sql";
        Assertions.assertFalse(AnalisadorEndereco.verificarArquivoXls(enderecoDocumento));
    }

    @Test
    public void verificarEnderecoNaoExistente(){
        String enderecoDocumento ="C:\\SQLtest\\SQL_30.xls";
        Assertions.assertFalse(AnalisadorEndereco.verificarArquivoXls(enderecoDocumento));
    }


}
