package com.example.xls2sql.xls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalisadorEnderecoTest {

    @Test
    public void verificarEnderecoComExtensaoErrada(){
        String enderecoDocumento ="C:\\Users\\levi\\Desktop\\programar\\SQL\\SQL_03.sql";
        Assertions.assertFalse(AnalisadorEndereco.verificarArquivoXls(enderecoDocumento));
    }

    @Test
    public void verificarEnderecoNaoExistente(){
        String enderecoDocumento ="C:\\Users\\levi\\Desktop\\programar\\SQL\\SQL_30.xls";
        Assertions.assertFalse(AnalisadorEndereco.verificarArquivoXls(enderecoDocumento));
    }

    @Test
    public void verificarEnderecoXlsExistente(){
        String enderecoDocumento ="C:\\Users\\levi\\Desktop\\programar\\SQL\\PRODUTOS.xlsx";
        Assertions.assertTrue(AnalisadorEndereco.verificarArquivoXls(enderecoDocumento));
    }
}
